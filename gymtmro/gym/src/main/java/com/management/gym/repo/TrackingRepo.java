package com.management.gym.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.gym.entity.FitnessTracking;

public interface TrackingRepo extends JpaRepository<FitnessTracking, Long> {

	List<FitnessTracking> findByClient_Id(Long clientId);}
