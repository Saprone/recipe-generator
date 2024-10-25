package com.saprone.food_recipe_generator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    private static final String urlIngredientsMealDB = "https://www.themealdb.com/api/json/v1/1/list.php?i=list";

    @PostConstruct
    public void fetchAndSaveIngredients() {
        try {
            // Check if table ingredients in the database is empty
            if (ingredientRepository.count() == 0) {
                // Fetching the ingredients from the external API
                String response = restTemplate.getForObject(urlIngredientsMealDB, String.class);

                // Parsing the response and saving each ingredient
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(response);
                JsonNode ingredientsNode = jsonNode.path("meals");

                if (ingredientsNode.isArray()) {
                    for (JsonNode ingredientNode : ingredientsNode) {
                        String ingredientName = ingredientNode.path("strIngredient").asText();

                        if (!ingredientName.isEmpty()) {
                            Ingredient ingredient = new Ingredient();
                            ingredient.setName(ingredientName);

                            ingredientRepository.save(ingredient);
                        }
                    }
                }

                System.out.println("Ingredients fetched and saved successfully.");
            } else {
                System.out.println("Database is not empty. Skipping fetching and saving ingredients.");
            }
        } catch (JsonProcessingException e) {
            System.err.println("Error processing JSON response: " + e.getMessage());
        }
    }

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }
}
