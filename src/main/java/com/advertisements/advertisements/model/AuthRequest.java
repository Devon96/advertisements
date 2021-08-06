package com.advertisements.advertisements.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * class for the authentication requests
 */
@Data
public class AuthRequest {

    private String email;

    private String password;

}