package com.saprone.food_recipe_generator.service;

import com.saprone.food_recipe_generator.model.Ingredient;
import com.saprone.food_recipe_generator.repository.IngredientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class IngredientServiceTest {

    @Mock
    private IngredientRepository ingredientRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private IngredientService ingredientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFetchAndSaveIngredients_whenDatabaseIsEmpty() {
        // Arrange
        String mockResponse = "{\"meals\": [{\"strIngredient\": \"Sugar\"}, {\"strIngredient\": \"Salt\"}]}";
        when(ingredientRepository.count()).thenReturn(0L);
        when(restTemplate.getForObject(any(String.class), eq(String.class))).thenReturn(mockResponse);

        // Act
        ingredientService.fetchAndSaveIngredients();

        // Assert
        ArgumentCaptor<Ingredient> ingredientCaptor = ArgumentCaptor.forClass(Ingredient.class);
        verify(ingredientRepository, times(2)).save(ingredientCaptor.capture()); // Verify that save was called twice

        List<Ingredient> capturedIngredients = ingredientCaptor.getAllValues();
        assertEquals(2, capturedIngredients.size());
        assertEquals("Sugar", capturedIngredients.get(0).getName());
        assertEquals("Salt", capturedIngredients.get(1).getName());
    }

    @Test
    void testFetchAndSaveIngredients_whenDatabaseIsNotEmpty() {
        // Arrange
        when(ingredientRepository.count()).thenReturn(1L); // Mock the count to return more than 0

        // Act
        ingredientService.fetchAndSaveIngredients();

        // Assert
        verify(restTemplate, never()).getForObject(any(String.class), eq(String.class));
        verify(ingredientRepository, never()).save(any()); // Verify that save was never called
    }

    @Test
    void testFetchAndSaveIngredients_whenJsonIsInvalid() {
        // Arrange
        when(ingredientRepository.count()).thenReturn(0L);
        when(restTemplate.getForObject(any(String.class), eq(String.class))).thenReturn("invalid json");

        // Act
        ingredientService.fetchAndSaveIngredients();

        // Assert
        verify(ingredientRepository, never()).save(any());
    }

    @Test
    void testGetAllIngredients() {
        // Arrange
        Ingredient ingredient = new Ingredient();
        ingredient.setName("Tomato");
        when(ingredientRepository.findAll()).thenReturn(Collections.singletonList(ingredient));

        // Act
        List<Ingredient> ingredients = ingredientService.getAllIngredients();

        // Assert
        assertNotNull(ingredients);
        assertEquals(1, ingredients.size());
        assertEquals("Tomato", ingredients.getFirst().getName());
    }
}
