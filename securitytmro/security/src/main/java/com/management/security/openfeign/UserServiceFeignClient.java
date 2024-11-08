package com.management.security.openfeign;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.management.security.DTO.ClientDTO;
import com.management.security.DTO.FitnessPlanDTO;
import com.management.security.DTO.FitnessTrackingDTO;
import com.management.security.DTO.MembershipDTO;

@FeignClient(name = "gym", url = "http://localhost:8080")
public interface UserServiceFeignClient {

	@GetMapping("/user/getClient") 
	ResponseEntity<ClientDTO> getClientByUsername(@RequestParam("username") String username);

	@GetMapping("/registration/check") 
	boolean isUserRegistered(@RequestParam("username") String username);

	@GetMapping("/user/{id}")
	ResponseEntity<ClientDTO> getUserById(@PathVariable("id") Long id);

	@PostMapping("/register")
	String registerClient(@RequestBody ClientDTO clientDto, @PathVariable("id") Long id);

	@PostMapping("/register/save")
	public ResponseEntity<String> processRegister(@RequestParam("username") String username,
			@RequestParam("password") String encodedPassword, @RequestParam("mobile") String mobile,
			@RequestParam("age") int age, @RequestParam("weight") double weight, @RequestParam("height") double height,
			@RequestParam("membershipType") String membershipType);

	@GetMapping("/user/getplansById/{id}")
	ResponseEntity<Set<FitnessPlanDTO>> getFitnessPlans(@PathVariable("id") Long id);



	@PostMapping("/user/getLog")
	public ResponseEntity<String> logWorkout(@RequestParam("caloriesBurn") int caloriesBurn,
			@RequestParam("duration") int duration, @RequestParam("exerciseName") String exerciseName,
			@RequestParam("username") String username);

	@GetMapping("user/loghistory")
	public ResponseEntity<List<FitnessTrackingDTO>> viewWorkoutHistory(@RequestParam("username") String username);

	@DeleteMapping("/user/username/{username}")
	ResponseEntity<Void> deleteUser(@PathVariable("username") String username);

	@GetMapping("user/membership")
	public ResponseEntity<Map<String, Object>> viewMembership(@RequestParam("username") String username);

	@GetMapping("user/memberships") 
	ResponseEntity<List<MembershipDTO>> getAllMemberships();

	@GetMapping("user/userPlans")
	public ResponseEntity<List<FitnessPlanDTO>> getUserFitnessPlans(@RequestParam("username") String username);
	
	@GetMapping("user/details/{id}")
    public ResponseEntity<ClientDTO> getUserDetails(@PathVariable("id") Long id);
	
	  @PostMapping("/user/updateMembership")
	  ResponseEntity<String> updateMembership(@RequestParam("membershipId") Long membershipId, @RequestParam("username") String username);
	
	  @PutMapping("/user/details/update/{id}")
	  ResponseEntity<ClientDTO> updateUserDetails(@PathVariable("id") Long id,
	    		@RequestParam("username") String username,
		        @RequestParam("mobile") String mobile,
		        @RequestParam("age") int age,
		        @RequestParam("weight") double weight,
		        @RequestParam("height") double height,
		        @RequestParam("enabled") boolean enabled);
	  
	    @GetMapping("/getAllMems") 
	    ResponseEntity<List<MembershipDTO>> listMemberships();
	  

}

