//package com.management.gym.controllers;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.management.gym.DTO.MembershipDTO;
//import com.management.gym.service.MembershipService;
//
//import jakarta.servlet.http.HttpSession;
//
//@RestController
//public class SecurityController {
//
//	 @Autowired
//	    private MembershipService membershipService;
//
//
//    @GetMapping("/updatePassword")
//    public ResponseEntity<String> showUpdatePasswordPage() {
//        return ResponseEntity.ok("Update password page"); // Indicate that the update password page can be accessed
//    }
//
//
//
//    @GetMapping("/logout")
//    public ResponseEntity<String> logout(HttpSession session) {
//        session.invalidate();
//        return ResponseEntity.ok("Logged out successfully.");
//    }
//    
//    @GetMapping("/getAllMems")
//    public ResponseEntity<List<MembershipDTO>> listMemberships() {
//        List<MembershipDTO> memberships = membershipService.getAllMemberships();
//        return ResponseEntity.ok(memberships);
//    }
//
//}
