package com.erdal.service;

import com.erdal.dto.LoginRequest;
import com.erdal.dto.LoginResponse;
import com.erdal.dto.RegisterRequest;

public interface AuthService {
    LoginResponse register(RegisterRequest request);
    LoginResponse authenticate(LoginRequest request);
}
