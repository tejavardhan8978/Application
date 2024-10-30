package edu.metrostate.Controller;

import edu.metrostate.Model.Recipe;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RecipePopupController implements Initializable {

    private RecipeController recipeController;
    private Stage RecipeControllerStage;

    @FXML private Text recipeTitle;
    @FXML private Text recipeCuisine;
    @FXML private Text recipeDescription;
    @FXML private TextArea recipeInstructions;

    @FXML private Text caloriesColumn;
    @FXML private Text carbohydratesColumn;
    @FXML private Text fatColumn;
    @FXML private Text proteinColumn;
    @FXML private Text sodiumColumn;
    @FXML private Text sugarsColumn;
    @FXML private Text dietaryFiberColumn;
    @FXML private Text cholesterolColumn;
    @FXML private Text servingSizeColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initializing RecipePopupController");
    }

    @FXML
    public void handleBackButtonClick(MouseEvent event) {
        System.out.println("Closing RecipePopupController");
        Stage modalStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        modalStage.close();
    }

    @FXML
    public void handleHomeButtonClick(MouseEvent event) throws IOException {
        System.out.println("Closing RecipePopupController and returning to HomeScreen");
        Stage modalStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        modalStage.close();
        if (this.recipeController != null) {
            recipeController.switchToHome(event);
        }
    }

    public void setRecipeController(RecipeController recipeController) {
        this.recipeController = recipeController;
    }

    public void setStage(Stage stage) {
        this.RecipeControllerStage = stage;
    }

    public void setRecipeModalDetails(Recipe recipe) {
        this.recipeTitle.setText(recipe.getName());
        this.recipeCuisine.setText(recipe.getCuisine().getName());
        this.recipeDescription.setText(recipe.getDescription());
        this.recipeInstructions.setText(recipe.getInstructions());
        this.caloriesColumn.setText(String.valueOf(recipe.getNutrition().getCalories()));
        this.servingSizeColumn.setText(String.valueOf(recipe.getNutrition().getServingSize()));
        this.carbohydratesColumn.setText(String.valueOf(recipe.getNutrition().getTotalCarbohydrates()));
        this.fatColumn.setText(String.valueOf(recipe.getNutrition().getTotalFat()));
        this.proteinColumn.setText(String.valueOf(recipe.getNutrition().getTotalProtein()));
        this.sugarsColumn.setText(String.valueOf(recipe.getNutrition().getTotalSugars()));;
        this.sodiumColumn.setText(String.valueOf(recipe.getNutrition().getTotalSodium()));
        this.dietaryFiberColumn.setText(String.valueOf(recipe.getNutrition().getDietaryFiber()));
        this.cholesterolColumn.setText(String.valueOf(recipe.getNutrition().getCholesterol()));

    }
}
