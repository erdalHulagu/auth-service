package com.erdal.service;

import com.erdal.config.SecurityUtils;
import com.erdal.dto.*;
import com.erdal.exeptions.AuthBadRequestException;
import com.erdal.exeptions.AuthNotFoundException;
import com.erdal.exeptions.ErrorMessages;
import com.erdal.jwt.JwtServiceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserServiceClient userServiceClient;
    private final PasswordEncoder passwordEncoder;
    private final JwtServiceImpl jwtService;

    @Override
    public LoginResponse register(RegisterRequest request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        UserDTO newUser = new UserDTO();
        newUser.setFullName(request.getFullName());
        newUser.setUserName(request.getUserName());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(encodedPassword);
        newUser.setPhone(request.getPhone());

        UserDTO savedUser = userServiceClient.createUser(newUser);
        if (savedUser == null) {
            throw new AuthBadRequestException(ErrorMessages.REGISTER_FAILED);
        }

        String token = jwtService.generateToken(savedUser.getUserName());
        return new LoginResponse(token, savedUser);
    }

    @Override
    public LoginResponse authenticate(LoginRequest request) {
        UserDTO existingUser = userServiceClient.getUserByUsername(request.getUserName());
        if (existingUser == null) {
            throw new AuthNotFoundException(ErrorMessages.USER_NOT_FOUND);
        }

        if (!passwordEncoder.matches(request.getPassword(), existingUser.getPassword())) {
            throw new AuthBadRequestException(ErrorMessages.INVALID_CREDENTIALS);
        }

        String token = jwtService.generateToken(existingUser.getUserName());
        return new LoginResponse(token, existingUser);
    }
 // ------------------ get current user ------------------------
 	public UserDTO getCurrentUser(LoginRequest request) {

 		String email = SecurityUtils.getCurrentUserLogin()
 				.orElseThrow(() -> new AuthNotFoundException(ErrorMessages.PRINCIPAL_FOUND_MESSAGE));
 		UserDTO existingUser = userServiceClient.getUserByUsername(request.getUserName());
        if (existingUser == null) {
            throw new AuthNotFoundException(ErrorMessages.USER_NOT_FOUND);
        }

 		return existingUser;

 	}
 	
}
