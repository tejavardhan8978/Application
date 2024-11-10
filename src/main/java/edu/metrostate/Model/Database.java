package edu.metrostate.Model;

import java.sql.*;

public class Database {
    private static String databaseName = "test.db";
    private static String connectionString = "jdbc:sqlite:" + databaseName;
    private static Connection conn = null;

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

    public static void dbDisconnect() throws SQLException {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e) {
            throw e;
        }
    }
}

