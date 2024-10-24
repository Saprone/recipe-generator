package com.saprone.food_recipe_generator.service;

import com.saprone.food_recipe_generator.model.Ingredient;
import com.saprone.food_recipe_generator.model.IngredientList;
import com.saprone.food_recipe_generator.repository.IngredientRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(IngredientService.class);

    @PostConstruct
    public void fetchAndSaveIngredients() {
        try {
            IngredientList ingredientList = restTemplate.getForObject("https://www.themealdb.com/api/json/v1/1/list.php?i=list", IngredientList.class);

            if (ingredientList != null && ingredientList.getIngredients() != null) {
                for (IngredientList.IngredientItem item : ingredientList.getIngredients()) {
                    String ingredientName = item.getStrIngredient();
                    String ingredientId = item.getIdIngredient();

                    if (ingredientName != null && !ingredientName.trim().isEmpty()) {
                        Ingredient ingredient = new Ingredient();
                        ingredient.setIngredientId(Integer.parseInt(ingredientId));
                        ingredient.setIngredientName(ingredientName);

                        ingredientRepository.save(ingredient);
                    } else {
                        logger.warn("Received empty ingredient name.");
                    }
                }
            } else {
                logger.warn("No ingredients found in response.");
            }
        } catch (Exception e) {
            logger.error("Error fetching and saving ingredients.", e);
        }
    }

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }
}
