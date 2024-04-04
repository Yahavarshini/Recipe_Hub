package com.yaha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yaha.model.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe,Long>{
	

}
