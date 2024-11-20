package edu.metrostate.Model;

import java.util.ArrayList;
import java.util.Date;

public class IngredientList {

    private ArrayList<Ingredient> ingredients;
    private String name;
    private int listType;
    private static int lastId = 0;

    public IngredientList() {
        this.ingredients = new ArrayList<>();
    }

    public IngredientList(ArrayList<Ingredient> ingredients, String name, int listType) {
        this.ingredients = ingredients;
        this.name = name;
        this.listType = listType;
    }

    public void addIngredient(Ingredient ingredient) {
        ingredient.setIngredientID(++lastId);
        ingredients.add(ingredient);
    }
    public void updateIngredient(Ingredient ingredient, int newQuantity, Date newDate){
        ingredient.UpdateIngredient(ingredient.getIngredientID(), newQuantity, newDate);
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
