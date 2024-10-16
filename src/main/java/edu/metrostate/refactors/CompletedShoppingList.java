package edu.metrostate.refactors;


import java.time.LocalDate;

public final class CompletedShoppingList  {

    private final ShoppingList shoppingList;
    private final LocalDate purchaseDate;

    public CompletedShoppingList (LocalDate purchaseDate, ShoppingList shoppingList) {
        this.purchaseDate = purchaseDate;
        this.shoppingList = shoppingList;
    }

    public ShoppingList getShoppingList () {
        return shoppingList;
    }

    public LocalDate getPurchaseDate () {
        return purchaseDate;
    }
}