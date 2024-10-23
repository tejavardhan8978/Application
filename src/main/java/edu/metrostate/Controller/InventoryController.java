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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;

public class InventoryController implements Initializable {
    Stage stage;
    Scene scene;
    Parent root;
    IngredientList ingredientList;

    @FXML private TableView<Ingredient> inventoryTable;
    @FXML private TableColumn<Ingredient, Integer> idColumn;
    @FXML private TableColumn<Ingredient, String> name;
    @FXML private TableColumn<Ingredient, Date> expiryDate;
    @FXML private TableColumn<Ingredient, MacroNutrient> primaryMacroNutrient;
    @FXML private TableColumn<Ingredient, Storage> storage;
    @FXML private TableColumn<Ingredient, Integer> quantity;
    @FXML private TableColumn<Ingredient, String> category;

    public void switchToHome(MouseEvent event) throws IOException {
        root = FXMLLoader.load(Main.class.getResource("/HomeScreen.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToAddIngredient(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/AddToInventory.fxml"));
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
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        ingredientList = IngredientListSingleton.getInstance();
//        updateTableView();
    public void initialize(URL url, ResourceBundle resourceBundle){
        ingredientList = IngredientListSingleton.getInstance();
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        expiryDate.setCellValueFactory(new PropertyValueFactory<>("expiryDate"));
        primaryMacroNutrient.setCellValueFactory(new PropertyValueFactory<>("primaryMacroNutrient"));
        storage.setCellValueFactory(new PropertyValueFactory<>("storage"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        category.setCellValueFactory(new PropertyValueFactory<>("category"));
        updateTableView();
        inventoryTable.setItems(getIngredientItems());
        inventoryTable.refresh();
    }

    public ObservableList<Ingredient> getIngredientItems() {
        ObservableList<Ingredient> ingredientList = FXCollections.observableArrayList();
//        ingredientList.add(new Ingredient(1, "Chicken", new java.util.Date(), new NutritionalChart(), MacroNutrient.PROTEIN, Storage.FRIDGE, 5, "Meat", "good meat"));
//        ingredientList.add(new Ingredient(2, "Mutton", new java.util.Date(), new NutritionalChart(), MacroNutrient.PROTEIN, Storage.FRIDGE, 5, "Meat", "best meat"));
//        ingredientList.add(new Ingredient(3, "Broccoli", new java.util.Date(), new NutritionalChart(), MacroNutrient.FIBER, Storage.FRIDGE, 5, "Vegetable", "Least favoured vegetable"));
//        ingredientList.add(new Ingredient(4, "Beans", new java.util.Date(), new NutritionalChart(), MacroNutrient.FIBER, Storage.FRIDGE, 5, "Vegetable", "no comments"));

        return ingredientList;

    }

    public void addIngredient(Ingredient ingredient) {
        ingredientList.addIngredient(ingredient);
        System.out.println("You have added an ingredient!");
    }

    public void updateTableView() {
        System.out.println("you've reached the update");
        ObservableList<Ingredient> items = FXCollections.observableArrayList(ingredientList.getIngredients());
        inventoryTable.setItems(items);
        inventoryTable.refresh();
    }

    public void setIngredientList(IngredientList ingredientList, InventoryController inventoryController) {
        this.ingredientList = ingredientList;
    }

    public void openIngredientModal(MouseEvent event) throws IOException {
        if (event.getClickCount() == 2) {
            Ingredient tempIngredient = inventoryTable.getSelectionModel().getSelectedItem();
            if (tempIngredient != null) {
                System.out.println(tempIngredient);

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/IngredientDetailedModal.fxml"));

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
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/ChangeQuantityInventoryModal.fxml"));
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


    public static Ingredient tempIngredient;
}