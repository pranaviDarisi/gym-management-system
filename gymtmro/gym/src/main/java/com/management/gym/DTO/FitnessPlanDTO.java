package com.management.gym.DTO;

import java.util.HashSet;
import java.util.Set;

public class FitnessPlanDTO {
    private Long id;
    private String planName;
    private String description;
    public Set<ClientDTO> getClients() {
		return clients;
	}
	public void setClients(Set<ClientDTO> clients) {
		this.clients = clients;
	}
	private Set<ClientDTO> clients = new HashSet<>(); // List of user IDs associated with this fitness plan
    
    public FitnessPlanDTO(Long id, String planName, String description) {
        this.id = id;
        this.planName = planName;
        this.description = description;
    }
    public FitnessPlanDTO() {
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
	@Override
	public String toString() {
		return "planName=" + planName + ", description=" + description ;
	}

//	public Set<ClientDTO> getClients() {
//		return clients;
//	}
//
//	public void setClients(Set<ClientDTO> clients) {
//		this.clients = clients;
//	}


}

