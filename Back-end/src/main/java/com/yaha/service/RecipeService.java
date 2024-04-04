package com.yaha.service;

import java.util.List;

import com.yaha.model.Recipe;
import com.yaha.model.User;

public interface RecipeService {
	
	public Recipe createRecipe(Recipe recipe , User user);
	
	public Recipe findRecipeById(Long id) throws Exception;
	
	public String deleteRecipe(Long id) throws Exception;
	
	public Recipe updateRecipe(Recipe recipe , Long id) throws Exception;
	
	public List<Recipe> findAllRecipe();
	
	public Recipe likeRecipe(Long recipeId, User user) throws Exception;
	
	
	
	

}
