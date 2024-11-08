package com.management.security.DTO;

import java.util.HashSet;
import java.util.Set;

public class ClientDTO {

	private Long id;
	private String username;
	private String password;
	private String mobile;
	private int age;
	private double weight;
	private double height;
	private boolean enabled;
	private Set<AuthorityDTO> authorities = new HashSet<>(); // Set of Authority DTO objects
	private MembershipDTO membership; // Membership DTO object
	private Set<FitnessPlanDTO> fitnessPlans = new HashSet<>(); // Set of FitnessPlan DTO objects
	private Set<FitnessTrackingDTO> fitnessTrackings = new HashSet<>(); // Set of FitnessTracking DTO objects

	// Empty constructor
	public ClientDTO() {
	}

	// Full fields constructor
	public ClientDTO(Long id, String username, String password, String mobile, int age, double weight, double height,
			boolean enabled) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.mobile = mobile;
		this.age = age;
		this.weight = weight;
		this.height = height;
		this.enabled = enabled;
	}

	public ClientDTO(Long id, String username, String password, String mobile, int age, double weight, double height,
			boolean enabled, Set<AuthorityDTO> authorities, MembershipDTO membership, Set<FitnessPlanDTO> fitnessPlans,
			Set<FitnessTrackingDTO> fitnessTrackings) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.mobile = mobile;
		this.age = age;
		this.weight = weight;
		this.height = height;
		this.enabled = enabled;
		this.authorities = authorities;
		this.membership = membership;
		this.fitnessPlans = fitnessPlans;
		this.fitnessTrackings = fitnessTrackings;
	}

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<AuthorityDTO> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<AuthorityDTO> authorities) {
		this.authorities = authorities;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public MembershipDTO getMembership() {
		return membership;
	}

	public void setMembership(MembershipDTO membership) {
		this.membership = membership;
	}

	public Set<FitnessPlanDTO> getFitnessPlans() {
		return fitnessPlans;
	}

	public void setFitnessPlans(Set<FitnessPlanDTO> fitnessPlans) {
		this.fitnessPlans = fitnessPlans;
	}

	public Set<FitnessTrackingDTO> getFitnessTrackings() {
		return fitnessTrackings;
	}

	public void setFitnessTrackings(Set<FitnessTrackingDTO> fitnessTrackings) {
		this.fitnessTrackings = fitnessTrackings;
	}

}
