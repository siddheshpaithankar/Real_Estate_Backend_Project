package com.example.dto;

public class AdminResponseDTO {
    private String name;

    public AdminResponseDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
