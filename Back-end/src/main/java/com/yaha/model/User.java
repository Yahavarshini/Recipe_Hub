package com.yaha.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Entity
public class User {
	
	@Getter
	@Setter
	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Getter
	@Setter
	private String fullName;
	
	@Getter
	@Setter
	private String email;
	
	@Getter
	@Setter
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	

}
