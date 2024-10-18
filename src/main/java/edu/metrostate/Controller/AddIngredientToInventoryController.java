package edu.metrostate.Controller;

import edu.metrostate.Main;
import edu.metrostate.Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;
import java.time.LocalDate;

public class AddIngredientToInventoryController {
    @FXML private TextArea itemNameArea;
    @FXML private TextArea expiryDateArea;
    @FXML private TextArea servingSizeArea;
    @FXML private TextArea quantityArea;
    @FXML private TextArea caloriesArea;
    @FXML private TextArea carbsArea;
    @FXML private TextArea fatArea;
    @FXML private TextArea proteinArea;
    @FXML private TextArea sodiumArea;
    @FXML private TextArea sugarArea;
    @FXML private TextArea fiberArea;
    @FXML private TextArea cholesterolArea;
    @FXML private ListView<String> macroNutrientListView;
    @FXML private ListView<String> storageListView;
    @FXML private ListView<String> categoryListView;

    private IngredientList ingredientList;
    private InventoryController inventoryController;

    @FXML
    public void initialize() {
        // Populate the MacroNutrient ListView
        ObservableList<String> macroNutrientOptions = FXCollections.observableArrayList();
        for (MacroNutrient nutrient : MacroNutrient.values()) {
            macroNutrientOptions.add(nutrient.name());
        }
        macroNutrientListView.setItems(macroNutrientOptions);

        // Populate the Storage ListView
        ObservableList<String> storageOptions = FXCollections.observableArrayList();
        for (Storage storage : Storage.values()) {
            storageOptions.add(storage.name());
        }
        storageListView.setItems(storageOptions);

        //Populate the Category ListView
        ObservableList<String> categoryOptions = FXCollections.observableArrayList();
        for (Category category : Category.values()){
            categoryOptions.add(category.name());
        }
        categoryListView.setItems(categoryOptions);
    }

    // Set reference to InventoryController
    public void setIngredientList(IngredientList ingredientList, InventoryController inventoryController) {
        this.ingredientList = ingredientList;
        this.inventoryController = inventoryController;
    }

    @FXML
    public void setSaveButton(MouseEvent event) throws IOException {
        int id =0;
        String name = itemNameArea.getText();
        String category = categoryListView.getSelectionModel().getSelectedItem();
        String expiryDateString = expiryDateArea.getText();
        String quantityString = quantityArea.getText();
        String description = "CREATE FIELD FOR DESCRIPTION!!";

        try {
            // Parse expiry date
            LocalDate expiryDate = LocalDate.parse(expiryDateString);
            Date expiryDateObj = java.sql.Date.valueOf(expiryDate);

            // Parse quantity
            int quantity = Integer.parseInt(quantityString);

            // Gather nutritional information
            int servingSize = Integer.parseInt(servingSizeArea.getText());
            int calories = Integer.parseInt(caloriesArea.getText());
            int totalCarbohydrates = Integer.parseInt(carbsArea.getText());
            int totalFat = Integer.parseInt(fatArea.getText());
            int totalProtein = Integer.parseInt(proteinArea.getText());
            int totalSodium = Integer.parseInt(sodiumArea.getText());
            int totalSugars = Integer.parseInt(sugarArea.getText());
            int dietaryFiber = Integer.parseInt(fiberArea.getText());
            int cholesterol = Integer.parseInt(cholesterolArea.getText());

            // Create a NutritionalChart object
            NutritionalChart nutrition = new NutritionalChart(servingSize, calories, totalCarbohydrates, totalFat,
                    cholesterol, dietaryFiber, totalProtein, totalSodium, totalSugars);

            //Gets the selected enum's
            MacroNutrient selectedMacroNutrient = MacroNutrient.valueOf(macroNutrientListView.getSelectionModel().getSelectedItem());
            Storage selectedStorage = Storage.valueOf(storageListView.getSelectionModel().getSelectedItem());

            // Create a new Ingredient object
            
            Ingredient newIngredient = new Ingredient(
                    id,
                    name,
                    expiryDateObj,
                    nutrition, // Use the created NutritionalChart
                    selectedMacroNutrient, // Use selected macro nutrient from the ListView
                    selectedStorage, // Use selected storage from the ListView
                    quantity,
                    category,
                    description

            );

            //Add the new ingredient to the list
            System.out.println("Creating new ingredient: " + newIngredient);
            inventoryController.addIngredient(newIngredient);

            // Clear fields after saving
            clearFields();
            ReturnToInventoryHome(event);

        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    public void setCancelButton(MouseEvent event) throws IOException {
        clearFields();
        ReturnToInventoryHome(event);
    }

    private void clearFields() {
        itemNameArea.clear();
        expiryDateArea.clear();
        quantityArea.clear();
        caloriesArea.clear();
        carbsArea.clear();
        fatArea.clear();
        proteinArea.clear();
        sodiumArea.clear();
        sugarArea.clear();
        fiberArea.clear();
        cholesterolArea.clear();
    }

    //Go back to the referenced Inventory Controller to display changes
    public void ReturnToInventoryHome(MouseEvent event) throws IOException{
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/Inventory-Home.fxml"));
        Parent root = loader.load();
        InventoryController inventoryController = loader.getController();
        inventoryController.setIngredientList(IngredientListSingleton.getInstance(), inventoryController); // Keep the reference
        inventoryController.updateTableView();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}