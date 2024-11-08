package com.management.gym.DTO;

import java.util.List;

public class MembershipDTO {
    private Long id;                                // Unique identifier for the membership
    private String membershipType;                  // Type of membership (e.g., monthly, yearly)
    private double fee;                             // Cost associated with the membership
    private boolean status;                         // Indicates if the membership is active/inactive
    private List<ClientDTO> clients;               // List of clients associated with this membership

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<ClientDTO> getClients() {
        return clients;
    }

    public void setClients(List<ClientDTO> clients) {
        this.clients = clients;
    }
}
