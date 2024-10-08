package edu.metrostate.Model;

import java.util.ArrayList;

public class RecipeListModel {

    private ArrayList<Recipe> recipes;
    private String name;
    private int listType;

    public RecipeListModel() {}

    public RecipeListModel(ArrayList<Recipe> recipes, String name, int listType) {
        this.recipes = recipes;
        this.name = name;
        this.listType = listType;
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
