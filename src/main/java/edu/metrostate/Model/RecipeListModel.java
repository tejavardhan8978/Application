package edu.metrostate.Model;

import java.util.ArrayList;

public class RecipeListModel {

    private ArrayList<Recipe> recipes;
    private String name;
    private int listType;
    private static int lastId =0;

    public RecipeListModel() {
        this.recipes = new ArrayList<>();
    }

    public RecipeListModel(ArrayList<Recipe> recipes, String name, int listType) {
        this.recipes = recipes;
        this.name = name;
        this.listType = listType;
    }

    public void addRecipe(Recipe recipe){
        recipe.setId(++lastId);
        recipes.add(recipe);
    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
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
