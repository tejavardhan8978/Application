package edu.metrostate.Controller;

import edu.metrostate.Main;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ChangeQuantityModalController implements Initializable {

    private Stage changeQuantityModalStage;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initializing Change Quantity Modal");
    }

    public void handleAddToInventoryClick(MouseEvent event) throws IOException {
        System.out.println("Opening Add to inventory quantity modal");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ChangeQuantityInventoryModal.fxml"));
        Pane addInventoryModal = fxmlLoader.load();

        Stage modalStage = new Stage();
        Scene modalScene = new Scene(addInventoryModal);

        modalStage.setScene(modalScene);
        modalStage.setTitle("Add to Inventory Quantity");
        modalStage.initModality(Modality.APPLICATION_MODAL);

        modalStage.show();
        ChangeQuantityInventoryModalController inventoryModalController = fxmlLoader.getController();
        inventoryModalController.setChangeQuantityInventoryModalstage(modalStage);

    }

    public void handleAddToGroceryListClick(MouseEvent event) throws IOException {
        System.out.println("Opening Add to Grocery list modal");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ChangeQuantityGroceryListModal.fxml"));
        Pane addToGroceryModal = fxmlLoader.load();

        Stage modalStage = new Stage();
        Scene modalScene = new Scene(addToGroceryModal);

        modalStage.setScene(modalScene);
        modalStage.setTitle("Add to Grocery List Quantity");
        modalStage.initModality(Modality.APPLICATION_MODAL);

        modalStage.show();
        ChangeQuantityGroceryListModalController groceryListModalController = fxmlLoader.getController();
        groceryListModalController.setChangeQuantityGroceryListModalStage(modalStage);

    }

    public void handleBackButtonClick(MouseEvent event) {
        System.out.println("Close add to quantity modal");
        this.changeQuantityModalStage.close();
    }

    public void setChangeQuantityModalStage(Stage stage) {
        this.changeQuantityModalStage = stage;
    }

    //This is for the ChangeQuantityInventoryModal
    public void BackButton(MouseEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("/Inventory-Home.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }






}
