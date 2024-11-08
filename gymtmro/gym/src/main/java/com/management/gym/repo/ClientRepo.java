package com.management.gym.repo;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.management.gym.entity.Client;
import com.management.gym.entity.FitnessPlan;


public interface ClientRepo extends JpaRepository<Client, Long> {


    
    Optional<Client> findByUsername(String username); 
    
    Set<FitnessPlan> findFitnessPlansById(Long clientId);
    
    boolean existsByUsername(String username);
    
    @Query("SELECT c FROM Client c LEFT JOIN FETCH c.authorities WHERE c.username = :username")
    Optional<Client> findByUsernameWithAuthorities(@Param("username") String username);

  
    
   
}

