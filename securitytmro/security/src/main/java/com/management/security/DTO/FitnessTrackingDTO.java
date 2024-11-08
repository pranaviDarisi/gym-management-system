package com.management.security.DTO;


import java.time.LocalDate;

public class FitnessTrackingDTO {
    private Long id;  // Optional, if you want to return the ID after saving
    private int caloriesBurn; 
    private LocalDate date; 
    private int duration; 
    private String exerciseName; 
    private String clientUsername; // Assuming you want to track by username

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCaloriesBurn() {
        return caloriesBurn;
    }

    public void setCaloriesBurn(int caloriesBurn) {
        this.caloriesBurn = caloriesBurn;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getClientUsername() {
        return clientUsername;
    }

    public void setClientUsername(String clientUsername) {
        this.clientUsername = clientUsername;
    }
}

