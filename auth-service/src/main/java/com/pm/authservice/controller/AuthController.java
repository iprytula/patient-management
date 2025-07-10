package com.pm.authservice.controller;

import com.pm.authservice.dto.LoginRequestDTO;
import com.pm.authservice.dto.LoginResponseDTO;
import com.pm.authservice.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AuthController {
	private final AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@Operation(summary = "Generate JWT token on user login")
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO) {
		Optional<String> tokenOptional = authService.authenticate(loginRequestDTO);

		return tokenOptional.map(token ->
			ResponseEntity.ok(new LoginResponseDTO(token)))
			.orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());

	}

	@Operation(summary = "Validate JWT token")
	@PostMapping("/validate")
	public ResponseEntity<Void> validateToken(@RequestHeader("Authorization") String authorizationHeader) {
		if (!authorizationHeader.startsWith("Bearer ")) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

		return authService.validateToken(authorizationHeader.substring(7)) ?
			ResponseEntity.ok().build() :
			ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

}
