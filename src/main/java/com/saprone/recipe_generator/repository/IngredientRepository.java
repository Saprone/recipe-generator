package com.saprone.recipe_generator.repository;

import com.saprone.recipe_generator.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
