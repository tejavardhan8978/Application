package edu.metrostate.Model;

import edu.metrostate.Model.Categories.Ingredient;

public class Recipe {

    private int id;
    private String name;
    private Cuisine cuisine;
    private String description;
    private int cookTime;
    private int servings;
    private Ingredient primaryIngredient;
    private int calories;
    private IngredientList ingredientList;

    //default constructor
    public Recipe() {}

    public Recipe(int id, String name, Cuisine cuisine, String description,
                  int cookTime, int servings, Ingredient primaryIngredient,
                  int calories, IngredientList ingredientList) {
        this.id = id;
        this.name = name;
        this.cuisine = cuisine;
        this.description = description;
        this.cookTime = cookTime;
        this.servings = servings;
        this.primaryIngredient = primaryIngredient;
        this.calories = calories;
        this.ingredientList = ingredientList;
    }

    public boolean queryIngredientsToGroceryList() {
        return false;
    }
    
}