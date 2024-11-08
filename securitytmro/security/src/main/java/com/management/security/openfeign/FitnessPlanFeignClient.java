package com.management.security.openfeign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.management.security.DTO.FitnessPlanDTO;

@FeignClient(name = "gym", url = "http://localhost:8080/admin/fitnessPlans")
public interface FitnessPlanFeignClient {

    @GetMapping("/getFitnessPlans")
    ResponseEntity<List<FitnessPlanDTO>> getAllFitnessPlans();

    @PostMapping("/addPlan")
    ResponseEntity<FitnessPlanDTO> addFitnessPlan(@RequestBody FitnessPlanDTO fitnessPlanDTO);

    @GetMapping("/getById/{planId}")
    ResponseEntity<FitnessPlanDTO> getFitnessPlan(@PathVariable("planId") Long planId);

    @PutMapping("/updatePlan/{planId}")
    ResponseEntity<FitnessPlanDTO> updateFitnessPlan(@PathVariable("planId") Long planId, @RequestBody FitnessPlanDTO updatedPlanDTO);

    @DeleteMapping("/delete/{planId}")
    public ResponseEntity<String> deleteFitnessPlan(@PathVariable("planId") Long planId);
}


