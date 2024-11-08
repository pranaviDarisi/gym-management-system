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
import org.springframework.web.bind.annotation.RequestMapping;

import com.management.security.DTO.MembershipDTO;
import com.management.security.openfeign.MembershipServiceFeignClient;

@Controller
@RequestMapping("/admin/memberships")
public class MembershipFeignController {

    @Autowired
    private MembershipServiceFeignClient membershipServiceFeignClient;

    @GetMapping("/getAllMems")
    public String listMemberships(Model model) {
        ResponseEntity<List<MembershipDTO>> response = membershipServiceFeignClient.listMemberships();
        List<MembershipDTO> memberships = response.getBody();
        
        model.addAttribute("memberships", memberships);
        return "membership-display"; 
    }

    @GetMapping("/showForm")
    public String showCreateMembershipForm(Model model) {
        model.addAttribute("membership", new MembershipDTO());
        return "add-membership"; 
    }

    @PostMapping("/addmembership")
    public String createMembership(@ModelAttribute MembershipDTO membershipDTO) {
        membershipServiceFeignClient.createMembership(membershipDTO); 
        return "redirect:/admin/memberships/getAllMems"; 
    }

    @GetMapping("/edit/{id}")
    public String showEditMembershipForm(@PathVariable("id") Long id, Model model) {
        ResponseEntity<MembershipDTO> response = membershipServiceFeignClient.getMembershipById(id);
        MembershipDTO membershipDTO = response.getBody();
        
        if (membershipDTO != null) {
            model.addAttribute("membership", membershipDTO);
            return "update-membership"; 
        }
        
        return "redirect:/admin/memberships?error=MembershipNotFound";
    }

    @PostMapping("/update/{id}")
    public String updateMembership(@PathVariable("id") Long id, @ModelAttribute MembershipDTO membershipDTO) {
        membershipServiceFeignClient.updateMembership(id, membershipDTO);
        return "redirect:/admin/memberships/getAllMems"; 
    }

    // Delete membership
    @GetMapping("/delete/{id}")
    public String deleteMembership(@PathVariable("id") Long id) {
        membershipServiceFeignClient.deleteMembership(id);
        return "redirect:/admin/memberships/getAllMems";
    }
}
