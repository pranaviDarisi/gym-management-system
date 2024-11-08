package com.management.gym.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.management.gym.DTO.MembershipDTO;
import com.management.gym.entity.Client;
import com.management.gym.entity.Membership;
import com.management.gym.repo.ClientRepo;
import com.management.gym.repo.MembershipRepo;

@Service
public class MembershipService {

    @Autowired
    private MembershipRepo repo;
    @Autowired
    private ClientRepo userRepo;

    // Method to convert MembershipDTO to Membership entity
    private Membership convertToEntity(MembershipDTO membershipDTO) {
        Membership membership = new Membership();
        membership.setId(membershipDTO.getId());
        membership.setMembershipType(membershipDTO.getMembershipType());
        membership.setFee(membershipDTO.getFee());
        membership.setStatus(membershipDTO.isStatus());
        return membership;
    }

    // Method to convert Membership entity to MembershipDTO
    private MembershipDTO convertToDTO(Membership membership) {
        MembershipDTO membershipDTO = new MembershipDTO();
        membershipDTO.setId(membership.getId());
        membershipDTO.setMembershipType(membership.getMembershipType());
        membershipDTO.setFee(membership.getFee());
        membershipDTO.setStatus(membership.isStatus());
        return membershipDTO;
    }

    public MembershipDTO saveMembership(MembershipDTO membershipDTO) {
        Membership membership = convertToEntity(membershipDTO);
        Membership savedMembership = repo.save(membership);
        return convertToDTO(savedMembership);
    }

    public List<MembershipDTO> getAllMemberships() {
        return repo.findAll().stream()
                   .map(this::convertToDTO)
                   .collect(Collectors.toList());
    }
    
    public MembershipDTO getMembershipById(Long id) {
        Membership membership = repo.findById(id).orElse(null);
        return membership != null ? convertToDTO(membership) : null;
    }
    
    public MembershipDTO getMembershipByUsername(String username) {
        Optional<Client> userOptional = userRepo.findByUsername(username);
        if (userOptional.isPresent()) {
            Client user = userOptional.get();
            Membership membership = user.getMembership();
            return membership != null ? convertToDTO(membership) : null;
        }
        return null; 
    }


    public MembershipDTO updateMembership(Long id, MembershipDTO updatedMembershipDTO) {
        Membership existingMembership = repo.findById(id).orElse(null);
        if (existingMembership != null) {
            Membership updatedMembership = convertToEntity(updatedMembershipDTO);
            updatedMembership.setId(existingMembership.getId());
            Membership savedMembership = repo.save(updatedMembership);
            return convertToDTO(savedMembership);
        }
        return null;
    }

    public void deleteMembership(Long membershipId) {
        Membership membership = repo.findById(membershipId).get();

        for (Client client : membership.getClients()) {
            client.setMembership(null);
            userRepo.save(client);
        }

        repo.delete(membership);
    }
    
    public boolean updateMembership(String username, Long membershipId) {
    	
        Client user = userRepo.findByUsername(username).get();
        Membership membership = repo.findById(membershipId).orElse(null);
        if (membership == null) {
            return false; // Membership not found
        }
        // Update the user's membership
        user.setMembership(membership);
        userRepo.save(user);

        return true; 
    }
}
