package com.saprone.food_recipe_generator.repository;

import com.saprone.food_recipe_generator.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
