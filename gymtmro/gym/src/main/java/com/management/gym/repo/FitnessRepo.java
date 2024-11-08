package com.management.gym.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.gym.entity.FitnessPlan;
import com.management.gym.entity.Client;

public interface FitnessRepo extends JpaRepository<FitnessPlan, Long> {
	
	 List<FitnessPlan> findByClients(Client user); 
	 
	 List<FitnessPlan> findByClients_Username(String username);

}
