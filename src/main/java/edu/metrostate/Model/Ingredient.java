package edu.metrostate.Model;

import java.util.ArrayList;
import java.util.Date;

public class Ingredient {

    private int id;
    private String name;
    private Date expiryDate;
    private NutritionalChart nutrition;
    private MacroNutrient primaryMacroNutrient;
    private Storage storage;
    private int quantity;
    private String category;
    //Buying an ingredient for the first time
    public boolean CreateNewIngredient(int id, String name, Date expiryDate, NutritionalChart nutrition, MacroNutrient primaryMacroNutrient, Storage storage, int quantity, String category){
        this.id = id;
        this.name = name;
        this.expiryDate = expiryDate;
        this.nutrition = nutrition;
        this.primaryMacroNutrient = primaryMacroNutrient;
        this.storage = storage;
        this.quantity = quantity;
        this.category = category;
        return true;
    }
    //This will be the method to update quantity as more ingredients are bought
    public boolean UpdateQuantity(int id, int NewQuantity){
        this.id = id;
        this.quantity = NewQuantity;
        return false;
    }
    //This Will Search through all stored ingredients to find matching ID and return quantity of it
    public int GetQuantity(int id){
        return quantity;
    }
    /**
     * This method takes an ingredient as a parameter and returns a list of recipes that use this specific ingredient.
     * @param ingredient Ingredient to be searched for.
     * @return ArrayList of Recipes that use the given ingredient.
     */
    public static ArrayList<Recipe> queryRecipes(Ingredient ingredient) {
        ArrayList<Recipe> recipes = new ArrayList<>();
        return recipes;
    }
}