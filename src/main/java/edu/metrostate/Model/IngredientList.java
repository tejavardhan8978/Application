package edu.metrostate.Model;

import java.util.ArrayList;
import java.util.Date;

/**
 * This class instantiates an object which contains the list of ingredients and which category the list belongs to.
 */
public class IngredientList {

    private ArrayList<Ingredient> ingredients;
    private String name;
    private static int lastId = 0;


    /**
     * ListType field represents whether the list is of:
     * 1 - Ingredients in inventory.
     * 2 - Ingredients in a given recipe.
     * 3 - Ingredients in the grocery list.
     */
    private int listType;

    /**
     * Default constructor
     */
    public IngredientList() {
        this.ingredients = new ArrayList<>();
    }

    public IngredientList(ArrayList<Ingredient> ingredients, String name, int listType) {
        this.ingredients = ingredients;
        this.name = name;
        this.listType = listType;
    }

    public void addIngredient(Ingredient ingredient) {
        ingredient.setID(++lastId);
        ingredients.add(ingredient);
    }
    public void updateIngredient(Ingredient ingredient, int newQuantity, Date newDate){
        ingredient.UpdateIngredient(ingredient.getId(), newQuantity, newDate);
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getListType() {
        return listType;
    }

    public void setListType(int listType) {
        this.listType = listType;
    }
}
