package com.yaha.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Recipe {
	
	@Getter @Setter
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Getter @Setter
	private String title;
	
	@Getter @Setter
	@ManyToOne
	private User user;
	
	@Getter @Setter
	private String image;
	
	@Getter @Setter
	private String description;
	
	
	@Getter @Setter
	private boolean vegetarian;
	
	@Getter @Setter
	private LocalDateTime createdAt;
	
	@Getter @Setter
	private List<Long> likes = new ArrayList<>();

}
