package edu.metrostate.Controller;

import edu.metrostate.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class AddIngredientToInventoryControllerV2 {

    private Stage stage;
    private Scene scene;
    private Parent root;


    public void switchToInventoryCancel(MouseEvent event) throws IOException {
       // root = FXMLLoader.load(Main.class.getResource("/Inventory-Home.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }






}
