package com.management.security.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.management.security.DTO.ClientDTO;
import com.management.security.DTO.FitnessPlanDTO;
import com.management.security.DTO.MembershipDTO;
import com.management.security.openfeign.AdminFeignClient;
import com.management.security.openfeign.FitnessPlanFeignClient;
import com.management.security.openfeign.UserServiceFeignClient;

@Controller
@RequestMapping("/admin")
public class AdminFeignController {

    @Autowired
    private AdminFeignClient adminFeignClient;
    @Autowired
    private FitnessPlanFeignClient fitnessPlanFeignClient;
	@Autowired
	private PasswordEncoder passwordEncoder;
    
    @Autowired
    private UserServiceFeignClient userServiceFeignClient;
 
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

        return "admin-dashboard";
    }

    @GetMapping("/getusers")
    public String getAllUsers(Model model) {
        ResponseEntity<List<ClientDTO>> responseEntity = adminFeignClient.getAllUsers();
        List<ClientDTO> users = responseEntity.getBody(); 
        
        model.addAttribute("users", users);
        
        return "userslist-display"; 
    }

    @GetMapping("/assignPlan")
    public String showAssignPlanForm(Model model) {
        ResponseEntity<List<ClientDTO>> clientsResponse = adminFeignClient.getAllUsers();
        List<ClientDTO> clients = clientsResponse.getBody();
        model.addAttribute("clients", clients);

        ResponseEntity<List<FitnessPlanDTO>> fitnessPlansResponse = fitnessPlanFeignClient.getAllFitnessPlans();
        List<FitnessPlanDTO> fitnessPlans = fitnessPlansResponse.getBody();
        model.addAttribute("fitnessPlans", fitnessPlans);

        return "assign-plan";
    }

    @PostMapping("/savePlan")
    public String assignFitnessPlanToUser(@RequestParam("userId") Long userId, @RequestParam("fitnessPlanId") Long fitnessPlanId, Model model) {
        adminFeignClient.assignFitnessPlan(userId, fitnessPlanId);
        model.addAttribute("successMessage", "Successfully assigned the plan!");
        
        List<ClientDTO> users = adminFeignClient.getAllUsers().getBody();
        List<FitnessPlanDTO> fitnessPlans = fitnessPlanFeignClient.getAllFitnessPlans().getBody();
        model.addAttribute("users", users);
        model.addAttribute("fitnessPlans", fitnessPlans);
        
        return "assign-plan"; 
    }
    @GetMapping("/details/{id}")
    public String getUserDetails(@PathVariable("id") Long id, Model model) {
        ResponseEntity<ClientDTO> response = adminFeignClient.getUserDetails(id);
        ClientDTO user = response.getBody();
        
        model.addAttribute("user", user);
        return "adminUserdetails-display"; 
    }
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {

        return "adminRegister"; 
    }
    @PostMapping("/register/save")
    public String adminRegister(
    		 @RequestParam("username") String username,
    		 @RequestParam("password") String password,
    	        @RequestParam("mobile") String mobile,
    	        @RequestParam("age") int age,
    	        @RequestParam("weight") double weight,
    	        @RequestParam("height") double height,
    	        @RequestParam("membershipType") String membershipType,
            @AuthenticationPrincipal UserDetails userDetails, // Inject UserDetails
            Model model) {

        if (userServiceFeignClient.isUserRegistered(username)) {
            model.addAttribute("error", "You cannot register twice.");
            return "adminRegister"; 
        }
        String encodedPassword = passwordEncoder.encode(password);
        ClientDTO clientDto = new ClientDTO();
        clientDto.setUsername(username);
        clientDto.setPassword(encodedPassword);
        clientDto.setMobile(mobile);
        clientDto.setAge(age);
        clientDto.setWeight(weight);
        clientDto.setHeight(height);
        
        MembershipDTO membership = new MembershipDTO();
        membership.setMembershipType(membershipType); // Set the membership type here
        clientDto.setMembership(membership); // Associate the membership DTO

        

        ResponseEntity<String> responseEntity = adminFeignClient.adminProcessRegister(username,encodedPassword, mobile, age, weight, height, membershipType);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            model.addAttribute("username", username);
            return "redirect:/home"; 
        } else {
            model.addAttribute("error", responseEntity.getBody());
            return "adminRegister"; 
        }
    }
    
    
}
