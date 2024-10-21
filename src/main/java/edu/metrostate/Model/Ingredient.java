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
    private String description;

    //Buying an ingredient for the first time
    public Ingredient(int id, String name, Date expiryDate, NutritionalChart nutrition,
                      MacroNutrient primaryMacroNutrient, Storage storage, int quantity, String category, String description){
        this.id = id;
        this.name = name;
        this.expiryDate = expiryDate;
        this.nutrition = nutrition;
        this.primaryMacroNutrient = primaryMacroNutrient;
        this.storage = storage;
        this.quantity = quantity;
        this.category = category;
        this.description = description;
    }
    public Ingredient(){}
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

    public void setID(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public Date getExpiryDate(){
        return expiryDate;
    }

    public MacroNutrient getPrimaryMacroNutrient(){
        return primaryMacroNutrient;
    }

    public Storage getStorage(){
        return storage;
    }

    public int getQuantity(){
        return quantity;
    }

    public String getCategory(){
        return category;
    }

    public String getDescription() {
        return this.description;
    }
}