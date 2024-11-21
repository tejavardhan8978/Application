package edu.metrostate.Model;

import java.sql.*;

public class Database {
    private static String databaseName = "test.db";
    private static String connectionString = "jdbc:sqlite:" + databaseName;

    // Method to establish a connection to the database
    public static Connection getConnection() {
       Connection connection = null;
        try {
            connection = DriverManager.getConnection(connectionString);
            System.out.println("Connected to database!");
            // Create the table if it doesn't exist
            Ingredient.createTable(connection);
            NutritionalChart.createTable(connection);
            Recipe.createTable(connection);
            Cuisine.createTable(connection);
        } catch (SQLException e) {
            System.out.println("Connection error: " + e.getMessage());
            return null;
        }
        return connection;
    }

    public static void dbDisconnect(Connection connection) throws SQLException {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (Exception e) {
            System.out.println("Error closing connection: " + e.getMessage());
            throw e;
        }
    }
}

