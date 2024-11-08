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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.management.security.DTO.ClientDTO;
import com.management.security.DTO.MembershipDTO;
import com.management.security.openfeign.MembershipServiceFeignClient;
import com.management.security.openfeign.UserServiceFeignClient;

import jakarta.servlet.http.HttpSession;

@Controller 
public class securityCon {

    @Autowired
    private UserServiceFeignClient userServiceFeignClient; 

    @Autowired
    private MembershipServiceFeignClient membershipServiceFeignClient;
   
    
	@Autowired
	private PasswordEncoder passwordEncoder;

    @GetMapping("/home")
    public String home(Principal principal) {
        if (principal == null) {
           return "redirect:/login";
        } else {
           return "home";
        }
    }
    
    @GetMapping("/deniedAccess")
    public String access() {
        return "denied-access"; 
    }

    @GetMapping("/login")
    public String login() {
        return "loginPage"; 
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/gym/login"; 
    }


    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
    	ResponseEntity<List<MembershipDTO>>  list = membershipServiceFeignClient.listMemberships(); 
    	 List<MembershipDTO> memberships = list.getBody();
    	model.addAttribute("memberships", memberships); 
        return "userRegister";
    }


@PostMapping("register/save")
public String ClientRegister(
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
        return "userRegister"; 
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

    

    ResponseEntity<String> responseEntity = userServiceFeignClient.processRegister(username,encodedPassword, mobile, age, weight, height, membershipType);

    if (responseEntity.getStatusCode().is2xxSuccessful()) {
        model.addAttribute("username", username);
        return "redirect:/login"; 
    } else {
        model.addAttribute("error", responseEntity.getBody());
        return "userRegister"; 
    }
}

//@DeleteMapping("/delete")
//public String delete(Principal principal) {
//	userServiceFeignClient.deleteUser(principal.getName()); 
//  return "redirect:/login"; 
//}

//@GetMapping("/updatePassword")
//public String showUpdatePasswordPage() {
//  return "updatePassword"; 
//}

}

