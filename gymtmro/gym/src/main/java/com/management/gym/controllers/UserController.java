package com.management.gym.controllers;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.management.gym.DTO.ClientDTO;
import com.management.gym.DTO.FitnessTrackingDTO;
import com.management.gym.DTO.MembershipDTO;
import com.management.gym.entity.Authority;
import com.management.gym.entity.Client;
import com.management.gym.entity.FitnessPlan;
import com.management.gym.entity.FitnessTracking;
import com.management.gym.entity.Membership;
import com.management.gym.repo.MembershipRepo;
import com.management.gym.service.FitnessService;
import com.management.gym.service.FitnessTrackingService;
import com.management.gym.service.MembershipService;
import com.management.gym.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FitnessTrackingService fitnessTrackingService;

    @Autowired
    private FitnessService fitnessService;

    @Autowired
    private MembershipService membershipService;
    @Autowired
    private MembershipRepo membershipRepo;

    @GetMapping("/getClient")
    public ResponseEntity<ClientDTO> getClientByUsername(@RequestParam("username") String username) {
        ClientDTO client = userService.findByUsername(username);
        if (client != null) {
            return ResponseEntity.ok(client);
        } else {
            return ResponseEntity.notFound().build(); 
        }
    }
    
    //these for log the workout and see the logged workout

    @GetMapping("/logWorkout")
    public ResponseEntity<FitnessTracking> logWorkoutForm() {
        FitnessTracking fitnessTracking = new FitnessTracking();
        return ResponseEntity.ok(fitnessTracking);
    }

    @PostMapping("/getLog")
    public ResponseEntity<String> logWorkout(
            @RequestParam("caloriesBurn") int caloriesBurn,
            @RequestParam("duration") int duration,
            @RequestParam("exerciseName") String exerciseName,
            @RequestParam("username") String username) {
        
        FitnessTracking fitnessTracking = new FitnessTracking();
        fitnessTracking.setCaloriesBurn(caloriesBurn);
        fitnessTracking.setDate(LocalDate.now());
        fitnessTracking.setDuration(duration);
        fitnessTracking.setExerciseName(exerciseName);

        ClientDTO clientDTO = userService.findByUsername(username);
        if (clientDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Client not found.");
        }

        Client client = convertToEntity(clientDTO);
        fitnessTracking.setClient(client);

        fitnessTrackingService.saveFitnessTracking(fitnessTracking, username);
        return ResponseEntity.ok("Workout logged successfully!");
    }

    @GetMapping("/loghistory")
    public ResponseEntity<List<FitnessTrackingDTO>> viewWorkoutHistory(@RequestParam("username") String username) {
        ClientDTO clientDTO = userService.findByUsername(username);
        if (clientDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); 
        }
        
        List<FitnessTracking> workoutHistory = fitnessTrackingService.getAllWorkoutsForUser(clientDTO.getId());
        List<FitnessTrackingDTO> workoutHistoryDTOs = workoutHistory.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
            
        return ResponseEntity.ok(workoutHistoryDTOs);
    }

//its used to show users particular membership 

    @GetMapping("/membership")
    public ResponseEntity<Map<String, Object>> viewMembership(@RequestParam("username") String username) {
        ClientDTO user = userService.findByUsername(username);

        if (user == null) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "User not found with username: " + username);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        if (user.getMembership() == null) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Membership not found for user: " + username);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        MembershipDTO currentMembership = user.getMembership();
        List<MembershipDTO> availableMemberships = membershipService.getAllMemberships();

        Map<String, Object> response = new HashMap<>();
        response.put("currentMembership", currentMembership);
        response.put("memberships", availableMemberships);

        return ResponseEntity.ok(response);
    }


    //profile

    @GetMapping("/details/{id}")
    public ResponseEntity<ClientDTO> getUserDetails(@PathVariable("id") Long id) {
        ClientDTO clientDTO = userService.findUserById(id); 

        if (clientDTO != null) {
            return ResponseEntity.ok(clientDTO); 
        } else {
            return ResponseEntity.notFound().build(); 
        }
    }
    
    //to see the plans associated to the particular user these plans should be assigned by admin

    @GetMapping("/userPlans")
    public ResponseEntity<List<FitnessPlan>> getUserFitnessPlans(@RequestParam("username") String username) {
        List<FitnessPlan> plans = fitnessService.getFitnessPlansForClient(username);
        
        if (plans.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(plans);
        }
        return ResponseEntity.ok(plans);
    }
    
    @PostMapping("/updateMembership")
    public ResponseEntity<String> updateMembership(
            @RequestParam("username") String username,
            @RequestParam("membershipId") Long membershipId) {
        boolean isUpdated = membershipService.updateMembership(username, membershipId);

        if (isUpdated) {
            return ResponseEntity.ok("Membership updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or Membership not found");
        }
    }
    
    
    private FitnessTrackingDTO convertToDTO(FitnessTracking fitnessTracking) {
        FitnessTrackingDTO dto = new FitnessTrackingDTO();
        dto.setId(fitnessTracking.getId());
        dto.setCaloriesBurn(fitnessTracking.getCaloriesBurn());
        dto.setDate(fitnessTracking.getDate());
        dto.setDuration(fitnessTracking.getDuration());
        dto.setExerciseName(fitnessTracking.getExerciseName());


        return dto;
    }
    
    @PutMapping("/details/update/{id}")
    public ResponseEntity<ClientDTO> updateUser(
    		@PathVariable("id") Long id,
    		    		@RequestParam("username") String username,
    			        @RequestParam("mobile") String mobile,
    			        @RequestParam("age") int age,
    			        @RequestParam("weight") double weight,
    			        @RequestParam("height") double height,
    			        @RequestParam("enabled") boolean enabled) {
    	
        ClientDTO client = userService.findUserById(id);
        client.setUsername(username);
        client.setMobile(mobile);
        client.setAge(age);
        client.setWeight(weight);
        client.setHeight(height);
        client.setEnabled(enabled);
        Membership membership = membershipRepo.findByClients_Username(username).get();
        MembershipDTO membershipdto= convertToMembershipDTO(membership);
        client.setMembership(membershipdto);

    	
            ClientDTO updatedUser = userService.updateUser(id,client);
            return ResponseEntity.ok(updatedUser);
        
    }
    // Convert from Membership to MembershipDTO
    public MembershipDTO convertToMembershipDTO(Membership membership) {
        MembershipDTO membershipDTO = new MembershipDTO();
        membershipDTO.setId(membership.getId());
        membershipDTO.setMembershipType(membership.getMembershipType());
        membershipDTO.setFee(membership.getFee());
        membershipDTO.setStatus(membership.isStatus());
        return membershipDTO;
    }




    // Conversion method from ClientDTO to Client
    private Client convertToEntity(ClientDTO clientDTO) {
        Client client = new Client();
        client.setId(clientDTO.getId());
        client.setUsername(clientDTO.getUsername());
        client.setPassword(clientDTO.getPassword()); // Be cautious with setting passwords directly
        client.setMobile(clientDTO.getMobile());
        client.setAge(clientDTO.getAge());
        client.setWeight(clientDTO.getWeight());
        client.setHeight(clientDTO.getHeight());
        client.setEnabled(clientDTO.isEnabled());

        // Assuming ClientDTO has a Set<AuthorityDTO> authorities
        if (clientDTO.getAuthorities() != null) {
            Set<Authority> authorities = clientDTO.getAuthorities().stream()
                .map(authDTO -> {
                    Authority authority = new Authority();
                    authority.setAuthority(authDTO.getAuthority()); 
                    return authority;
                })
                .collect(Collectors.toSet());
            client.setAuthorities(authorities); // Set authorities in Client entity
        }

        return client;
    }
}
