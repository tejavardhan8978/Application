package edu.metrostate.Controller;

import edu.metrostate.Model.Ingredient;
import edu.metrostate.Model.NutritionalChart;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {

    private static String databaseName = "test.db";
    private static String connectionString = "jdbc:sqlite:" + databaseName;
    private static Connection conn = null;


    public static Connection connect() {
        try {
            Connection connection = DriverManager.getConnection(connectionString);
            // Create the table if it doesn't exist
            Ingredient.createTable(connection);
            NutritionalChart.createTable(connection);
            return connection;
        } catch (SQLException e) {
            System.out.println("Connection error IngredientTable: " + e.getMessage());
            return null;
        }
    }
}