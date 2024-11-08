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

import com.management.security.DTO.MembershipDTO;

@FeignClient(name = "gym", url = "http://localhost:8080") // Corrected URL
public interface MembershipServiceFeignClient {

    @PostMapping("/admin/memberships/add") // Keep admin/memberships here
    ResponseEntity<String> createMembership(@RequestBody MembershipDTO membershipDTO); // Change return type to String for success message

    @GetMapping("/admin/memberships/{id}") // Keep admin/memberships here
    ResponseEntity<MembershipDTO> getMembershipById(@PathVariable("id") Long id);

    @GetMapping("/admin/memberships/getAllMems") // Keep admin/memberships here
    ResponseEntity<List<MembershipDTO>> listMemberships();

    @PutMapping("/admin/memberships/update/{id}") // Keep admin/memberships here
    ResponseEntity<String> updateMembership(@PathVariable("id") Long id, @RequestBody MembershipDTO membershipDTO); // Change return type to String for success message

    @DeleteMapping("/admin/memberships/delete/{id}") // Keep admin/memberships here
    ResponseEntity<String> deleteMembership(@PathVariable("id") Long id); // Change return type to String for success message
}
