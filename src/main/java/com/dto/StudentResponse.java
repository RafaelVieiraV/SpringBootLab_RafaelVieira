package com.dto;

public class StudentResponse {

    private Long id;
    private String fullName; // Cambiado de 'name' a 'fullName' para ser consistentes
    private String email;

    // Constructor vacío
    public StudentResponse() {}

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}