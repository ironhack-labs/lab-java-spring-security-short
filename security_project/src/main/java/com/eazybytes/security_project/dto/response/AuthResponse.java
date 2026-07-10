package com.eazybytes.security_project.dto.response;

import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private String email;
    private String role;


}
