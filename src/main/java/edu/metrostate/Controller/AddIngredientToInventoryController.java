package edu.metrostate.Controller;

import edu.metrostate.Main;
import edu.metrostate.Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.time.LocalDate;
import java.util.Objects;

public class AddIngredientToInventoryController {
    @FXML private TextField itemNameField;
    @FXML private TextField expiryDateField;
    @FXML private TextField servingSizeField;
    @FXML private TextField quantityField;
    @FXML private TextField caloriesField;
    @FXML private TextField carbsField;
    @FXML private TextField fatField;
    @FXML private TextField proteinField;
    @FXML private TextField sodiumField;
    @FXML private TextField sugarField;
    @FXML private TextField fiberField;
    @FXML private TextField cholesterolField;
    @FXML private TextArea itemDescriptionArea;
    @FXML private ListView<String> macroNutrientListView;
    @FXML private ListView<String> storageListView;
    @FXML private ListView<String> categoryListView;

    @FXML private TextArea fileNameDisplay;
    private File file;

    IngredientList ingredientList;
    private InventoryController inventoryController;
    private Parent root;
    private Stage stage;

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
        String name = itemNameField.getText();
        String category = categoryListView.getSelectionModel().getSelectedItem();
        String expiryDateString = expiryDateField.getText();
        String quantityString = quantityField.getText();
        String description = itemDescriptionArea.getText();

        try {
            // Parse expiry date
            LocalDate expiryDate = LocalDate.parse(expiryDateString);
            Date expiryDateObj = java.sql.Date.valueOf(expiryDate);

            // Parse quantity
            int quantity = Integer.parseInt(quantityString);

            // Gather nutritional information
            int servingSize = Integer.parseInt(servingSizeField.getText());
            int calories = Integer.parseInt(caloriesField.getText());
            int totalCarbohydrates = Integer.parseInt(carbsField.getText());
            int totalFat = Integer.parseInt(fatField.getText());
            int totalProtein = Integer.parseInt(proteinField.getText());
            int totalSodium = Integer.parseInt(sodiumField.getText());
            int totalSugars = Integer.parseInt(sugarField.getText());
            int dietaryFiber = Integer.parseInt(fiberField.getText());
            int cholesterol = Integer.parseInt(cholesterolField.getText());

            // Create a NutritionalChart object
            //!!!!!ID of nutritional chart needs to be accounted for!!!!!!
            NutritionalChart nutrition = new NutritionalChart(id, servingSize, calories, totalCarbohydrates, totalFat,
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

            newIngredient.setImage(file);

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
        itemNameField.clear();
        expiryDateField.clear();
        quantityField.clear();
        caloriesField.clear();
        carbsField.clear();
        fatField.clear();
        proteinField.clear();
        sodiumField.clear();
        sugarField.clear();
        fiberField.clear();
        cholesterolField.clear();
        itemDescriptionArea.clear();
    }

    //Go back to the referenced Inventory Controller to display changes
    public void ReturnToInventoryHome(MouseEvent event) throws IOException{
        inventoryController.updateTableView();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void setRoot(Parent root) {
        this.root = root;
    }

    @FXML
    public void imageChooser(MouseEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpeg", "*.png"));
        file = fc.showOpenDialog(null);

        if (file != null) {
            fileNameDisplay.setText(file.getPath());

        }
    }

}