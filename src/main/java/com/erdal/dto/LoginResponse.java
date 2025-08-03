package com.erdal.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String token;
    private UserDTO user;
    
    public  LoginResponse() {}
    
    public  LoginResponse( String token) {
    this.token=token;
    }
    public  LoginResponse( String token,UserDTO user) {
    	this.token=token;
    	this.user=user;
    	
    }
    
    
   
    
    	
    
}
