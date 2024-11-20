package edu.metrostate.Controller;

import edu.metrostate.Model.*;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

//import static edu.metrostate.Model.Ingredient.getIngredientByID;

public class AddToRecipeController {

    //Fields not for the nutritional chart
    @FXML private TextField recipeNameField;
    @FXML private TextField cuisineNameField;
    @FXML private TextField cuisineOriginField;
    @FXML private TextField primaryIngredientField;
    @FXML private TextField servingsField;
    @FXML private TextField cookTimeField;
    @FXML private TextField recipeDescriptionArea;
    @FXML private TextArea directionsArea;
    @FXML private TextArea ingredientsField;

    //Fields for a nutritional Chart
    @FXML private TextField servingSizeField;
    @FXML private TextField caloriesField;
    @FXML private TextField carbsField;
    @FXML private TextField fatField;
    @FXML private TextField proteinField;
    @FXML private TextField sodiumField;
    @FXML private TextField sugarField;
    @FXML private TextField fiberField;
    @FXML private TextField cholesterolField;

    //Declare instance of the recipe list to manage the list.
    RecipeListModel recipeList;
    //Declare an instance of the recipe controller to add items to the list and update view
    private RecipeController recipeController;
    private Parent root;
    private Stage stage;

    //Initialize the recipeList/Controller allows the controller to access and modify the shared recipe list
    public void setRecipeList(RecipeListModel recipeList, RecipeController recipeController){
        this.recipeList = recipeList;
        this.recipeController = recipeController;
    }

    //Clear fields so no data remains
    private void clearFields(){
        recipeNameField.clear();
        cuisineNameField.clear();
        cuisineOriginField.clear();
        primaryIngredientField.clear();
        servingsField.clear();
        cookTimeField.clear();
        directionsArea.clear();
        recipeDescriptionArea.clear();
        ingredientsField.clear();
        servingSizeField.clear();
        caloriesField.clear();
        carbsField.clear();
        fatField.clear();
        proteinField.clear();
        sodiumField.clear();
        sugarField.clear();
        fiberField.clear();
        cholesterolField.clear();
    }

    //Cancel button to stop adding a recipe clear the fields and go back
    public void setCancelButton(MouseEvent event) throws IOException, SQLException {
        clearFields();
        ReturnToRecipeHome(event);
    }

    public void ReturnToRecipeHome(MouseEvent event) throws IOException, SQLException {
        recipeController.updateTableView();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void setRoot(Parent root) {
        this.root = root;
    }

    public void handleSaveButtonAction(MouseEvent event) throws SQLException, IOException {

        try {
            Connection connection = Database.getConnection();

            //get all info into local variables.
            Recipe recipe = new Recipe();
            String recipeName = recipeNameField.getText();
            if (recipeName.isEmpty()) {
                recipe.setName(" ");
            } else {
                recipe.setName(recipeName);
            }

            String description = recipeDescriptionArea.getText();
            if (description.isEmpty()) {
                recipe.setDescription(" ");
            } else {
                recipe.setDescription(description);
            }

            Integer cookTime = Integer.parseInt(cookTimeField.getText());
            recipe.setCookTime(cookTime);
            Integer servings = Integer.parseInt(servingsField.getText());
            recipe.setServings(servings);

            String primaryIngredientName = primaryIngredientField.getText().trim();
            Ingredient primaryIngredient = null;

            if (!primaryIngredientName.isEmpty()) {
                primaryIngredient = Ingredient.getIngredientByName((primaryIngredientName));
                if (primaryIngredient != null) {
                    recipe.setPrimaryIngredientID(primaryIngredient.getIngredientID());
                    recipe.setPrimaryIngredient(primaryIngredient);
                } else {
                    recipe.setPrimaryIngredientID(0);
                    recipe.setPrimaryIngredient(null);
                }
            }

            String instructions = directionsArea.getText();
            if (instructions.isEmpty()) {
                recipe.setInstructions(" ");
            } else {
                recipe.setInstructions(instructions);
            }

            String ingredients = ingredientsField.getText();
            if (ingredients.isEmpty()) {
                recipe.setIngredients(" ");
            } else {
                recipe.setIngredients(ingredients);
            }

            // Getting values for Nutritional chart to insert into recipe.
            Integer servingSize = Integer.parseInt(servingSizeField.getText());
            Integer calories = Integer.parseInt(caloriesField.getText());
            Integer totalCarbohydrates = Integer.parseInt(carbsField.getText());
            Integer totalFat = Integer.parseInt(fatField.getText());
            Integer totalProtein = Integer.parseInt(proteinField.getText());
            Integer totalSodium = Integer.parseInt(sodiumField.getText());
            Integer totalSugars = Integer.parseInt(sugarField.getText());
            Integer dietaryFiber = Integer.parseInt(fiberField.getText());
            Integer cholesterol = Integer.parseInt(cholesterolField.getText());

            try {
                NutritionalChart nutritionalChart = new NutritionalChart.Builder()
                        .servingSize(servingSize)
                        .calories(calories)
                        .totalCarbohydrates(totalCarbohydrates)
                        .totalFat(totalFat)
                        .totalProtein(totalProtein)
                        .totalSodium(totalSodium)
                        .totalSugars(totalSugars)
                        .dietaryFiber(dietaryFiber)
                        .cholesterol(cholesterol)
                        .build();

                int nutritionID;
                if (nutritionalChart.checkAllValuesExist()) {
                    nutritionID = nutritionalChart.insert(connection);
                } else {
                    nutritionID = 0;
                }

                recipe.setNutritionID(nutritionID);
                recipe.setNutrition(nutritionalChart);

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Exception thrown while inserting a nutrition chart from AddToRecipeController");
            }

            try {
                String cuisineName = cuisineNameField.getText();
                String cuisineCountry = cuisineOriginField.getText();
                Cuisine cuisine = new Cuisine(cuisineName, cuisineCountry);

                Integer cuisineID;
                if (cuisine.checkAllValuesExist()) {
                    cuisineID = cuisine.insert(connection);
                } else {
                    cuisineID = 0;
                }

                recipe.setCuisineID(cuisineID);
                recipe.setCuisine(cuisine);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Exception thrown when inserting cuisine in AddToRecipeController");
            }

            System.out.println(recipe);
            recipe.insert(connection);
            recipeList.addRecipe(recipe);
            recipeController.updateTableView();

        }catch (Exception e) {
            e.printStackTrace();
        }
        clearFields();
        ReturnToRecipeHome(event);
    }
}
