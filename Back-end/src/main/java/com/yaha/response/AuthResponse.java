package com.yaha.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
	
	@Getter @Setter
	private String jwt;
	@Getter @Setter
	private String message;

}
