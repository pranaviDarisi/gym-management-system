package com.management.security.secure;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.management.security.DTO.AuthorityDTO;
import com.management.security.DTO.ClientDTO;

public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    private Set<GrantedAuthority> authorities;
    private boolean enabled;

    // Constructor for ClientDTO
    public CustomUserDetails(ClientDTO client) {
        this.username = client.getUsername();
        this.password = client.getPassword();
        this.authorities = client.getAuthorities().stream()
                                 .map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
                                 .collect(Collectors.toSet());
        this.enabled = client.isEnabled();
    }

    // Full fields constructor
    public CustomUserDetails(String username, String password, Set<AuthorityDTO> authorityDTOs, boolean enabled) {
        this.username = username;
        this.password = password;
        this.authorities = authorityDTOs.stream()
                                        .map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
                                        .collect(Collectors.toSet());
        this.enabled = enabled;
    }

    // Implementing methods from UserDetails interface
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Customize this logic if needed
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Customize this logic if needed
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Customize this logic if needed
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    // Getters and Setters for additional fields
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthorities(Set<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
