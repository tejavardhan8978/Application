package edu.metrostate.Controller;

import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ChangeQuantityInventoryModalController implements Initializable {

    private Stage changeQuantityInventoryModalstage;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Inventory Quantity modal is initialized");
    }

    public void handleBackButton(MouseEvent event) {
        System.out.println("Closing Ingredient Quantity modal");
        changeQuantityInventoryModalstage.close();
    }

    public void setChangeQuantityInventoryModalstage(Stage stage) {
        this.changeQuantityInventoryModalstage = stage;
    }
}
