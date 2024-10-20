package com.saprone.food_recipe_generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@SpringBootApplication
@RestController
public class FoodRecipeGeneratorApplication {

	@Autowired
	private RecipeRepository recipeRepository;

	@PostMapping("/recipe")
	public Recipe addRecipe(@RequestBody Recipe recipe) {
		return recipeRepository.save(recipe);
	}

	@GetMapping("/recipes")
	public List<Recipe> getAllRecipes() {
		return recipeRepository.findAll();
	}

	public static void main(String[] args) {
		SpringApplication.run(FoodRecipeGeneratorApplication.class, args);
	}
}
