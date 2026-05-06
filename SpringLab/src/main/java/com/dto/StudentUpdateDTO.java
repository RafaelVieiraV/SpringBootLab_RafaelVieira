package com.dto;

import jakarta.validation.constraints.*;

public class StudentUpdateDTO {
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 120)
    private String fullName;

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
}