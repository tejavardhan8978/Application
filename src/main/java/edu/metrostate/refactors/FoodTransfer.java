package edu.metrostate.refactors;

public final class FoodTransfer {
    private final CompletedShoppingList completedShoppingList;
    private final Pantry pantry;

    public FoodTransfer (CompletedShoppingList completedShoppingList, Pantry pantry) {
        this.completedShoppingList = completedShoppingList;
        this.pantry = pantry;
    }

    public CompletedShoppingList getCompletedShoppingList () {
        return completedShoppingList;
    }

    public Pantry getPantry () {
        return pantry;
    }

    public void transfer () {}
}