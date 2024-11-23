package edu.metrostate.Controller;

import edu.metrostate.Main;
import edu.metrostate.Model.Database;
import edu.metrostate.Model.Ingredient;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

public class ChangeQuantityInventoryModalController implements Initializable {

    private Stage changeQuantityInventoryModalstage;
    private Ingredient ingredient;

    @FXML private TextField newExpiry;
    @FXML private TextField inStock;
    @FXML private TextField addQuantity;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Inventory Quantity modal is initialized");

    }

    public void handleSaveButton(MouseEvent event) {
        int tempaddition = Integer.parseInt(addQuantity.getText());
        String sDate = newExpiry.getText();
        LocalDate expiryDate = LocalDate.parse(sDate);
        Date expiryDateObj = java.sql.Date.valueOf(expiryDate);

        if (expiryDateObj != null) {
            ingredient.setExpiryDate(expiryDateObj);
        }
        //increment the quantity if user entered positive number
        if (tempaddition > 0) {
            ingredient.setQuantity(ingredient.getQuantity() + tempaddition);
        }
        handleBackButton(event);
    }

    public void handleBackButton(MouseEvent event) {
        System.out.println("Closing Ingredient Quantity modal");
        changeQuantityInventoryModalstage.close();
    }

    public void setChangeQuantityInventoryModalstage(Stage stage) {
        this.changeQuantityInventoryModalstage = stage;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
        inStock.setText(String.valueOf(ingredient.getQuantity()));
        System.out.println(ingredient + "in setIngredient in change quantity inventory modal controller");
    }


//    @FXML
//    private TextField addQuantity;
//    @FXML private TextField newExpiry;
//    private Stage stage;
//    private Scene scene;
//    private Parent root;
//
//    public void IngredientBackButton(MouseEvent event) throws IOException {
//        root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("/Views/Inventory-Home.fxml")));
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
//    public void saveButton(MouseEvent event) throws IOException {
//
//        Ingredient tempIngredient = InventoryController.tempIngredient;
//        String quantityString = addQuantity.getText();
//        int quantity = Integer.parseInt(quantityString);
//        int tempQuantity = tempIngredient.getQuantity() + quantity;
//
//
//        String DateString = newExpiry.getText();
//        LocalDate TempDate = LocalDate.parse(DateString);
//        java.sql.Date updateDate = java.sql.Date.valueOf(TempDate);
//
//
//        //IngredientList ingredientList = IngredientListSingleton.getInstance();
//        //ingredientList.updateIngredient(tempIngredient, tempQuantity, updateDate);
//        UpdateData(tempIngredient.getIngredientID(), tempQuantity, updateDate);
//        root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("/Views/Inventory-Home.fxml")));
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
//    public static void UpdateData(int updateID, int updateQuantity, java.sql.Date newExpiry){
//        String query ="UPDATE IngredientTable SET expiryDate = ?, quantity = ? WHERE ingredientID = ?";
//        try (Connection conn= Database.getConnection();
//             PreparedStatement preparedStatement = conn.prepareStatement(query)){
//            preparedStatement.setDate(1, newExpiry);
//            preparedStatement.setInt(2, updateQuantity);
//            preparedStatement.setInt(3, updateID);
//
//            int rowsAffected = preparedStatement.executeUpdate();
//            System.out.println("DID THE UPDATE");
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//
//
//
//
//
//    }
}
