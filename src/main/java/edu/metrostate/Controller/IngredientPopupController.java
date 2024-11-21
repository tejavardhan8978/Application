package edu.metrostate.Controller;

import edu.metrostate.Model.Ingredient;
import edu.metrostate.Model.NutritionalChart;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.io.IOException;
import java.net.URL;
import java.util.PrimitiveIterator;
import java.util.ResourceBundle;

public class IngredientPopupController implements Initializable {

    private InventoryController inventoryController;
    private Stage ingredientPopupStage;

    @FXML private Text ingredientTitle;
    @FXML private Text ingredientCategory;
    @FXML private Text ingredientDescription;
    @FXML private TextField ingredientStock;
    @FXML private TableView ingredientRecipeTable;
    private Stage inventoryControllerStage;
    private Ingredient ingredient;

    @FXML private Text caloriesColumn;
    @FXML private Text carbohydratesColumn;
    @FXML private Text fatColumn;
    @FXML private Text proteinColumn;
    @FXML private Text sodiumColumn;
    @FXML private Text sugarsColumn;
    @FXML private Text dietaryFiberColumn;
    @FXML private Text cholesterolColumn;
    @FXML private Text servingSizeColumn;
    @FXML private ImageView imageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initializing Ingredient pop up screen");
    }

    @FXML
    public void handleBackButtonClick(MouseEvent event) {
        System.out.println("Closing Ingredient Popup");
        Stage modalStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        modalStage.close();
        inventoryController.updateTableView();
    }

    @FXML
    public void handleHomeButtonClick(MouseEvent event) throws IOException {
        System.out.println("Closing IngredientPopupController and returning to HomeScreen");
        Stage modalStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        modalStage.close();
        if (this.inventoryController != null) {
            System.out.println(inventoryController);
            inventoryController.backToHome(event);
        }
    }

    public void setInventoryController(InventoryController inventoryController) {
        this.inventoryController = inventoryController;
    }

    public void setStage(Stage stage) {
        this.inventoryControllerStage = stage;
    }

    public void setIngredientModalDetails(Ingredient ingredient) {
        //saving ingredient as a local variable
        setIngredient(ingredient);
        //setting details of all text fields
        this.ingredientTitle.setText(ingredient.getName());
        this.ingredientCategory.setText(ingredient.getCategory() != null ? ingredient.getCategory().name() : "No Category");
        this.ingredientDescription.setText(ingredient.getDescription() == null || ingredient.getDescription().isEmpty() ? "No Description" : ingredient.getDescription());
        if (ingredient.getNutrition() != null) {
            this.caloriesColumn.setText(String.valueOf(ingredient.getNutrition().getCalories()));
            this.servingSizeColumn.setText(String.valueOf(ingredient.getNutrition().getServingSize()));
            this.carbohydratesColumn.setText(String.valueOf(ingredient.getNutrition().getTotalCarbohydrates()));
            this.fatColumn.setText(String.valueOf(ingredient.getNutrition().getTotalFat()));
            this.proteinColumn.setText(String.valueOf(ingredient.getNutrition().getTotalProtein()));
            this.sugarsColumn.setText(String.valueOf(ingredient.getNutrition().getTotalSugars()));
            this.sodiumColumn.setText(String.valueOf(ingredient.getNutrition().getTotalSodium()));
            this.dietaryFiberColumn.setText(String.valueOf(ingredient.getNutrition().getDietaryFiber()));
            this.cholesterolColumn.setText(String.valueOf(ingredient.getNutrition().getCholesterol()));
        }
        refreshIngredientStock();
        try {
            displayImage(ingredient.getImage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleAddButtonClick(MouseEvent event) throws IOException {
        System.out.println("Opening add quantity modal");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/ChangeQuantityModal.fxml"));
        Pane addModal = fxmlLoader.load();

        Stage modalStage = new Stage();
        Scene modalScene = new Scene(addModal);

        modalStage.setScene(modalScene);
        modalStage.setTitle("Add to Ingredient Popup");
        modalStage.initModality(Modality.APPLICATION_MODAL);

        modalStage.show();

        ChangeQuantityModalController changeQuantityModalController = fxmlLoader.getController();
        changeQuantityModalController.setChangeQuantityModalStage(modalStage);
        changeQuantityModalController.setIngredient(ingredient);
        changeQuantityModalController.setIngredientPopupController(this);

    }

    public void setIngredientPopupStage(Stage stage) {
        this.ingredientPopupStage = stage;
    }

    public void displayImage(File file) throws IOException {
        if (file != null) {
            try {
                // Read BufferedImage
                BufferedImage bufferedImage = ImageIO.read(file);

                // Convert BufferedImage to byte array
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "png", outputStream);
                ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

                // Create JavaFX Image from byte array
                Image image = new Image(inputStream);
                imageView.setImage(image);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public void refreshIngredientStock() {
        this.ingredientStock.setText(String.valueOf(ingredient.getQuantity()));
    }
}
