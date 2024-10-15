package edu.metrostate.refactors;

import java.time.LocalDate;

public class PantryItem {
    private final Food food;
    private int quantityInStock;
    private int reorderThreshold;
    private final LocalDate purchaseDate;

    public PantryItem (LocalDate purchaseDate, int reorderThreshold, int quantityInStock, Food food) {
        this.purchaseDate = purchaseDate;
        this.reorderThreshold = reorderThreshold;
        this.quantityInStock = quantityInStock;
        this.food = food;
    }

    public Food getFood () {
        return food;
    }

    public int getQuantityInStock () {
        return quantityInStock;
    }

    public int getReorderThreshold () {
        return reorderThreshold;
    }

    public LocalDate getPurchaseDate () {
        return purchaseDate;
    }

    public void increaseQuantityInStock (int amount) {
        quantityInStock += Math.abs(amount);
    }

    public void decreaseQuantityInStock (int amount) {
        if (Math.abs(amount) > quantityInStock)
            quantityInStock = 0;
        else
            quantityInStock -= Math.abs(amount);
    }

    public void setReorderThreshold (int reorderThreshold) {
        this.reorderThreshold = Math.abs(reorderThreshold);
    }
}