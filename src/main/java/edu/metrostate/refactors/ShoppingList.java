package edu.metrostate.refactors;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class ShoppingList {
    private final LocalDate creationDate;
    private final List<GroceryItem> items;

    public ShoppingList(LocalDate creationDate) {
        this.creationDate = creationDate;
        this.items = new ArrayList<>();
    }

    public LocalDate getCreationDate () {
        return creationDate;
    }

    public List<GroceryItem> getItems () {
        return items;
    }
}