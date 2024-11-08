package com.management.gym.controllers;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.management.gym.entity.Authority;
import com.management.gym.entity.Client;
import com.management.gym.entity.Membership;
import com.management.gym.repo.AuthorityRepo;
import com.management.gym.repo.MembershipRepo;
import com.management.gym.service.UserService;

@RestController
public class RegistrationController {

    @Autowired
    private UserService clientService; 
    @Autowired
    private AuthorityRepo authRepo;
    @Autowired
    private MembershipRepo membershipRepo; 
    
    @GetMapping("/registration/check")
    public ResponseEntity<Boolean> checkRegistration(@RequestParam("username") String username) {
        boolean isRegistered = clientService.checkIfRegistered(username);
        return ResponseEntity.ok(isRegistered);
    }


    
    
    @PostMapping("/register/save")
    public ResponseEntity<String> processRegister(
    		 @RequestParam("username") String username,
    		 @RequestParam("password") String password,
    	        @RequestParam("mobile") String mobile,
    	        @RequestParam("age") int age,
    	        @RequestParam("weight") double weight,
    	        @RequestParam("height") double height,
    	        @RequestParam("membershipType") String membershipType){
        try {
            Client client = new Client();
            client.setUsername(username);
            client.setPassword(password);
            client.setMobile(mobile);
            client.setAge(age);
            client.setWeight(weight);
            client.setHeight(height);
            client.setEnabled(true);

            Optional<Membership> membership = membershipRepo.findByMembershipType(membershipType);
            if (membership.isPresent()) {
                client.setMembership(membership.get());
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid membership type.");
            }
            Authority authority = authRepo.findByAuthority("ROLE_USER")
                    .orElseGet(() -> authRepo.save(new Authority("ROLE_USER"))); 
            client.setAuthorities(Set.of(authority)); 

            clientService.saveUser(client);

            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during registration: " + e.getMessage());
        }
    }
    
    @PostMapping("/admin/register/save")
    public ResponseEntity<String> adminProcessRegister(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("mobile") String mobile,
            @RequestParam("age") int age,
            @RequestParam("weight") double weight,
            @RequestParam("height") double height,
            @RequestParam("membershipType") String membershipType) {
        try {
            Client client = new Client();
            client.setUsername(username);
            client.setPassword(password);
            client.setMobile(mobile);
            client.setAge(age);
            client.setWeight(weight);
            client.setHeight(height);
            client.setEnabled(true);

            // Set membership to null if not found
            Optional<Membership> membership = membershipRepo.findByMembershipType(membershipType);
            client.setMembership(membership.orElse(null)); // Set to null if not present

            Authority authority = authRepo.findByAuthority("ROLE_ADMIN")
                    .orElseGet(() -> authRepo.save(new Authority("ROLE_ADMIN"))); 
            client.setAuthorities(Set.of(authority)); 

            clientService.saveUser(client);

            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during registration: " + e.getMessage());
        }
    }


}
