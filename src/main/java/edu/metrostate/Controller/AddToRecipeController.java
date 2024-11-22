package edu.metrostate.Controller;

import edu.metrostate.Main;
import edu.metrostate.Model.*;
import edu.metrostate.Util;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

import static edu.metrostate.Model.Ingredient.getIngredientByID;

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
    IngredientList ingredientList;
    Ingredient primaryIngredient;
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
                Util.displayErrorMessage("Name", "Recipe name cannot be empty!");
                return;
            } else {
                recipe.setName(recipeName);
            }

            String description = recipeDescriptionArea.getText();
            if (description.isEmpty()) {
                displayErrorMessage("Description", "Description cannot be empty!");
                return;
            } else {
                recipe.setDescription(description);
            }

            Integer cookTime = Integer.parseInt(cookTimeField.getText());
            if (cookTimeField.getText().isEmpty()) {
                displayErrorMessage("CookTime", "CookTime cannot be empty!");
                return;
            } else {
                recipe.setCookTime(cookTime);
            }

            Integer servings = Integer.parseInt(servingsField.getText());
            if (servingsField.getText().isEmpty()) {
                displayErrorMessage("Servings", "Servings cannot be empty!");
                return;
            } else {
                recipe.setServings(servings);
            }

            int primaryIngredientID = primaryIngredient.getIngredientID();

//            if (!primaryIngredientName.isEmpty()) {
//                primaryIngredient = Ingredient.getIngredientByName((primaryIngredientName));
//                if (primaryIngredient != null) {
//                    recipe.setPrimaryIngredientID(primaryIngredient.getIngredientID());
//                    recipe.setPrimaryIngredient(primaryIngredient);
//                } else {
//                    recipe.setPrimaryIngredientID(0);
//                    recipe.setPrimaryIngredient(null);
//                }

            if (primaryIngredientID == 0) {
                recipe.setPrimaryIngredientID(0);
                recipe.setPrimaryIngredient(getIngredientByID(0));
            } else {
                recipe.setPrimaryIngredientID(primaryIngredientID);
                recipe.setPrimaryIngredient(getIngredientByID(primaryIngredientID));
            }

            String instructions = directionsArea.getText();
            if (instructions.isEmpty()) {
                displayErrorMessage("Directions", "Directions cannot be empty!");
                return;
            } else {
                recipe.setInstructions(instructions);
            }

            String ingredients = ingredientsField.getText();
            if (ingredients.isEmpty()) {
                displayErrorMessage("Ingredients", "Ingredients cannot be empty!");
                return;
            } else {
                recipe.setIngredients(ingredients);
            }
            try {
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
                    //Inserting nutritional chart to DB
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
                    //Inserting cuisine to DB
                    cuisineID = cuisine.insert(connection);
                } else {
                    cuisineID = 0;
                }
                recipe.setCuisineID(cuisineID);
                recipe.setCuisine(cuisine);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //Inserting recipe to DB
            recipe.insert(connection);
            if (ingredientList.getIngredients().isEmpty()) {

            }
            IngredientRecipeController.addIngredientRecipeToDB(recipe, ingredientList.getIngredients());

        }catch (Exception e) {
            e.printStackTrace();
        }

        clearFields();
        ReturnToRecipeHome(event);
    }

    public void displayErrorMessage(String guiltyField, String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText("Invalid Input in " + guiltyField);
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    public void addIngredientsToRecipe(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/AddIngredientsToRecipe.fxml"));
            Parent root = loader.load();

            Stage newStage = new Stage();
            newStage.setTitle("Add Ingredients to Recipe");
            newStage.setScene(new Scene(root));
            AddIngredientToRecipeController controller = loader.getController();
            controller.setAddToRecipeController(this);
            newStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setIngredientList (IngredientList ingredientList, Ingredient primaryIngredient) {
        this.ingredientList = ingredientList;
        this.primaryIngredient = primaryIngredient;
    }

    public void populateIngredients (IngredientList ingredientList, Ingredient primaryIngredient) {
        StringBuilder sb = new StringBuilder();
        for (Ingredient ingredient : ingredientList.getIngredients()) {
            sb.append(ingredient.getName()).append(",").append("\n");
        }
        if (!sb.isEmpty()) {
            ingredientsField.setText(sb.toString());
        }
if (primaryIngredient != null ) {
    primaryIngredientField.setText(primaryIngredient.getName());
}
    }

}
