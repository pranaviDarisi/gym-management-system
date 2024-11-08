package com.management.gym.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "authorities")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
   // @ManyToMany(mappedBy = "authorities")
    private String authority; // e.g., "ROLE_USER", "ROLE_ADMIN"
    
    public Authority() {
    }

    public Authority(String authority) {
		super();
		this.authority = authority;
	}

    public Authority(Long id2, String authority2) {
    	
    	this.authority = authority2;
    	this.id=id2;
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
}
