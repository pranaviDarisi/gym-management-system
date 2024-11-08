package com.management.gym.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.gym.entity.Membership;

//hi
public interface MembershipRepo extends JpaRepository<Membership, Long> {
	Optional<Membership> findByMembershipType(String membershipType);
	 Optional<Membership> findByClients_Username(String username);
}

