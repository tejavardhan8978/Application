package edu.metrostate.Model.Categories;

import edu.metrostate.Model.MacroNutrient;
import edu.metrostate.Model.NutritionalChart;
import edu.metrostate.Model.Recipe;
import edu.metrostate.Model.Storage;

import java.util.ArrayList;
import java.util.Date;

public abstract class Ingredient {

    private int id;
    private String name;
    private Date expiryDate;
    private NutritionalChart nutrition;
    private MacroNutrient primaryMacroNutrient;
    private Storage storage;

    public Ingredient(int id, String name, Date expiryDate, NutritionalChart nutrition, MacroNutrient primaryMacroNutrient, Storage storage) {
        this.id = id;
        this.name = name;
        this.expiryDate = expiryDate;
        this.nutrition = nutrition;
        this.primaryMacroNutrient = primaryMacroNutrient;
        this.storage = storage;
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