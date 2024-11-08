package com.management.gym.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.management.gym.DTO.ClientDTO;
import com.management.gym.entity.FitnessPlan;
import com.management.gym.service.FitnessService;
import com.management.gym.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private FitnessService fitnessService;
    
    //admin only can do
    
    @GetMapping("/getusers")
    public ResponseEntity<List<ClientDTO>> getAllUsers() {
        List<ClientDTO> clients = userService.findAllClients();
        return ResponseEntity.ok(clients); 
    }

    @GetMapping("/dashboard")
    public ResponseEntity<String> showDashboard() {
        return ResponseEntity.ok("Dashboard loaded successfully");
    }

    @PostMapping("/savePlan")
    public ResponseEntity<String> assignFitnessPlan(
            @RequestParam("userId") Long userId, 
            @RequestParam("fitnessPlanId") Long fitnessPlanId) {

        fitnessService.assignFitnessPlanToUser(userId, fitnessPlanId);
        return ResponseEntity.ok("Successfully assigned the plan!");
    }

    @GetMapping("/assignPlan")
    public ResponseEntity<Map<String, Object>> showAssignPlanForm() {
        List<ClientDTO> clients = userService.findAllClients();
        List<FitnessPlan> fitnessPlans = fitnessService.getAllPlans();

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("clients", clients);
        responseBody.put("fitnessPlans", fitnessPlans);

        return ResponseEntity.ok(responseBody); 
    }
    
    @GetMapping("/details/{id}")
    public ResponseEntity<ClientDTO> getUserDetails(@PathVariable("id") Long id) {
        ClientDTO clientDTO = userService.findUserById(id);

        if (clientDTO != null) {
            return ResponseEntity.ok(clientDTO); 
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
