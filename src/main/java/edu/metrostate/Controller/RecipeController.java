package edu.metrostate.Controller;

import edu.metrostate.Main;
import edu.metrostate.Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RecipeController implements Initializable {
    Stage stage;
    Scene scene;
    Parent root;
    RecipeListModel recipeList;

    //Create fields for the table view and its columns
    @FXML private TableView<Recipe> recipeTable;
    @FXML private TableColumn<Recipe, Integer> idColumn;
    @FXML private TableColumn<Recipe, String> nameColumn;
    @FXML private TableColumn<Recipe, Cuisine> cuisineColumn;
    @FXML private TableColumn<Recipe, Integer> cookTimeColumn;
    @FXML private TableColumn<Recipe, Integer> servingsColumn;
    @FXML private TableColumn<Recipe, Ingredient> primaryIngredientColumn;

    //Switches back to the home screen
    public void switchToHome(MouseEvent event) throws IOException {
        root = FXMLLoader.load(Main.class.getResource("/HomeScreen.fxml"));
        this.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToAddRecipe(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/AddToRecipe.fxml"));
        Parent root = loader.load();
        //Retrieve the controller associated with the addToRecipe and store it in controller
        AddToRecipeController controller = loader.getController();
        //Call on the controller and pass the recipe list and pass the Recipe controller allowing the AddToRecipe controller
        //to modify the list of recipes
        controller.setRecipeList(this.recipeList, this);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Set up singleton
        recipeList = RecipeListSingleton.getInstance();
        //Setup columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        cuisineColumn.setCellValueFactory(new PropertyValueFactory<>("cuisine"));
        cookTimeColumn.setCellValueFactory(new PropertyValueFactory<>("cookTime"));
        servingsColumn.setCellValueFactory(new PropertyValueFactory<>("servings"));
        primaryIngredientColumn.setCellValueFactory(new PropertyValueFactory<>("primaryIngredient"));
        //load default data for testing
        recipeTable.setItems(getRecipeItems());
    }

    public ObservableList<Recipe> getRecipeItems() {
        ObservableList<Recipe> recipeList = FXCollections.observableArrayList();
        recipeList.add(new Recipe(1, "Steak", new Cuisine("American", "America"), "Big chunk of meat", 20, 1, "Steak", new NutritionalChart(), "Steak, Butter, Spices", "cook till medium rare"));
        recipeList.add(new Recipe(2, "Steak", new Cuisine("American", "America"), "Big chunk of meat", 25, 1, "Steak", new NutritionalChart(), "Steak, Butter, Spices", "cook till medium rare"));
        recipeList.add(new Recipe(3, "Steak", new Cuisine("American", "America"), "Big chunk of meat", 23, 1, "Steak", new NutritionalChart(), "Steak, Butter, Spices", "cook till medium rare"));
        recipeList.add(new Recipe(4, "Steak", new Cuisine("American", "America"), "Big chunk of meat", 22, 1, "Steak", new NutritionalChart(), "Steak, Butter, Spices", "cook till medium rare"));

        return recipeList;
    }

    //Adds a new recipe to the list and updates the view
    public void addRecipe(Recipe recipe){
        recipeList.addRecipe(recipe);
        System.out.println("You have added a recipe!");
    }

    //Prints the current recipes in the list
    public void updateTableView(){
        System.out.println("You've reached the update");
        ObservableList<Recipe> items = FXCollections.observableArrayList(recipeList.getRecipes());
        recipeTable.setItems(items);
        recipeTable.refresh();
    }

    //Sets the recipe list model for the controller
    public void setRecipeList(RecipeListModel recipeList, RecipeController recipeController){
        this.recipeList = recipeList;
    }

    @FXML
    public void openModal(MouseEvent event) throws IOException {

        if (event.getClickCount() == 2) {
            Recipe tempRecipe = recipeTable.getSelectionModel().getSelectedItem();
            if (tempRecipe != null) {
                System.out.println(tempRecipe);

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/RecipeDetailedModal.fxml"));
                //fxmlLoader.setLocation(getClass().getResource("/RecipeDetailedModal.fxml"));
                AnchorPane recipeModal = fxmlLoader.load();
                RecipePopupController recipePopupController = fxmlLoader.getController();
                recipePopupController.setRecipeController(this);
                recipePopupController.setRecipeModalDetails(tempRecipe);


                Stage modalStage = new Stage();
                Scene modalScene = new Scene(recipeModal);
                modalStage.setScene(modalScene);
                modalStage.setTitle("Recipe Popup");
                modalStage.initModality(Modality.APPLICATION_MODAL);

                modalStage.show();
            }
        }
    }
}