package com.mcjag.daytoday.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mcjag.daytoday.tables.User;
import com.mcjag.daytoday.payload.request.LoginRequest;
import com.mcjag.daytoday.payload.request.SignupRequest;
import com.mcjag.daytoday.payload.response.JwtResponse;
import com.mcjag.daytoday.payload.response.MessageResponse;
import com.mcjag.daytoday.providers.UserProvider;
import com.mcjag.daytoday.security.jwt.JwtUtils;
import com.mcjag.daytoday.security.services.UserDetailsImpl;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserProvider userProvider;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		

		return ResponseEntity.ok(new JwtResponse(jwt, 
                                                 userDetails.getFirstName(),
                                                 userDetails.getLastName(),
												 userDetails.getEmail()
												 ));
	}

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
/*
		if (userProvider.findUserByEmail(signUpRequest.getEmail())== null) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
*/
		// Create new user's account
        User user = new User(signUpRequest.getEmail(),
                             signUpRequest.getFirstName(),
                             signUpRequest.getLastName(),
							 encoder.encode(signUpRequest.getPassword()));


		userProvider.addUser(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}