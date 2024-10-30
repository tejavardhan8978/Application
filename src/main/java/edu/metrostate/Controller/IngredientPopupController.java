package edu.metrostate.Controller;

import edu.metrostate.Model.Ingredient;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.Console;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class IngredientPopupController implements Initializable {

    private InventoryController inventoryController;
    private Stage ingredientPopupStage;

    @FXML
    private Text ingredientTitle;
    @FXML
    private Text ingredientCategory;
    @FXML
    private Text ingredientDescription;
    @FXML
    private TextField ingredientStock;
    @FXML
    private TableView ingredientNutritionChartTable1;
    @FXML
    private TableView ingredientNutritionChartTable2;
    @FXML
    private TableView ingredientRecipeTable;
    private Stage inventoryControllerStage;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initializing Ingredient pop up screen");
    }

    @FXML
    public void handleBackButtonClick(MouseEvent event) {
        System.out.println("Closing Ingredient Popup");
        Stage modalStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        modalStage.close();
    }

    @FXML
    public void handleHomeButtonClick(MouseEvent event) throws IOException {
        System.out.println("Closing IngredientPopupController and returning to HomeScreen");
        Stage modalStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        modalStage.close();
        if (this.inventoryController != null) {
            System.out.println(inventoryController);
            inventoryController.backToHome(event);
        }
    }

    public void setInventoryController(InventoryController inventoryController) {
        this.inventoryController = inventoryController;
    }

    public void setStage(Stage stage) {
        this.inventoryControllerStage = stage;
    }

    public void setIngredientModalDetails(Ingredient ingredient) {
        this.ingredientTitle.setText(ingredient.getName());
        this.ingredientCategory.setText(ingredient.getCategory());
        this.ingredientDescription.setText(ingredient.getDescription());
    }

    public void handleAddButtonClick(MouseEvent event) throws IOException {
        System.out.println("Opening add quantity modal");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ChangeQuantityModal.fxml"));
        Pane addModal = fxmlLoader.load();

        Stage modalStage = new Stage();
        Scene modalScene = new Scene(addModal);

        modalStage.setScene(modalScene);
        modalStage.setTitle("Add to Ingredient Popup");
        modalStage.initModality(Modality.APPLICATION_MODAL);

        modalStage.show();

        ChangeQuantityModalController changeQuantityModalController = fxmlLoader.getController();
        changeQuantityModalController.setChangeQuantityModalStage(modalStage);

    }

    public void setIngredientPopupStage(Stage stage) {
        this.ingredientPopupStage = stage;
    }
}
