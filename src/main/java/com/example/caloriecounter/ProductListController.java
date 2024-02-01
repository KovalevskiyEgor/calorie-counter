package com.example.caloriecounter;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class ProductListController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField findProduct;
    @FXML
    private TableColumn<Product, String> productCalories;

    @FXML
    private TableColumn<?, ?> productCarbs;

    @FXML
    private TableColumn<?, ?> productFats;

    @FXML
    private TableColumn<?, ?> productName;

    @FXML
    private TableColumn<?, ?> productProteins;
    @FXML
    private TableView<Product> productsTable;
    DataBaseHandler dataBaseHandler = new DataBaseHandler();
    ObservableList list;
    @FXML
    private Button backToMainWIndowButton;
    @FXML
    private Button findProductButton;
    private TableRow<Product> currentRow;

    {
        try {
            list = dataBaseHandler.getProducts();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productCalories.setCellValueFactory(new PropertyValueFactory<>("productCalories"));
        productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        productCarbs.setCellValueFactory(new PropertyValueFactory<>("productCarbs"));
        productProteins.setCellValueFactory(new PropertyValueFactory<>("productProteins"));
        productFats.setCellValueFactory(new PropertyValueFactory<>("productFats"));

        //productsTable.setItems(list);

        FilteredList<Product> filteredList = new FilteredList<>(list, t->true);

        findProduct.textProperty().addListener((observable, oldValue,newValue)->{
            filteredList.setPredicate(product -> {
                if(newValue==null||newValue.isEmpty()) return true;

                String lowerCaseFilter=newValue.toLowerCase();

                if(product.getProductName().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                    return true;
                }
                return false;
            });
                }
                );
        SortedList<Product> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(productsTable.comparatorProperty());

        productsTable.setItems(sortedList);


//        if(!findProduct.getText().equals("")){
//            try {
//                list=dataBaseHandler.findProduct(findProduct.getText());
//                productsTable.setItems(list);
//            }
//            catch (Exception e) {
//                Shake shake = new Shake(findProduct);
//                shake.playAnim();
//            }}




        backToMainWIndowButton.addEventHandler(MouseEvent.MOUSE_CLICKED,mouseEvent -> {
            HelloApplication.setButton(backToMainWIndowButton,"mainWindow.fxml");
        });

        productsTable.getSelectionModel().setCellSelectionEnabled(false);
        productsTable.setRowFactory(tableView -> {
            final TableRow<Product> row = new TableRow<>();
            row.setOnMousePressed(event -> {
                HelloApplication.openNewWindow("add_product.fxml");

            });
            return row;
        });


        TableView.TableViewSelectionModel<Product> selectionModel = productsTable.getSelectionModel();
        selectionModel.selectedItemProperty().addListener(new ChangeListener<Product>(){

            public void changed(ObservableValue<? extends Product> val, Product oldVal, Product newVal){
                if(newVal != null) {
                    AddProductController.productName=newVal.getProductName();
                    AddProductController.fats=newVal.getProductFats();
                    AddProductController.proteins=newVal.getProductProteins();
                    AddProductController.calories=newVal.getProductCalories();
                    AddProductController.carbo=newVal.getProductCarbs();
                    System.out.println(newVal.getProductName());
                }

            }

        });





    }

}
