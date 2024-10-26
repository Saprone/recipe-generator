package com.saprone.food_recipe_generator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.saprone.food_recipe_generator.model.Ingredient;
import com.saprone.food_recipe_generator.repository.IngredientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class IngredientServiceTest {

    @Mock
    private IngredientRepository ingredientRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private IngredientService ingredientService;

    @Test
    public void testFetchAndSaveIngredients_whenDatabaseIsEmpty() {
        // Arrange
        String jsonResponse = "{\"meals\":[{\"strIngredient\":\"Ingredient1\"},{\"strIngredient\":\"Ingredient2\"}]}";
        when(ingredientRepository.count()).thenReturn(0L); // Mock the count to return 0
        when(restTemplate.getForObject(any(String.class), eq(String.class))).thenReturn(jsonResponse);

        // Act
        ingredientService.fetchAndSaveIngredients();

        // Assert
        verify(ingredientRepository, times(2)).save(any(Ingredient.class)); // Verify that save was called twice
    }

    @Test
    public void testFetchAndSaveIngredients_whenDatabaseIsNotEmpty() {
        // Arrange
        when(ingredientRepository.count()).thenReturn(1L); // Mock the count to return more than 0

        // Act
        ingredientService.fetchAndSaveIngredients();

        // Assert
        verify(ingredientRepository, never()).save(any(Ingredient.class)); // Verify that save was never called
    }
}
