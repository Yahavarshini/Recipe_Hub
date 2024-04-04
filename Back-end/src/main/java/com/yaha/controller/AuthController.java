package com.yaha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yaha.config.JwtProvider;
import com.yaha.model.User;
import com.yaha.repository.UserRepository;
import com.yaha.request.LoginRequest;
import com.yaha.response.AuthResponse;
import com.yaha.service.CustomeUserDetailsService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CustomeUserDetailsService customeUserDetails;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@PostMapping("/signup")
	public AuthResponse createUser(@RequestBody User user) throws Exception{
		
		String email = user.getEmail();
		String password = user.getPassword();
		String fullName = user.getFullName();
		
		User isExistEmail = userRepository.findByEmail(email);
		
		if(isExistEmail != null) {
			throw new Exception("Email is already used with another account");
		}
		
		User createdUser = new User();
		createdUser.setFullName(fullName);
		createdUser.setEmail(email);
		createdUser.setPassword(passwordEncoder.encode(password));
		
//		User savedUser = userRepository.save(createdUser);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(email,password);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtProvider.generateToken(authentication);
		
	    AuthResponse res = new AuthResponse();
	    
		res.setJwt(token);
		res.setMessage("Signup success");
		
		return res;
	}

	@PostMapping("/signin")
	public AuthResponse signinHandler(@RequestBody LoginRequest loginRequest) {
		String username = loginRequest.getEmail();
		String password = loginRequest.getPassword();
		
		Authentication authentication = authenticate(username,password);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
	    String token = jwtProvider.generateToken(authentication);
		
	    AuthResponse res = new AuthResponse();
	    
		res.setJwt(token);
		res.setMessage("Signin success");
		
		return res;
		
	}
	
    
    public  Authentication authenticate(String username, String password) {
		
		UserDetails userDetails = customeUserDetails.loadUserByUsername(username);
		
		if(userDetails == null) {
			throw new BadCredentialsException("User not found");
		}
		
		if(!passwordEncoder.matches(password,userDetails.getPassword())) {
			throw new BadCredentialsException("Invalid Password");
		}
		
		
		return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
	}
}
