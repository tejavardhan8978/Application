package edu.metrostate.Model;

import java.sql.*;
import java.util.Objects;
import java.util.stream.Stream;

public class Cuisine {

    private Integer cuisineID;
    private String name;
    private String country;

    public Cuisine(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return name;
    }

    public Integer getCuisineID() {
        return cuisineID;
    }


    public static void createTable(Connection connection) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS CuisineTable(" +
                "cuisineID INTEGER PRIMARY KEY AUTOINCREMENT ," +
                "name TEXT," +
                "country TEXT" +
                ");";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.execute();
        }

        String insertBlank = "INSERT INTO CuisineTable(" +
                "name," +
                "country)" +
                "VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertBlank, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, "PANGAEA");
            preparedStatement.setString(2, "PANGAEA");

            preparedStatement.execute();
            try (ResultSet generatedKey = preparedStatement.getGeneratedKeys()) {
                if (generatedKey.next()) {
                    Integer cuisineID = generatedKey.getInt(1);
                    if (cuisineID == 0) {
                        System.out.println("Blank cuisine has been successfully added.");
                    }
                }
            }
            } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }


    public int insert(Connection connection) {
        String insertBlank = "INSERT INTO CuisineTable(" +
                "name," +
                "country)" +
                "VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertBlank, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, this.name);
            preparedStatement.setString(2, this.country);

            preparedStatement.execute();
            try (ResultSet generatedKey = preparedStatement.getGeneratedKeys()) {
                if (generatedKey.next()) {
                    this.cuisineID = generatedKey.getInt(1);
                    return cuisineID;
                }
            }

        } catch (SQLException exception) {
        exception.printStackTrace();
        }

        return -1;
    }

    public static Cuisine getCuisineByID(int ID) throws SQLException {
        String sql = "SELECT * FROM CuisineTable WHERE cuisineID = ?";
        try (Connection connection = Database.getConnection()) {
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, ID);
                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {

                    String name = rs.getString("name");
                    String country = rs.getString("country");

                    return new Cuisine(name, country);
                }
            }
        }
        return null;
    }

    public boolean checkAllValuesExist() {
        boolean isFilled = Stream.of(this.getName(),
                this.getCountry()).allMatch(Objects::nonNull);
        return isFilled;
    }

}