package com.erdal.controller;

import com.erdal.authResponse.AuthResponse;
import com.erdal.authResponse.ResponseMessage;
import com.erdal.dto.LoginRequest;
import com.erdal.dto.LoginResponse;
import com.erdal.dto.RegisterRequest;
import com.erdal.jwt.JwtService;
import com.erdal.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")

public class AuthController {

//	private static final Logger logger = LoggerFactory.getLogger(UserJwtController.class);
	// Bu classımda sadece login ve register işlemleri yapılacak

	@Autowired
	private AuthService authService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtService  jwtService;

	@PostMapping("/register")
	public ResponseEntity<AuthResponse> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {

		authService.register(registerRequest);

		AuthResponse response = new AuthResponse();
		response.setMessage(ResponseMessage.REGISTER_RESPONSE_MESSAGE);
		response.setSuccess(true);

		return new ResponseEntity<>(response, HttpStatus.CREATED);

	}

	// ------------------------login user-----------------------------
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> authenticate(@Validated @RequestBody LoginRequest loginRequest) {

		
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				loginRequest.getEmail(), loginRequest.getPassword());

		Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();// mevcut giriş yapan kullanıcıyı getirir

		String jwtToken = jwtService.generateToken(userDetails);

		LoginResponse loginResponse = new LoginResponse(jwtToken);

		return new ResponseEntity<>(loginResponse, HttpStatus.OK);

	}

}
