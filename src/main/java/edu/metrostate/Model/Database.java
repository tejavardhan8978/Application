package edu.metrostate.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    public static String databaseName = "test.db";
    public static String connectionString = "jdbc:sqlite:" + databaseName;

    // Method to establish a connection to the database
    public static Connection getConnection() {
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

    public static Connection NutritionalChartConnect() {
        try {
            Connection connection = DriverManager.getConnection(connectionString);
            // Create the table if it doesn't exist
            NutritionalChart.createTable(connection);
            return connection;
        } catch (SQLException e) {
            System.out.println("Connection error: " + e.getMessage());
            return null;
        }
    }
}

