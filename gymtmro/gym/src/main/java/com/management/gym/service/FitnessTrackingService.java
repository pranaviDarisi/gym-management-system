package com.management.gym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.gym.entity.FitnessTracking;
import com.management.gym.entity.Client;
import com.management.gym.repo.TrackingRepo;
import com.management.gym.repo.ClientRepo;

@Service
public class FitnessTrackingService {

    @Autowired
    private TrackingRepo repo;

    @Autowired
    private ClientRepo userRepo;

    public FitnessTracking saveFitnessTracking(FitnessTracking tracking,String username) {
        Client user = userRepo.findByUsername(username).orElse(null);
        if (user != null) {
            tracking.setClient(user); 
            return repo.save(tracking); 
        }
        return null; 
    }

    public List<FitnessTracking> getAllWorkoutsForUser(Long clientId) {
        return repo.findByClient_Id(clientId);
    }


}
