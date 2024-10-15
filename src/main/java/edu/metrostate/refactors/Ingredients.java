package edu.metrostate.refactors;

import java.util.ArrayList;
import java.util.List;

public final class Ingredients {

    private List<Ingredient> ingredients;

    public Ingredients() {
        this.ingredients = new ArrayList<Ingredient>();
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }

    public Ingredient search (int ingredientId) {
        return null;
    }

    public Ingredient search (String ingredientName) {
        return null;
    }

    public int getTotalCalories () {
        return 0;
    }

}