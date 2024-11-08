package com.management.gym.DTO;

public class AuthorityDTO {
    private Long id;

    private String authority; // e.g., "ROLE_USER", "ROLE_ADMIN"

    // Getters and Setters
    // Default constructor
    public AuthorityDTO(Long id, String authority) {
    	this.authority = authority;
    	this.id=id;
    }

    // Getters and Setters
    public AuthorityDTO(String authority) {
		super();
		this.authority = authority;
	}

    public AuthorityDTO() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
    @Override
    public String toString() {
        return "AuthorityDTO{" +
                "authority='" + authority + '\'' +
                '}';
    }
}

