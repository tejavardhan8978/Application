package edu.metrostate.Controller;

import edu.metrostate.Main;
import edu.metrostate.Model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;

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

    public void setSaveButton(MouseEvent event)throws IOException {
    //Collect String input data
    int id = 0;
    String recipeName = recipeNameField.getText();
    String primaryIngredient = primaryIngredientField.getText();
    String recipeDescription = recipeDescriptionArea.getText();
    String instructions = directionsArea.getText();
    String ingredients = ingredientsField.getText();
    String cuisineName = cuisineNameField.getText();
    String cuisineCountry = cuisineOriginField.getText();

    //Parse Int input data
    try{
        int servings = Integer.parseInt(servingsField.getText());
        int cookTime = Integer.parseInt(cookTimeField.getText());

        int servingSize = Integer.parseInt(servingSizeField.getText());
        int calories = Integer.parseInt(caloriesField.getText());
        int totalCarbohydrates = Integer.parseInt(carbsField.getText());
        int totalFat = Integer.parseInt(fatField.getText());
        int totalProtein = Integer.parseInt(proteinField.getText());
        int totalSodium = Integer.parseInt(sodiumField.getText());
        int totalSugars = Integer.parseInt(sugarField.getText());
        int dietaryFiber = Integer.parseInt(fiberField.getText());
        int cholesterol = Integer.parseInt(cholesterolField.getText());

        // Create a NutritionalChart object
        NutritionalChart nutrition = new NutritionalChart(servingSize, calories, totalCarbohydrates, totalFat,
                cholesterol, dietaryFiber, totalProtein, totalSodium, totalSugars);

        //Create a cuisine object
        Cuisine cuisine = new Cuisine(cuisineName, cuisineCountry);

        //Create the new recipe
        Recipe newRecipe = new Recipe(
            id,
            recipeName,
            cuisine,
            recipeDescription,
            cookTime,
            servings,
            primaryIngredient,
            nutrition,
            ingredients,
            instructions
        );

        System.out.println("Creating a new recipe: " + newRecipe);
        recipeController.addRecipe(newRecipe);

        //Clear the fields and go back to the recipe table view
        clearFields();
        ReturnToRecipeHome(event);

    }catch (Exception e){
            e.printStackTrace();
        }
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
    public void setCancelButton(MouseEvent event) throws IOException {
        clearFields();
        ReturnToRecipeHome(event);
    }

    public void ReturnToRecipeHome(MouseEvent event) throws IOException {
//        Node source = (Node) event.getSource();
//        Stage stage = (Stage) source.getScene().getWindow();
//        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/Recipe-Home.fxml"));
//        Parent root = loader.load();
//        //Retrieve the controller associated with the addToRecipe and store it in controller
//        RecipeController recipeController = loader.getController();
//        //Sets a recipe list from the recipeController and pass the singleton list and the reference to the controller.
//        recipeController.setRecipeList(RecipeListSingleton.getInstance(), recipeController);
//        //Calls to update the table view after a successful addition.
//        recipeController.updateTableView();
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
        recipeController.updateTableView();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void setRoot(Parent root) {
        this.root = root;
    }

}
