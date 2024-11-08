package com.management.security.openfeign;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.management.security.DTO.ClientDTO;

@FeignClient(name = "gym", url = "http://localhost:8080/admin") // Make sure the port matches your gym service
public interface AdminFeignClient {
	// Get all users
	@GetMapping("/getusers")
    public ResponseEntity<List<ClientDTO>> getAllUsers();

	@PostMapping("/savePlan")
	public ResponseEntity<String> assignFitnessPlan(@RequestParam("userId") Long userId,
			@RequestParam("fitnessPlanId") Long fitnessPlanId);

	@GetMapping("/assignPlan")
	ResponseEntity<Map<String, Object>> getClientsAndFitnessPlans();
	
	@GetMapping("/details/{id}")
    public ResponseEntity<ClientDTO> getUserDetails(@PathVariable("id") Long id);
	@PostMapping("/register")
	String registerClient(@RequestBody ClientDTO clientDto, @PathVariable("id") Long id);

	@PostMapping("/register/save")
	public ResponseEntity<String> adminProcessRegister(@RequestParam("username") String username,
			@RequestParam("password") String encodedPassword, @RequestParam("mobile") String mobile,
			@RequestParam("age") int age, @RequestParam("weight") double weight, @RequestParam("height") double height,
			@RequestParam("membershipType") String membershipType);
}
