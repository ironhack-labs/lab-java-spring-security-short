package com.example.demo.dto;


import com.example.demo.entity.AuthorRole;

public class AuthorUpdateRequest {

    private String name;

    private String email;

    private String password;
    private AuthorRole role;

    public String getName() {
        return name;
    }

    public AuthorRole getRole() {
        return role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
