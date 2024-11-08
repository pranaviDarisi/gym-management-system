package com.management.security.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.management.security.DTO.FitnessPlanDTO;
import com.management.security.openfeign.FitnessPlanFeignClient;

@Controller
public class FitnessPlanFeignController {

    @Autowired
    private FitnessPlanFeignClient fitnessPlanFeignClient;

    @GetMapping("/admin/fitnessPlans/getFitnessPlans")
    public String getAllFitnessPlans(Model model) {
        ResponseEntity<List<FitnessPlanDTO>> response = fitnessPlanFeignClient.getAllFitnessPlans();
        List<FitnessPlanDTO> fitnessPlans = response.getBody();
        
        model.addAttribute("fitnessPlans", fitnessPlans); 
        return "fitnessplan-display"; 
    }

    @GetMapping("/admin/fitnessPlans/add")
    public String showAddForm(Model model) {
        model.addAttribute("fitnessPlan", new FitnessPlanDTO());
        return "add-fitnessplan"; 
    }

    @PostMapping("/admin/fitnessPlans/addPlan")
    public String assignFitnessPlan(@ModelAttribute FitnessPlanDTO fitnessPlanDTO, Model model) {
        fitnessPlanFeignClient.addFitnessPlan(fitnessPlanDTO); 
        model.addAttribute("message", "Fitness plan assigned successfully!");
        return "redirect:/admin/fitnessPlans/getFitnessPlans";
    }

    @GetMapping("/admin/fitnessPlans/update/{planId}")
    public String getUpdateFitnessPlan(@PathVariable("planId") Long planId, Model model) {
        ResponseEntity<FitnessPlanDTO> response = fitnessPlanFeignClient.getFitnessPlan(planId);
        FitnessPlanDTO fitnessPlanDTO = response.getBody();
        
        model.addAttribute("fitnessPlan", fitnessPlanDTO);
        return "update-fitnessplan"; 
    }

    @PostMapping("/admin/fitnessPlans/update/{planId}")
    public String updateFitnessPlan(@PathVariable("planId") Long planId, @ModelAttribute FitnessPlanDTO updatedPlanDTO) {
        fitnessPlanFeignClient.updateFitnessPlan(planId, updatedPlanDTO); 
        return "redirect:/admin/fitnessPlans/getFitnessPlans";
    }

    @GetMapping("/admin/fitnessPlans/delete/{planId}")
    public String deleteFitnessPlan(@PathVariable("planId") Long planId, Model model) {
        fitnessPlanFeignClient.deleteFitnessPlan(planId); 
        return "redirect:/admin/fitnessPlans/getFitnessPlans"; 
    }
}

