package edu.metrostate.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IngredientListSingleton {
    private static IngredientListSingleton instance;
    private final IngredientList ingredientList;

    private IngredientListSingleton() {
        ingredientList = new IngredientList();
        loadIngredientsFromDatabase();
    }

    //Singleton design pattern
    public static IngredientListSingleton getInstance() {
        if (instance == null) {
            instance = new IngredientListSingleton();
        }
        return instance;
    }

    //Gets necessary information from the table for initial start of the application
    private void loadIngredientsFromDatabase() {
        String sql = "SELECT * FROM IngredientTable";
        try (Connection connection = Database.getConnection()) {
            assert connection != null;
            try (PreparedStatement stmt = connection.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    int ingredientID = rs.getInt("ingredientID");
                    String name = rs.getString("name");
                    Date expiryDate = rs.getDate("expiryDate");
                    int quantity = rs.getInt("quantity");

                    String primaryMacroNutrientString = rs.getString("primaryMacroNutrient");
                    MacroNutrient primaryMacroNutrient = null;
                    if (primaryMacroNutrientString != null) {
                        try {
                            primaryMacroNutrient = MacroNutrient.valueOf(primaryMacroNutrientString.toUpperCase());
                        } catch (IllegalArgumentException e) {
                            // Handle invalid enum value
                            System.out.println("Invalid primaryMacroNutrient value: " + primaryMacroNutrientString);
                        }
                    }

                    String storageString = rs.getString("storage");
                    Storage storage = null;
                    if (storageString != null){
                        try{
                            storage = Storage.valueOf(storageString.toUpperCase());
                        } catch (IllegalArgumentException e){
                            System.out.println("invalid storage value: " + storageString);
                        }
                    }

                    String categoryString = rs.getString("category");
                    Category category = null;
                    if (categoryString != null){
                        try{
                            category = Category.valueOf(categoryString.toUpperCase());
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid category value: " + categoryString);
                        }
                    }

                    //Creates new objects of items through a simpler constructor for user viewing
                    Ingredient ingredient = new Ingredient.Builder()
                    .ingredientID(ingredientID)
                    .name(name)
                    .expiryDate(expiryDate)
                    .quantity(quantity)
                    .primaryMacroNutrient(primaryMacroNutrient)
                    .storage(storage)
                    .category(category)
                    .build();
                    ingredientList.addIngredient(ingredient);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
    }

    //Add a new item into the singleton list to be viewed in the table
    public void addIngredientToList(Ingredient ingredient) {
        ingredientList.addIngredient(ingredient);
    }

    public IngredientList getIngredients() {
        return ingredientList;
    }
}

