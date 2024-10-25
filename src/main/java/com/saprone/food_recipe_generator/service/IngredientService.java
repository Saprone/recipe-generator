package com.saprone.food_recipe_generator.service;

import com.saprone.food_recipe_generator.model.Ingredient;
import com.saprone.food_recipe_generator.repository.IngredientRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private RestTemplate restTemplate;

    @PostConstruct
    public void fetchAndSaveIngredients() throws Exception {
        try {
            String urlIngredientsMealDB = "https://www.themealdb.com/api/json/v1/1/list.php?i=list";
            System.out.println(urlIngredientsMealDB);

        } catch (Exception e) {
            throw new Exception("Error fetching and saving ingredients.", e);
        }
    }

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }
}
