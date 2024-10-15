package edu.metrostate.refactors;


public final class GroceryItem {
    private final Food food;
    private final int purchaseAmount;

    public Food getFood () {
        return food;
    }

    public int getPurchaseAmount () {
        return purchaseAmount;
    }

    public GroceryItem (Food food, int purchaseAmount) {
        this.food = food;
        this.purchaseAmount = purchaseAmount;
    }
}