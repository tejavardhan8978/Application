package edu.metrostate.Model;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cuisine getCuisine() {
        return cuisine;
    }

    public void setCuisine(Cuisine cuisine) {
        this.cuisine = cuisine;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCookTime() {
        return cookTime;
    }

    public void setCookTime(int cookTime) {
        this.cookTime = cookTime;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public Ingredient getPrimaryIngredient() {
        return primaryIngredient;
    }

    public void setPrimaryIngredient(Ingredient primaryIngredient) {
        this.primaryIngredient = primaryIngredient;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public IngredientList getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(IngredientList ingredientList) {
        this.ingredientList = ingredientList;
    }
}