package edu.metrostate.Controller;

import edu.metrostate.Model.IngredientList;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class InventoryController implements Initializable {
    Stage stage;
    Scene scene;
    Parent root;
    IngredientList ingredientList;
    private boolean initialInventoryFlag = false;
    public static Ingredient tempIngredient;

    @FXML private TableView<Ingredient> inventoryTable;
    @FXML private TableColumn<Ingredient, Integer> idColumn;
    @FXML private TableColumn<Ingredient, String> name;
    @FXML private TableColumn<Ingredient, Date> expiryDate;
    @FXML private TableColumn<Ingredient, MacroNutrient> primaryMacroNutrient;
    @FXML private TableColumn<Ingredient, Storage> storage;
    @FXML private TableColumn<Ingredient, Integer> quantity;
    @FXML private TableColumn<Ingredient, String> category;
    @FXML private TextField searchBar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialize start - Inventory controller");
        ingredientList = IngredientListSingleton.getInstance().getIngredients();
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ingredientID"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        expiryDate.setCellValueFactory(new PropertyValueFactory<>("expiryDate"));
        primaryMacroNutrient.setCellValueFactory(new PropertyValueFactory<>("primaryMacroNutrient"));
        storage.setCellValueFactory(new PropertyValueFactory<>("storage"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        category.setCellValueFactory(new PropertyValueFactory<>("category"));

        updateTableView();
        inventoryTable.refresh();
        // Add this line in your initialize method to bind the search to the search bar
        searchBar.textProperty().addListener((observable, oldValue, newValue) -> onSearch());
        System.out.println("Initialize end - Inventory controller");
    }

    public void switchToHome(MouseEvent event) throws IOException {
        root = FXMLLoader.load(Main.class.getResource("/Views/HomeScreen.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        System.out.println(stage);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToAddIngredient(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/Views/AddToInventory.fxml"));
        Parent root = loader.load();
        // Get the controller
        AddIngredientToInventoryController controller = loader.getController();
        controller.setRoot(root);
        // Pass the ingredientList to the IngredientQuantityController
        controller.setIngredientList(this.ingredientList, this);
        // Switch to the new scene
        stage = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToEditIngredient(MouseEvent event) throws IOException {
        if (inventoryTable.getSelectionModel().getSelectedItem() != null) {
            tempIngredient = inventoryTable.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/Views/ChangeQuantityInventoryModal.fxml"));
            Parent root = loader.load();
            // Get the controller
            EditIngredient controller = loader.getController();
            // Switch to the new scene
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void updateTableView() {
        System.out.println("updateTableView - inventoryController");
        System.out.println("Ingredients loaded: " + ingredientList.getIngredients().size());
        ObservableList<Ingredient> items = FXCollections.observableArrayList(ingredientList.getIngredients());
        inventoryTable.setItems(items);
        inventoryTable.refresh();
    }

    public void openIngredientModal(MouseEvent event) throws IOException {
        if (event.getClickCount() == 2) {
            Ingredient tempIngredient = inventoryTable.getSelectionModel().getSelectedItem();
            if (tempIngredient != null) {
                System.out.println(tempIngredient);

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/IngredientDetailedModal.fxml"));

                AnchorPane ingredientModal = fxmlLoader.load();
                IngredientPopupController ingredientPopupController = fxmlLoader.getController();
                ingredientPopupController.setInventoryController(this);
                ingredientPopupController.setIngredientModalDetails(tempIngredient);


                stage = new Stage();
                scene = new Scene(ingredientModal);
                stage.setScene(scene);
                stage.setTitle("Ingredient Popup");
                stage.initModality(Modality.APPLICATION_MODAL);
                System.out.println(this);

                stage.show();
                ingredientPopupController.setIngredientPopupStage(stage);

            }
        }
    }

    public InventoryController getController() {
        return this;
    }

    public void backToHome(MouseEvent event) throws IOException {
        System.out.println("Going back to home");
        switchToHome(event);
    }

    private void performIngredientSearch(String searchTerm) {
        String query = "SELECT * FROM IngredientTable WHERE name LIKE ?";

        try (Connection conn = Database.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                // Set the search term to filter by ingredient name
                stmt.setString(1, "%" + searchTerm + "%");

                // Execute the query and get the results
                ResultSet rs = stmt.executeQuery();

                ObservableList<Ingredient> searchResults = FXCollections.observableArrayList();

                // Process the result set and add matching ingredients to the list
                while (rs.next()) {
                    int ingredientID = rs.getInt("ingredientID");
                    String name = rs.getString("name");
                    Date expiryDate = rs.getDate("expiryDate");
                    int quantity = rs.getInt("quantity");

                    //Ensure that we do not try to get an enum for a null value
                    try {
                        String primaryMacroString = rs.getString("primaryMacroNutrient");
                        MacroNutrient primaryMacroNutrient = null;
                        if (primaryMacroString != null){
                            primaryMacroNutrient = MacroNutrient .valueOf(primaryMacroString.toUpperCase());
                        }

                        String storageString = rs.getString("storage");
                        Storage storage = null;
                        if (storageString != null){
                            storage = Storage.valueOf(storageString.toUpperCase());
                        }

                        String categoryString = rs.getString("category");
                        Category category = null;
                        if (categoryString != null){
                            category = Category.valueOf(categoryString.toUpperCase());
                        }


                        Ingredient ingredient = new Ingredient.Builder()
                                .ingredientID(ingredientID)
                                .name(name)
                                .expiryDate(expiryDate)
                                .quantity(quantity)
                                .primaryMacroNutrient(primaryMacroNutrient)
                                .storage(storage)
                                .category(category)

                                .build();

                        searchResults.add(ingredient);
                        Database.dbDisconnect();
                    } catch (SQLException e){
                        System.out.println("One of the searched for items has missing information");
                    }
                }

                // Update the table with the search results
                inventoryTable.setItems(searchResults);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Called when the user types in the search bar
    @FXML
    public void onSearch() {
        String searchTerm = searchBar.getText().trim();
        performIngredientSearch(searchTerm);
    }
}














