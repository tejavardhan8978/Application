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
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.time.LocalDate;
import java.sql.Date;
import java.util.ResourceBundle;

public class InventoryController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML private TableView<Ingredient> InventoryTable;
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
    @Override
    public void initialize(URL arg0, ResourceBundle resourceBundle){
        idColumn.setCellValueFactory(new PropertyValueFactory<Ingredient, Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("name"));
        expiryDate.setCellValueFactory(new PropertyValueFactory<Ingredient, Date>("expiryDate"));
        primaryMacroNutrient.setCellValueFactory(new PropertyValueFactory<Ingredient, MacroNutrient>("primaryMacroNutrient"));
        storage.setCellValueFactory(new PropertyValueFactory<Ingredient, Storage>("storage"));
        quantity.setCellValueFactory(new PropertyValueFactory<Ingredient, Integer>("quantity"));
        category.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("category"));
        InventoryTable.setItems(getItems());
    }

    public ObservableList<Ingredient> getItems() {
        ObservableList<Ingredient> inventoryList = FXCollections.observableArrayList();
        LocalDate localExpiryDate = LocalDate.parse("2024-10-03");
        Date expiryDate = Date.valueOf(localExpiryDate);
        inventoryList.add(new Ingredient(01, "Apple", expiryDate, new NutritionalChart(1, 1, 1, 1, 1, 1, 1, 1, 1), MacroNutrient.CARBOHYDRATE, Storage.FRIDGE, 1, "Fruit"));


        return inventoryList;

    }
}