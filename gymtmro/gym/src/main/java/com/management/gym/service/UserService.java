package com.management.gym.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.gym.DTO.AuthorityDTO;
import com.management.gym.DTO.ClientDTO;
import com.management.gym.DTO.MembershipDTO;
import com.management.gym.entity.Authority;
import com.management.gym.entity.Client;
import com.management.gym.entity.Membership;
import com.management.gym.repo.AuthorityRepo;
import com.management.gym.repo.ClientRepo;
import com.management.gym.repo.MembershipRepo;

@Service
public class UserService {
    @Autowired
    private ClientRepo userRepository; 
    @Autowired
    private AuthorityRepo authRepo;
    @Autowired
    private MembershipRepo membershipRepo;
    
    public Authority getAuthorityByAuthority(String authorityName) {
        Optional<Authority> authority = authRepo.findByAuthority(authorityName);
        return authority.orElse(null); // Return null if not found, or handle it as needed
    }


    public List<ClientDTO> findAllClients() {
        return userRepository.findAll().stream()
                .map(this::convertToClientDTO)
                .collect(Collectors.toList());
    }

    public ClientDTO findUserById(Long id) {
        Optional<Client> userOptional = userRepository.findById(id);
        return userOptional.map(this::convertToClientDTO).orElse(null); // Convert to ClientDTO
    }


    public ClientDTO findByUsername(String username) {
        Optional<Client> userOptional = userRepository.findByUsernameWithAuthorities(username);
        if (userOptional.isEmpty()) {
            return null; 
        }
        return convertToClientDTO(userOptional.get());
    }


    public void saveUser(Client client2) {
        userRepository.save(client2);
    }

	 public boolean checkIfRegistered(String username) {
	        return userRepository.existsByUsername(username);
	    }
	 private ClientDTO convertToClientDTO(Client client) {
		    ClientDTO clientDTO = new ClientDTO();
		    clientDTO.setId(client.getId());
		    clientDTO.setUsername(client.getUsername());
		    clientDTO.setPassword(client.getPassword());
		    clientDTO.setMobile(client.getMobile());
		    clientDTO.setAge(client.getAge());
		    clientDTO.setWeight(client.getWeight());
		    clientDTO.setHeight(client.getHeight());
		    clientDTO.setEnabled(client.isEnabled());
		    
		    Set<AuthorityDTO> authorities = client.getAuthorities().stream()
		            .map(authority -> new AuthorityDTO(authority.getId(), authority.getAuthority()))
		            .collect(Collectors.toSet());
		    clientDTO.setAuthorities(authorities);
		    
		    if (client.getMembership() != null) {
		        MembershipDTO membershipDTO = new MembershipDTO(); // Create MembershipDTO instance
		        membershipDTO.setId(client.getMembership().getId());
		        membershipDTO.setMembershipType(client.getMembership().getMembershipType());
		        membershipDTO.setFee(client.getMembership().getFee());
		        clientDTO.setMembership(membershipDTO);
		    }
		    
		    return clientDTO;
		}
	 
	 public ClientDTO updateUser(Long id,ClientDTO client) {
	        // Save the updated user back to the database
		 Client convertToEntity = convertToEntity(client);
	        Client updatedUser = userRepository.save(convertToEntity);

	        // Convert the updated User entity back to ClientDTO and return
	        return convertToClientDTO(updatedUser);
	    }
	 
	 public Membership convertToMembership(MembershipDTO membershipDTO) {
		    Membership membership = new Membership();
		    membership.setId(membershipDTO.getId());
		    membership.setMembershipType(membershipDTO.getMembershipType());
		    membership.setFee(membershipDTO.getFee());
		    membership.setStatus(membershipDTO.isStatus());
		    return membership;
		}


private Client convertToEntity(ClientDTO clientDTO) {
    Client client = new Client();
    client.setId(clientDTO.getId());
    client.setUsername(clientDTO.getUsername());
    client.setPassword(clientDTO.getPassword());
    client.setMobile(clientDTO.getMobile());
    client.setAge(clientDTO.getAge());
    client.setWeight(clientDTO.getWeight());
    client.setHeight(clientDTO.getHeight());
    client.setEnabled(clientDTO.isEnabled());
    
    // Handle authorities conversion if necessary
    if (clientDTO.getAuthorities() != null) {
        Set<Authority> authorities = clientDTO.getAuthorities().stream()
                .map(authDTO -> new Authority(authDTO.getId(), authDTO.getAuthority())) // Assuming Authority has an appropriate constructor
                .collect(Collectors.toSet());
        client.setAuthorities(authorities);
    }
    
    // Handle membership conversion if necessary
    if (clientDTO.getMembership() != null) {
        Membership membership = new Membership(); // Create Membership instance
        membership.setId(clientDTO.getMembership().getId());
        membership.setMembershipType(clientDTO.getMembership().getMembershipType());
        membership.setFee(clientDTO.getMembership().getFee());
        client.setMembership(membership);
    }
    
    return client;
}



}
