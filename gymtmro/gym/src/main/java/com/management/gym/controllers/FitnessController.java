package com.management.gym.controllers;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.gym.DTO.FitnessPlanDTO;
import com.management.gym.entity.FitnessPlan;
import com.management.gym.service.FitnessService;

@RestController
@RequestMapping("/admin/fitnessPlans")
public class FitnessController {

    @Autowired
    private FitnessService fitnessPlanService;

    @GetMapping("/getFitnessPlans")
    public ResponseEntity<List<FitnessPlanDTO>> getFitnessPlans() {
        List<FitnessPlan> plans = fitnessPlanService.getAllPlans();
        List<FitnessPlanDTO> planDTOs = plans.stream()
            .map(plan -> new FitnessPlanDTO(plan.getId(), plan.getPlanName(), plan.getDescription()))
            .collect(Collectors.toList());
        return ResponseEntity.ok(planDTOs);
    }
    

    @GetMapping("/add")
    public ResponseEntity<FitnessPlan> showAddForm() {
        FitnessPlan fitnessPlan = new FitnessPlan();
        return ResponseEntity.ok(fitnessPlan);
    }

    @PostMapping("/addPlan")
    public ResponseEntity<FitnessPlanDTO> addFitnessPlan(@RequestBody FitnessPlanDTO fitnessPlanDTO) {
        FitnessPlanDTO createdPlan = fitnessPlanService.createFitnessPlan(fitnessPlanDTO);
        return new ResponseEntity<>(createdPlan, HttpStatus.CREATED);
    }

    @GetMapping("/getById/{planId}")
    public ResponseEntity<FitnessPlan> getFitnessPlan(@PathVariable("planId") Long planId) {
        FitnessPlan fitnessPlan = fitnessPlanService.getPlanById(planId); 
        return ResponseEntity.ok(fitnessPlan);
    }

    @PutMapping("/updatePlan/{planId}")
    public ResponseEntity<FitnessPlanDTO> updateFitnessPlan(@PathVariable("planId") Long planId, @RequestBody FitnessPlanDTO updatedPlan) {
        FitnessPlanDTO updated = fitnessPlanService.updatePlan(planId, updatedPlan); 
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated); 
    }


    @DeleteMapping("/delete/{planId}")
    public ResponseEntity<String> deleteFitnessPlan(@PathVariable("planId") Long planId) {
        try {
            fitnessPlanService.deletePlan(planId);
            return ResponseEntity.ok("Fitness plan deleted successfully!");
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

}

