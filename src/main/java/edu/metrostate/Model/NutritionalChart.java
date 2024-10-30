package edu.metrostate.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NutritionalChart {

    private int nutritionID;
    private int ingredientID;
    private int servingSize;
    private int calories;
    private int totalCarbohydrates;
    private int totalFat;
    private int totalProtein;
    private int totalSodium;
    private int totalSugars;
    private int dietaryFiber;
    private int cholesterol;

    public NutritionalChart(int ingredientID, int servingSize, int calories, int totalCarbohydrates, int totalFat,
                            int cholesterol, int dietaryFiber, int totalProtein, int totalSodium, int totalSugars) {
        this.ingredientID = ingredientID;
        this.servingSize = servingSize;
        this.calories = calories;
        this.totalCarbohydrates = totalCarbohydrates;
        this.totalFat = totalFat;
        this.totalProtein = totalProtein;
        this.totalSodium = totalSodium;
        this.totalSugars = totalSugars;
        this.dietaryFiber = dietaryFiber;
        this.cholesterol = cholesterol;
    }

    public NutritionalChart() {
    }

    public static void createTable(Connection connection) throws SQLException{
        String sql = "CREATE TABLE IF NOT EXISTS NutritionalChart(" +
                "nutritionID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "ingredientID INTEGER, " +
                "servingSize INTEGER," +
                "calories INTEGER, " +
                "totalCarbohydrates INTEGER, " +
                "totalFat INTEGER, " +
                "totalProtein INTEGER, " +
                "totalSodium INTEGER," +
                "totalSugars INTEGER, " +
                "dietaryFiber INTEGER, " +
                "cholesterol INTEGER, " +
                "FOREIGN KEY (ingredientID) REFERENCES IngredientTable (ingredientID) ON DELETE CASCADE" +
                ");";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.execute();
        }
    }

    public int insert(Connection connection) throws SQLException {
        String sql = "INSERT INTO NutritionalChart (" +
                "ingredientID, " +
                "servingSize, " +
                "calories, " +
                "totalCarbohydrates, " +
                "totalFat, " +
                "totalProtein, " +
                "totalSodium, " +
                "totalSugars, " +
                "dietaryFiber, " +
                "cholesterol) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, this.ingredientID);
            stmt.setInt(2, this.servingSize);
            stmt.setInt(3, this.calories);
            stmt.setInt(4, this.totalCarbohydrates);
            stmt.setInt(5, this.totalFat);
            stmt.setInt(6, this.totalProtein);
            stmt.setInt(7, this.totalSodium);
            stmt.setInt(8, this.totalSugars);
            stmt.setInt(9, this.dietaryFiber);
            stmt.setInt(10, this.cholesterol);

            stmt.execute();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    this.nutritionID = generatedKeys.getInt(1);
                    return this.nutritionID;
                }
            }
        }
        return -1;
    }

    public void updateIngredientID(Connection connection, int ingredientID) throws SQLException {
        System.out.println("I've received the ingredientID and it's being set to " + this.nutritionID);
        String sql = "UPDATE NutritionalChart SET ingredientID = ? WHERE nutritionID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, ingredientID); // Set the new ingredientID
            stmt.setInt(2, this.nutritionID); // Use the current nutritionID to locate the record
            stmt.executeUpdate(); // Execute the update
        }
    }


    public int getNutritionID(){
        return nutritionID;
    }

    public void setNutritionID(int nutritionID){
        this.nutritionID = nutritionID;
    }

    public int getIngredientID(){
        return ingredientID;
    }

    public void setIngredientID(int ingredientID){
        this.ingredientID = ingredientID;
    }

    public int getServingSize() {
        return servingSize;
    }

    public void setServingSize(int servingSize) {
        this.servingSize = servingSize;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getTotalCarbohydrates() {
        return totalCarbohydrates;
    }

    public void setTotalCarbohydrates(int totalCarbohydrates) {
        this.totalCarbohydrates = totalCarbohydrates;
    }

    public int getTotalFat() {
        return totalFat;
    }

    public void setTotalFat(int totalFat) {
        this.totalFat = totalFat;
    }

    public int getTotalProtein() {
        return totalProtein;
    }

    public void setTotalProtein(int totalProtein) {
        this.totalProtein = totalProtein;
    }

    public int getTotalSodium() {
        return totalSodium;
    }

    public void setTotalSodium(int totalSodium) {
        this.totalSodium = totalSodium;
    }

    public int getTotalSugars() {
        return totalSugars;
    }

    public void setTotalSugars(int totalSugars) {
        this.totalSugars = totalSugars;
    }

    public int getDietaryFiber() {
        return dietaryFiber;
    }

    public void setDietaryFiber(int dietaryFiber) {
        this.dietaryFiber = dietaryFiber;
    }

    public int getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(int cholesterol) {
        this.cholesterol = cholesterol;
    }
}