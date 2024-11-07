package com.saprone.food_recipe_generator.controller;

import com.saprone.food_recipe_generator.model.Ingredient;
import com.saprone.food_recipe_generator.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class IngredientController {

    private final IngredientService ingredientService;

    @Autowired
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/ingredients")
    public List<Ingredient> getAllIngredients() {
        return ingredientService.getAllIngredients();
    }
}
