package com.management.security.DTO;


public class AuthorityDTO {
    private Long id;

    private String authority; // e.g., "ROLE_USER", "ROLE_ADMIN"

    // Getters and Setters
    // Default constructor
    public AuthorityDTO() {
    }

    // Getters and Setters
    public AuthorityDTO(String authority) {
		super();
		this.authority = authority;
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
        return  authority;
    }
}



