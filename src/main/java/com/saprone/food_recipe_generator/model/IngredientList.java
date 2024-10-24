package com.saprone.food_recipe_generator.model;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
public class IngredientList {
    private List<IngredientItem> meals; // Change to map to the actual return type

    @Setter
    @Getter
    public static class IngredientItem {
        private String idIngredient;
        private String strIngredient;
    }
}
