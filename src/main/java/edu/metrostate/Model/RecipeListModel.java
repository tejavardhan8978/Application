package edu.metrostate.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RecipeListModel {

    private ArrayList<Recipe> recipes;
    private String name;
    private int listType;
    private static int lastId = 0;
    private boolean isLoaded;

    public RecipeListModel() {
        this.recipes = new ArrayList<>();
        this.isLoaded = false;
    }

    public RecipeListModel(ArrayList<Recipe> recipes, String name, int listType) {
        this.recipes = recipes;
        this.name = name;
        this.listType = listType;
        this.isLoaded = true;
    }

    public void addRecipe(Recipe recipe){
        recipe.setRecipeID(++lastId);
        recipes.add(recipe);
    }

    public ArrayList<Recipe> getRecipes() throws SQLException {
        if (!isLoaded) {
            loadRecipesFromDB();
            isLoaded = true;
        }
        return recipes;
    }

    public void setRecipes(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getListType() {
        return listType;
    }

    public void setListType(int listType) {
        this.listType = listType;
    }


    public void loadRecipesFromDB() throws SQLException {
        String sql = "SELECT * FROM RecipeTable";
        try (Connection connection = Database.getConnection()) {
            assert connection != null;
            System.out.println(connection + "trying to get connection .............................");
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int recipeID = resultSet.getInt("recipeID");
                    String name = resultSet.getString("name");
                    int cuisineID = resultSet.getInt("cuisineID");
                    String description = resultSet.getString("description");
                    int cookTime = resultSet.getInt("cookTime");
                    int primaryIngredientID = resultSet.getInt("primaryIngredientID");
                    String ingredients = resultSet.getString("ingredients");
                    String instruction = resultSet.getString("instructions");
                    int nutritionID = resultSet.getInt("nutritionID");
                    int servings = resultSet.getInt("servings");

                    Recipe recipe = new Recipe.RecipeBuilder()
                            .setRecipeID(recipeID)
                            .setName(name)
                            .setCuisineID(cuisineID)
                            .setDescription(description)
                            .setCookTime(cookTime)
                            .setPrimaryIngredientID(primaryIngredientID)
                            .setIngredients(ingredients)
                            .setInstruction(instruction)
                            .setNutritionID(nutritionID)
                            .setServings(servings)
                            .build();
                    this.addRecipe(recipe);

                }
            }
        }
        this.retrieveRestOfData();
    }

    public void retrieveRestOfData() throws SQLException {
        for (Recipe tempRecipe : this.recipes) {
            Cuisine tempCuisine = Cuisine.getCuisineByID(tempRecipe.getCuisineID());
            tempRecipe.setCuisine(tempCuisine);

            Ingredient tempIngredient = Ingredient.getIngredientByID(tempRecipe.getPrimaryIngredientID());
            tempRecipe.setPrimaryIngredient(tempIngredient);

            NutritionalChart tempChart = NutritionalChart.getNutritionalChartByID(tempRecipe.getNutritionID());
            tempRecipe.setNutrition(tempChart);

        }
    }
}
