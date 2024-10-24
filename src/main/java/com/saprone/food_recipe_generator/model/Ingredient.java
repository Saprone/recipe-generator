package com.saprone.food_recipe_generator.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Ingredient {

    @Id
    private Integer ingredientId;

    private String ingredientName;
}