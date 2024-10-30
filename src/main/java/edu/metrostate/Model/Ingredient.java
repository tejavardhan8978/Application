package edu.metrostate.Model;

import java.sql.ResultSet;
import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ingredient {

    private int ingredientID;
    private String name;
    private Date expiryDate;
    private int nutritionID;
    private MacroNutrient primaryMacroNutrient;
    private Storage storage;
    private int quantity;
    private Category category;
    private String description;
    private File image;

    public Ingredient(){
    }

    //Buying an ingredient for the first time
    public Ingredient(String name, Date expiryDate,
                      MacroNutrient primaryMacroNutrient,
                      Storage storage, int quantity, Category category,
                      String description){
        this.name = name;
        this.expiryDate = expiryDate;
        this.primaryMacroNutrient = primaryMacroNutrient;
        this.storage = storage;
        this.quantity = quantity;
        this.category = category;
        this.description = description;
    }

    //This constructor will add items to the singleton list as it is called upon to view them in the table
    public Ingredient(int ingredientID, String name, Date expiryDate, int quantity, MacroNutrient primaryMacroNutrient,
                      Storage storage, Category category){
        this.ingredientID = ingredientID;
        this.name = name;
        this.expiryDate = expiryDate;
        this.quantity = quantity;
        this.primaryMacroNutrient = primaryMacroNutrient;
        this.storage = storage;
        this.category = category;
    }

    //This will be the method to update quantity as more ingredients are bought
    public boolean UpdateIngredient(int ingredientID, int NewQuantity, Date NewDate){
        this.ingredientID = ingredientID;
        this.quantity = NewQuantity;
        this.expiryDate = NewDate;
        return false;
    }

    //Creating a table for the first time
    public static void createTable(Connection connection) throws SQLException{
        String sql = "CREATE TABLE IF NOT EXISTS IngredientTable(" +
                "ingredientID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT, " +
                "expiryDate DATE," +
                "nutritionID INTEGER," +
                "primaryMacroNutrient TEXT, " +
                "storage TEXT, " +
                "quantity INTEGER, " +
                "category TEXT, " +
                "description TEXT," +
                "FOREIGN KEY (nutritionID) REFERENCES NutritionalChart(nutritionID) ON DELETE CASCADE" +
                ");";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.execute();
        }
    }

    //inserting an item into the db
    public int insert(Connection connection) throws SQLException{
        String sql = "INSERT INTO IngredientTable (" +
                "name, " +
                "expiryDate, " +
                "nutritionID, " +
                "primaryMacroNutrient, " +
                "storage, " +
                "quantity, " +
                "category, " +
                "description)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
           stmt.setString(1, this.name);
           stmt.setDate(2, new java.sql.Date(this.expiryDate.getTime()));
           stmt.setInt(3, this.nutritionID);
           stmt.setString(4, this.primaryMacroNutrient.name());
           stmt.setString(5, this.storage.name());
           stmt.setInt(6, this.quantity);
           stmt.setString(7, this.category.name());
           stmt.setString(8, this.description);

           stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    this.ingredientID = generatedKeys.getInt(1);
                    return this.ingredientID;
                }
            }
        }
        return -1;
    }

    //Updating the keys after insertion
    public void updateNutritionID(Connection connection, int nutritionID) throws SQLException {
        System.out.println("I've received the nutritionID and it's being set to " + nutritionID);
        String sql = "UPDATE IngredientTable SET nutritionID = ? WHERE ingredientID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, nutritionID); // Set the nutritionID
            stmt.setInt(2, this.ingredientID); // Set the ingredientID to locate the record
            stmt.executeUpdate(); // Execute the update
        }
    }

    //This Will Search through all stored ingredients to find matching ID and return quantity of it
    public int GetQuantity(int id){
        return quantity;
    }

    /**
     * This method takes an ingredient as a parameter and returns a list of recipes that use this specific ingredient.
     * @param ingredient Ingredient to be searched for.
     * @return ArrayList of Recipes that use the given ingredient.
     */

    public static ArrayList<Recipe> queryRecipes(Ingredient ingredient) {
        ArrayList<Recipe> recipes = new ArrayList<>();
        return recipes;
    }

    public void setIngredientID(int ingredientID){
        this.ingredientID = ingredientID;
    }

    public int getIngredientID(){
        return ingredientID;
    }

    public void setNutritionID(int nutritionID){
        this.nutritionID = nutritionID;
    }

    public int getNutritionID(){
        return nutritionID;
    }

    public String getName(){
        return name;
    }

    public Date getExpiryDate(){
        return expiryDate;
    }

    public MacroNutrient getPrimaryMacroNutrient(){
        return primaryMacroNutrient;
    }

    public Storage getStorage(){
        return storage;
    }

    public int getQuantity(){
        return quantity;
    }

    public Category getCategory(){
        return category;
    }

    public String getDescription() {
        return this.description;
    }

    public NutritionalChart getNutrition() {
        return nutrition;
    }

    public void setNutrition(NutritionalChart nutrition) {
        this.nutrition = nutrition;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}