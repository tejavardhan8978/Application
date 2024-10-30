package edu.metrostate.Model;

public class Recipe {

    private int id;
    private String name;
    private Cuisine cuisine;
    private String description;
    private int cookTime;
    private int servings;
    private String primaryIngredient;
    private NutritionalChart nutrition;
    private String ingredients;
    private String instruction;

    //default constructor
    public Recipe() {}

    public Recipe(int id, String name, Cuisine cuisine, String description,
                  int cookTime, int servings, String primaryIngredient,
                  NutritionalChart nutrition, String ingredients, String instruction) {
        this.id = id;
        this.name = name;
        this.cuisine = cuisine;
        this.description = description;
        this.cookTime = cookTime;
        this.servings = servings;
        this.primaryIngredient = primaryIngredient;
        this.nutrition = nutrition;
        this.ingredients = ingredients;
        this.instruction = instruction;
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

    public String getPrimaryIngredient() {
        return primaryIngredient;
    }

    public void setPrimaryIngredient(String primaryIngredient) {
        this.primaryIngredient = primaryIngredient;
    }

    public String getIngredientList() {
        return ingredients;
    }

    public void setIngredientList(String ingredients) {
        this.ingredients = ingredients;
    }

    public NutritionalChart getNutrition() {
        return nutrition;
    }

    public void setNutrition(NutritionalChart nutrition) {
        this.nutrition = nutrition;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cuisine=" + cuisine +
                ", description='" + description + '\'' +
                ", cookTime=" + cookTime +
                ", servings=" + servings +
                ", primaryIngredient=" + primaryIngredient +
                ", ingredientList=" + ingredients +
                '}';
    }

    public String getInstructions() {
        return instruction;
    }
}