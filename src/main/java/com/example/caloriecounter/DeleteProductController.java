package com.example.caloriecounter;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class DeleteProductController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField changeProductWeight;

    @FXML
    private Button deleteProductButton;

    @FXML
    private Button saveChangesProductButton;
    static String amount;
    static String productName;
    static String mealName;
    static String login;

    DataBaseHandler dataBaseHandler = new DataBaseHandler();
    static Button button;

    @FXML
    void initialize() {
        addedProductsList addedProductsList = new addedProductsList();

        changeProductWeight.setText(amount);

        saveChangesProductButton.addEventHandler(MouseEvent.MOUSE_CLICKED,mouseEvent -> {
            try {
                button.getScene().getWindow().hide();
                dataBaseHandler.updateProduct(productName,amount,changeProductWeight.getText(),mealName,login);

                //HelloApplication.setButton(saveChangesProductButton,"addedProductsList.fxml");
                saveChangesProductButton.getScene().getWindow().hide();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        deleteProductButton.addEventHandler(MouseEvent.MOUSE_CLICKED,mouseEvent -> {
            try {
                dataBaseHandler.delete(productName,amount,mealName,login);
                button.getScene().getWindow().hide();
                //HelloApplication.setButton(deleteProductButton,"addedProductsList.fxml");
                deleteProductButton.getScene().getWindow().hide();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });


    }

}
