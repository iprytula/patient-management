package com.pm.authservice.service;

import com.pm.authservice.dto.LoginRequestDTO;
import com.pm.authservice.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

	private final UserService userService;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtils;

	public AuthServiceImpl(UserService userService, PasswordEncoder passwordEncoder, JwtUtil jwtUtils) {
		this.jwtUtils = jwtUtils;
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Optional<String> authenticate(LoginRequestDTO loginRequestDTO) {
		return userService
			.findByEmail(loginRequestDTO.getEmail())
			.filter(u -> passwordEncoder.matches(loginRequestDTO.getPassword(), u.getPassword()))
			.map(u -> jwtUtils.generateToken(u.getEmail(), u.getRole()));
	}

	@Override
	public boolean validateToken(String token) {
		try {
			jwtUtils.validateToken(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
