package edu.metrostate.Controller;

import edu.metrostate.Main;
import edu.metrostate.Model.Database;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;



public class EditIngredient {
    @FXML
    private TextField addQuantity;
    @FXML private TextField newExpiry;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void IngredientBackButton(MouseEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("/Views/Inventory-Home.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.close();
    }
    public void saveButton(MouseEvent event) throws IOException {

        Ingredient tempIngredient = InventoryController.tempIngredient;
        String quantityString = addQuantity.getText();
        int quantity = Integer.parseInt(quantityString);
        int tempQuantity = tempIngredient.getQuantity() + quantity;


        String DateString = newExpiry.getText();
        LocalDate TempDate = LocalDate.parse(DateString);
        Date updateDate = java.sql.Date.valueOf(TempDate);


        //IngredientList ingredientList = IngredientListSingleton.getInstance();
        //ingredientList.updateIngredient(tempIngredient, tempQuantity, updateDate);
        UpdateData(tempIngredient.getIngredientID(), tempQuantity, updateDate);
        root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("/Views/Inventory-Home.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.close();
    }
    public static void UpdateData(int updateID, int updateQuantity, Date newExpiry){
        String query ="UPDATE IngredientTable SET expiryDate = ?, quantity = ? WHERE ingredientID = ?";
        try (Connection conn= Database.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)){
            preparedStatement.setDate(1, newExpiry);
            preparedStatement.setInt(2, updateQuantity);
            preparedStatement.setInt(3, updateID);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("DID THE UPDATE");
        }catch (SQLException e){
            e.printStackTrace();
        }





    }
}