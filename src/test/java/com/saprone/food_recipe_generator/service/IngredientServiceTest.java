package com.saprone.food_recipe_generator.service;

import com.saprone.food_recipe_generator.repository.IngredientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

class IngredientServiceTest {

    @InjectMocks
    private IngredientService ingredientService;

    @Mock
    private IngredientRepository ingredientRepository;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldFetchAndSaveIngredients() {
        String urlIngredientsMealDB = "https://www.themealdb.com/api/json/v1/1/list.php?i=list";

        System.out.println("Test method: fetchAndSaveIngredients.");
    }

    @Test
    public void shouldGetAllIngredients() {
        System.out.println("Test method: getAllIngredients.");
    }
}
