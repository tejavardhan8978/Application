package edu.metrostate.Controller;

import edu.metrostate.Model.IngredientList;
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
import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class InventoryController implements Initializable {
    Stage stage;
    Scene scene;
    Parent root;
    IngredientList ingredientList;
    public static Ingredient tempIngredient;

    @FXML private TableView<Ingredient> inventoryTable;
    @FXML private TableColumn<Ingredient, Integer> idColumn;
    @FXML private TableColumn<Ingredient, String> name;
    @FXML private TableColumn<Ingredient, Date> expiryDate;
    @FXML private TableColumn<Ingredient, MacroNutrient> primaryMacroNutrient;
    @FXML private TableColumn<Ingredient, Storage> storage;
    @FXML private TableColumn<Ingredient, Integer> quantity;
    @FXML private TableColumn<Ingredient, String> category;

    public void switchToHome(MouseEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("/Views/HomeScreen.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToAddIngredient(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/Views/AddToInventory.fxml"));
        Parent root = loader.load();
        // Get the controller
        AddIngredientToInventoryController controller = loader.getController();
        // Pass the ingredientList to the IngredientQuantityController
        controller.setIngredientList(this.ingredientList, this);
        // Switch to the new scene
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ingredientID"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        expiryDate.setCellValueFactory(new PropertyValueFactory<>("expiryDate"));
        primaryMacroNutrient.setCellValueFactory(new PropertyValueFactory<>("primaryMacroNutrient"));
        storage.setCellValueFactory(new PropertyValueFactory<>("storage"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        category.setCellValueFactory(new PropertyValueFactory<>("category"));
        updateTableView();
    }

    public void updateTableView() {
        System.out.println("you've reached the update");
        List<Ingredient> ingredients = IngredientListSingleton.getInstance().getIngredients();
        ObservableList<Ingredient> items = FXCollections.observableArrayList(ingredients);
        inventoryTable.setItems(items);
        inventoryTable.refresh();
    }

    public void openIngredientModal(MouseEvent event) throws IOException {
        if (event.getClickCount() == 2) {
            Ingredient tempIngredient = inventoryTable.getSelectionModel().getSelectedItem();
            if (tempIngredient != null) {
                System.out.println(tempIngredient);

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/IngredientDetailedModal.fxml"));

                AnchorPane ingredientModal = fxmlLoader.load();
                IngredientPopupController ingredientPopupController = fxmlLoader.getController();
                ingredientPopupController.setInventoryController(this);
                ingredientPopupController.setIngredientModalDetails(tempIngredient);


                Stage modalStage = new Stage();
                Scene modalScene = new Scene(ingredientModal);
                modalStage.setScene(modalScene);
                modalStage.setTitle("Ingredient Popup");
                modalStage.initModality(Modality.APPLICATION_MODAL);

                modalStage.show();
                ingredientPopupController.setIngredientPopupStage(modalStage);

            }
        }
    }

    public void switchToEditIngredient(MouseEvent event) throws IOException {
        if (inventoryTable.getSelectionModel().getSelectedItem() != null) {
            tempIngredient = inventoryTable.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/Views/ChangeQuantityInventoryModal.fxml"));
            Parent root = loader.load();
            // Get the controller
            EditIngredient controller = loader.getController();
            // Switch to the new scene
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
}