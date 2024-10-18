package edu.metrostate.Controller;

import edu.metrostate.Main;
import edu.metrostate.Model.Cuisine;
import edu.metrostate.Model.Ingredient;
import edu.metrostate.Model.IngredientList;
import edu.metrostate.Model.Recipe;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RecipeController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML private TableView<Recipe> recipeTable;
    @FXML private TableColumn<Recipe, Integer> idColumn;
    @FXML private TableColumn<Recipe, String> nameColumn;
    @FXML private TableColumn<Recipe, Cuisine> cuisineColumn;
    @FXML private TableColumn<Recipe, Integer> cookTimeColumn;
    @FXML private TableColumn<Recipe, Integer> servingsColumn;
    @FXML private TableColumn<Recipe, Ingredient> primaryIngredientColumn;
    @FXML private TableColumn<Recipe, Integer> caloriesColumn;


    public void switchToHome(MouseEvent event) throws IOException {
        root = FXMLLoader.load(Main.class.getResource("/HomeScreen.fxml"));
        this.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Setup columns
        idColumn.setCellValueFactory(new PropertyValueFactory<Recipe, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Recipe, String>("name"));
        cuisineColumn.setCellValueFactory(new PropertyValueFactory<Recipe, Cuisine>("cuisine"));
        cookTimeColumn.setCellValueFactory(new PropertyValueFactory<Recipe, Integer>("cookTime"));
        servingsColumn.setCellValueFactory(new PropertyValueFactory<Recipe, Integer>("servings"));
        primaryIngredientColumn.setCellValueFactory(new PropertyValueFactory<Recipe, Ingredient>("primaryIngredient"));
        caloriesColumn.setCellValueFactory(new PropertyValueFactory<Recipe, Integer>("calories"));

        //load data
        recipeTable.setItems(getRecipeItems());
    }

    public ObservableList<Recipe> getRecipeItems() {
        ObservableList<Recipe> recipeList = FXCollections.observableArrayList();
        recipeList.add(new Recipe(01, "Steak", new Cuisine("American", "America"), "Big chunk of meat", 20, 1, new Ingredient(), 500, new IngredientList(), "cook till medium rare"));
        recipeList.add(new Recipe(01, "Steak", new Cuisine("American", "America"), "Big chunk of meat", 25, 1, new Ingredient(), 500, new IngredientList(), "cook till medium rare"));
        recipeList.add(new Recipe(01, "Steak", new Cuisine("American", "America"), "Big chunk of meat", 23, 1, new Ingredient(), 500, new IngredientList(), "cook till medium rare"));
        recipeList.add(new Recipe(01, "Steak", new Cuisine("American", "America"), "Big chunk of meat", 22, 1, new Ingredient(), 500, new IngredientList(), "cook till medium rare"));

        return recipeList;
    }

    @FXML
    public void openModal(MouseEvent event) throws IOException {

        if (event.getClickCount() == 2) {
            Recipe tempRecipe = recipeTable.getSelectionModel().getSelectedItem();
            if (tempRecipe != null) {
                System.out.println(tempRecipe.toString());

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

    public void handleCloseToHome(MouseEvent event) throws IOException {
        this.stage.close();
        root = FXMLLoader.load(Main.class.getResource("/HomeScreen.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}