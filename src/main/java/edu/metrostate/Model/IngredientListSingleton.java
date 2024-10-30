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
    private final List<Ingredient> ingredientList;

    private IngredientListSingleton() {
        ingredientList = new ArrayList<>();
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
                    MacroNutrient primaryMacroNutrient = MacroNutrient.valueOf(rs.getString("primaryMacroNutrient").toUpperCase());
                    Storage storage = Storage.valueOf(rs.getString("Storage").toUpperCase());
                    int quantity = rs.getInt("quantity");
                    Category category = Category.valueOf(rs.getString("Category").toUpperCase());

                    //Creates new objects of items through a simpler constructor for user viewing
                    Ingredient ingredient = new Ingredient(ingredientID, name, expiryDate, quantity, primaryMacroNutrient, storage, category);
                    ingredientList.add(ingredient);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
    }

    //Add a new item into the singleton list to be viewed in the table
    public void addIngredientToList(Ingredient ingredient) {
        ingredientList.add(ingredient);
    }

    public List<Ingredient> getIngredients() {
        return ingredientList;
    }
}

