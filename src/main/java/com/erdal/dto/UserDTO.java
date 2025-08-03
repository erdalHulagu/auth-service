package com.erdal.dto;


import java.util.Set;

import lombok.Data;

@Data
public class UserDTO {
	
    private Long id;
    private String userName;
    private String fullName;
    private String password;
    private String email;
    private String phone;
    private Set<String> roles;
}