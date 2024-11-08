package com.management.gym.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.gym.DTO.MembershipDTO;
import com.management.gym.service.MembershipService;

@RestController
@RequestMapping("/admin/memberships")
public class MembershipController {

    @Autowired
    private MembershipService membershipService;

    @GetMapping("/getAllMems")
    public ResponseEntity<List<MembershipDTO>> listMemberships() {
        List<MembershipDTO> memberships = membershipService.getAllMemberships();
        return ResponseEntity.ok(memberships);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MembershipDTO> getMembershipById(@PathVariable("id") Long id) {
        MembershipDTO membership = membershipService.getMembershipById(id); 
        return membership != null ? ResponseEntity.ok(membership) : ResponseEntity.notFound().build(); 
    }

   
    @GetMapping("/showForm")
    public ResponseEntity<MembershipDTO> showCreateMembershipForm() {
        MembershipDTO membership = new MembershipDTO();
        return ResponseEntity.ok(membership);
    }

    @PostMapping("/add")
    public ResponseEntity<String> createMembership(@RequestBody MembershipDTO membershipDTO) {
        membershipService.saveMembership(membershipDTO); 
        return ResponseEntity.ok("Membership created successfully!");
    }

    @GetMapping("/edit/{id}")
    public ResponseEntity<MembershipDTO> showEditMembershipForm(@PathVariable("id") Long id) {
        MembershipDTO membership = membershipService.getMembershipById(id); 
        return membership != null ? ResponseEntity.ok(membership) : ResponseEntity.notFound().build(); 
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateMembership(@PathVariable("id") Long id, @RequestBody MembershipDTO membershipDTO) {
        MembershipDTO updatedMembership = membershipService.updateMembership(id, membershipDTO); 
        return updatedMembership != null ? ResponseEntity.ok("Membership updated successfully!") : ResponseEntity.notFound().build(); 
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMembership(@PathVariable("id")  Long id) {
        membershipService.deleteMembership(id);
        return ResponseEntity.ok("Membership deleted successfully!");
    }
}
