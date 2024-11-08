package com.management.gym.repo;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.gym.entity.Authority;

public interface AuthorityRepo extends JpaRepository<Authority, Long> {
   
    Optional<Authority> findByAuthority(String authority);
}
