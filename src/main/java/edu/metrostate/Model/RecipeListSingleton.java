package edu.metrostate.Model;

public class RecipeListSingleton {
    private static RecipeListModel instance;

    private RecipeListSingleton() {}

    public static RecipeListModel getInstance() {
        if (instance == null) {
            instance = new RecipeListModel();
        }
        return instance;
    }
}

