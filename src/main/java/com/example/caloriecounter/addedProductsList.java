package com.example.caloriecounter;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.w3c.dom.Text;

public class addedProductsList {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Product> addedProductsTable;

    @FXML
    private Button backToMainWIndowButton;

    @FXML
    private TableColumn<?, ?> productCalories;

    @FXML
    private TableColumn<?, ?> productCarbs;

    @FXML
    private TableColumn<?, ?> productFats;

    @FXML
    private TableColumn<?, ?> productName;

    @FXML
    private TableColumn<?, ?> productProteins;
    @FXML
    private TableColumn<?, ?> productAmount;
    static String login;
    static String mealName;

    @FXML
    private Label text;

    DataBaseHandler dataBaseHandler = new DataBaseHandler();
    ObservableList list;

    void updateList() {

        try {
            list = dataBaseHandler.getProducts(login, mealName);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }}

    private TableRow<Product> currentRow;




    @FXML
    void initialize() {
        updateList();
        text.setText(text.getText()+" "+mealName.toLowerCase()+ " продукты");

        backToMainWIndowButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            HelloApplication.setButton(backToMainWIndowButton,"mainWindow.fxml");
        });

        productCalories.setCellValueFactory(new PropertyValueFactory<>("productCalories"));
        productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        productCarbs.setCellValueFactory(new PropertyValueFactory<>("productCarbs"));
        productProteins.setCellValueFactory(new PropertyValueFactory<>("productProteins"));
        productFats.setCellValueFactory(new PropertyValueFactory<>("productFats"));
        productAmount.setCellValueFactory(new PropertyValueFactory<>("productAmount"));

        addedProductsTable.setItems(list);

        addedProductsTable.getSelectionModel().setCellSelectionEnabled(false);
        addedProductsTable.setRowFactory(tableView -> {
            final TableRow<Product> row = new TableRow<>();
            row.setOnMousePressed(event -> HelloApplication.openNewWindow("deleteProduct.fxml"));
            return row;
        });


        TableView.TableViewSelectionModel<Product> selectionModel = addedProductsTable.getSelectionModel();
        selectionModel.selectedItemProperty().addListener(new ChangeListener<Product>(){

            public void changed(ObservableValue<? extends Product> val, Product oldVal, Product newVal){
                if(newVal != null) {

                    System.out.println(newVal.getProductName());
                    DeleteProductController.amount=newVal.getProductAmount();
                    DeleteProductController.productName=newVal.getProductName();
                    DeleteProductController.mealName=mealName;
                    DeleteProductController.login=login;

                }

            }

        });

    }


}
