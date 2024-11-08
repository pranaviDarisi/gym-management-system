package com.management.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.management.security.DTO.ClientDTO;
import com.management.security.openfeign.UserServiceFeignClient;
import com.management.security.secure.CustomUserDetails;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserServiceFeignClient userServiceFeignClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            ResponseEntity<ClientDTO> responseEntity = userServiceFeignClient.getClientByUsername(username);
            ClientDTO client = responseEntity.getBody();

            if (client != null && client.getUsername() != null) {
                return new CustomUserDetails(
                    client.getUsername(),
                    client.getPassword(),
                    client.getAuthorities(),
                    client.isEnabled()
                );
            } 
        } catch (Exception e) {
        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }


}
