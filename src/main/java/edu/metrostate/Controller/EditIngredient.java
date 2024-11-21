package edu.metrostate.Controller;

import edu.metrostate.Main;
import edu.metrostate.Model.Ingredient;
import edu.metrostate.Model.IngredientList;
import edu.metrostate.Model.IngredientListSingleton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

public class EditIngredient {
    @FXML
    private TextField addedQuantity;
    @FXML private TextField newExpiration;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void IngredientBackButton(MouseEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("/Views/Inventory-Home.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void saveButton(MouseEvent event) throws IOException {
        Ingredient tempIngredient = InventoryController.tempIngredient;
        String quantityString = addedQuantity.getText();
        int quantity = Integer.parseInt(quantityString);
        int tempQuantity = tempIngredient.getQuantity() + quantity;


        String DateString = newExpiration.getText();
        LocalDate TempDate = LocalDate.parse(DateString);
        Date updateDate = java.sql.Date.valueOf(TempDate);

        //IngredientList ingredientList = IngredientListSingleton.getInstance();
        //ingredientList.updateIngredient(tempIngredient, tempQuantity, updateDate);

        root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("/Views/Inventory-Home.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}