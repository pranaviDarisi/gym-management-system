package com.management.security.controller;


import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.management.security.DTO.ClientDTO;
import com.management.security.DTO.FitnessPlanDTO;
import com.management.security.DTO.FitnessTrackingDTO;
import com.management.security.DTO.MembershipDTO;
import com.management.security.openfeign.MembershipServiceFeignClient;
import com.management.security.openfeign.UserServiceFeignClient;

import feign.FeignException;

@Controller
@RequestMapping("/user")
public class UserFeignController {


    @Autowired
    private UserServiceFeignClient userServiceFeignClient;


	
    @GetMapping("/edit/{id}")
    public String showEditUserForm(@PathVariable("id") Long id, Model model) {
        ResponseEntity<ClientDTO> response = userServiceFeignClient.getUserById(id);
        ClientDTO user = response.getBody();
        
        model.addAttribute("user", user);
        return "edit-user";
    }


  @GetMapping("/dashboard")
    public String userDashboard(Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute("error", "User is not authenticated.");
            return "redirect:/login"; 
        }

        ResponseEntity<ClientDTO> response = userServiceFeignClient.getClientByUsername(principal.getName());

        if (response.getStatusCode().is2xxSuccessful()) {
            ClientDTO user = response.getBody();

            if (user != null) {
                model.addAttribute("user", user);
                model.addAttribute("userId", user.getId()); 
                model.addAttribute("username", user.getUsername());
                model.addAttribute("role", user.getAuthorities()); 
            } else {
                model.addAttribute("error", "User not found.");
            }
        } else {
            model.addAttribute("error", "Failed to fetch user details.");
        }

        return "user-dashboard";
    }


    
    
    @GetMapping("/logWorkout")
   	public String showLogForm() {
   		return "add-log";
   		
   	}
    @PostMapping("/getLog")
    public String createFitnessTracking(
            @RequestParam("caloriesBurn") int caloriesBurn,
            @RequestParam("duration") int duration,
            @RequestParam("exerciseName") String exerciseName,
            Principal principal,
            Model model) {
        
        ResponseEntity<String> response = userServiceFeignClient.logWorkout(caloriesBurn, duration, exerciseName, principal.getName());

        if (response.getStatusCode() == HttpStatus.OK) {
            model.addAttribute("message", "Fitness tracking created successfully!");
        } else {
            model.addAttribute("error", "Failed to create fitness tracking.");
        }
        model.addAttribute("fitnessTracking", response.getBody());

        return "redirect:/user/loghistory"; 
    }


    @GetMapping("/loghistory")
    public String viewWorkoutHistory(Principal principal, Model model) {
        ResponseEntity<List<FitnessTrackingDTO>> response = userServiceFeignClient.viewWorkoutHistory(principal.getName());

        model.addAttribute("workoutHistory", response.getBody());

        return "log-display"; 
    }

    @GetMapping("/membership")
    public String viewMembership(Principal principal, Model model) {
        try {
            ResponseEntity<Map<String, Object>> response = userServiceFeignClient.viewMembership(principal.getName());

            if (response.getStatusCode().is2xxSuccessful()) {
                Map<String, Object> membershipData = response.getBody();

                model.addAttribute("currentMembership", membershipData.get("currentMembership"));
                model.addAttribute("memberships", membershipData.get("memberships"));
                model.addAttribute("currentUser", principal.getName());
            } else {
                model.addAttribute("errorMessage", "Failed to retrieve membership details.");
            }
        } catch (FeignException.NotFound ex) {
            String errorMessage = "Membership not found for user: " + principal.getName();
            model.addAttribute("errorMessage", errorMessage);
        } catch (FeignException ex) {
            String errorMessage = "Error occurred: " + ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);
        }

        return "viewUserMembership-display"; 
    }




    @GetMapping("/userPlans")
    public String getFitnessPlans(Principal principal, Model model) {
        try {
            ResponseEntity<List<FitnessPlanDTO>> response = userServiceFeignClient.getUserFitnessPlans(principal.getName());

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                model.addAttribute("fitnessPlans", response.getBody());
            } 

        } catch (FeignException.NotFound e) {
            model.addAttribute("noPlansMessage", "No fitness plans available for user.");
        } 
        return "userFitnessPlan-display";
    }

    
    
    @GetMapping("/details/{id}")
    public String getUserDetails(@PathVariable("id") Long id, Model model) {
        ResponseEntity<ClientDTO> response = userServiceFeignClient.getUserDetails(id);
        ClientDTO user = response.getBody();
        MembershipDTO mem=user.getMembership();
        
        model.addAttribute("user", user);
        model.addAttribute("membership", mem);
        return "userdetails-display"; 
    }
    
    @PostMapping("/details/update/{id}")
    public String updateUserDetails(@PathVariable("id") Long id,
    		@RequestParam("username") String username,
	        @RequestParam("mobile") String mobile,
	        @RequestParam("age") int age,
	        @RequestParam("weight") double weight,
	        @RequestParam("height") double height,
	        @RequestParam("enabled") boolean enabled,
	        Model model) {
        ResponseEntity<ClientDTO> response = userServiceFeignClient.updateUserDetails(id, username,mobile,age,weight,height,enabled);
        
        if (response.getStatusCode() != HttpStatus.OK) {
            model.addAttribute("errorMessage", "Failed to update user");
            return "error-page"; 
        }

        model.addAttribute("successMessage", "User updated successfully");
        return "redirect:/user/details/" + id; 
    }
    
    @PostMapping("/updateMembership")
    public String updateMembership(@RequestParam("membershipId") Long membershipId, Principal principal) {
    	ResponseEntity<String> response = userServiceFeignClient.updateMembership(membershipId, principal.getName());

        if (response.getStatusCode() == HttpStatus.OK) {
            return "redirect:/user/membership"; 
        } else {
            return "redirect:/user/membership?error=update_failed"; 
        }
    }
}

    
    

    

    
    
    



