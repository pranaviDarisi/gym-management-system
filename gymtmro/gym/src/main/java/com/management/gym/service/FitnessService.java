package com.management.gym.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.gym.DTO.FitnessPlanDTO;
import com.management.gym.entity.Client;
import com.management.gym.entity.FitnessPlan;
import com.management.gym.repo.ClientRepo;
import com.management.gym.repo.FitnessRepo;

@Service
public class FitnessService {

    @Autowired
    private FitnessRepo repo;
	@Autowired
	private ClientRepo ClientRepository;



	    public FitnessPlanDTO createFitnessPlan(FitnessPlanDTO fitnessPlanDTO) {
	        FitnessPlan fitnessPlan = new FitnessPlan();
	        fitnessPlan.setPlanName(fitnessPlanDTO.getPlanName());
	        fitnessPlan.setDescription(fitnessPlanDTO.getDescription());
	        
	        fitnessPlan = repo.save(fitnessPlan);
	        
	        fitnessPlanDTO.setId(fitnessPlan.getId());
	        return fitnessPlanDTO;
	    }
	


    public List<FitnessPlan> getAllPlans() {
        return repo.findAll();
    }

    public FitnessPlanDTO updatePlan(Long planId, FitnessPlanDTO updatedPlanDTO) {
        Optional<FitnessPlan> existingPlanOptional = repo.findById(planId);

        if (existingPlanOptional.isPresent()) {
            FitnessPlan existingPlan = existingPlanOptional.get();
            
            existingPlan.setPlanName(updatedPlanDTO.getPlanName());
            existingPlan.setDescription(updatedPlanDTO.getDescription());
            
            FitnessPlan savedPlan = repo.save(existingPlan);
            
            updatedPlanDTO.setId(savedPlan.getId());
            updatedPlanDTO.setPlanName(savedPlan.getPlanName());
            updatedPlanDTO.setDescription(savedPlan.getDescription());
            
            return updatedPlanDTO;
        }
        
        return null;  
        }
    
    public FitnessPlan getPlanById(Long planId) {
        Optional<FitnessPlan> plan = repo.findById(planId);
        return plan.orElse(null); 
    }

    public void deletePlan(Long planId) {
            repo.deleteById(planId);

    }
    
	public void assignFitnessPlanToUser(Long userId, Long fitnessPlanId) {
		Optional<Client> userOpt = ClientRepository.findById(userId);
		Optional<FitnessPlan> fitnessPlanOpt = repo.findById(fitnessPlanId);

		if (userOpt.isPresent() && fitnessPlanOpt.isPresent()) {
			Client user = userOpt.get();
			FitnessPlan fitnessPlan = fitnessPlanOpt.get();

			user.getFitnessPlans().add(fitnessPlan); 
			fitnessPlan.getClients().add(user); 

			ClientRepository.save(user); 
			repo.save(fitnessPlan); 
		} else {
			throw new RuntimeException("User or FitnessPlan not found"); // Handle errors as needed
		}

	}


	public List<FitnessPlan> getFitnessPlansForClient(String username) {
		return repo.findByClients_Username(username);
	}
	
	

}
