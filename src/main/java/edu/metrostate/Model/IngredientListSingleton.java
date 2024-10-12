package edu.metrostate.Model;

public class IngredientListSingleton {
    private static IngredientList instance;

    private IngredientListSingleton() {}

    public static IngredientList getInstance() {
        if (instance == null) {
            instance = new IngredientList();
        }
        return instance;
    }
}
