package edu.metrostate.Controller;

import edu.metrostate.Model.Ingredient;
import edu.metrostate.Model.IngredientList;

import java.util.ArrayList;

public class InventoryController {

    public ArrayList<IngredientList> getInventory() {
        ArrayList<IngredientList> inventory = new ArrayList<>();
        return inventory;
    }

    public boolean addNewIngredient(Ingredient ingredient) {
        //code to add new ingredient
        return true;
    }

    public Ingredient getIngredientDetailed(int id) {
        //code to get required details of ingredient.
        return null;
    }

    public boolean save() {
        return false;
    }
    
}