package com.example.caloriecounter;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AddProductController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField enterProductWeight;

    @FXML
    private Button addProductButton;
    static String productName;
    static String login;
    static String proteins;
    static String fats;
    static String carbo;
    static String calories;
    static String mealName;



    @FXML
    void initialize() {
        addProductButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            try {
                DataBaseHandler dataBaseHandler = new DataBaseHandler();
                dataBaseHandler.addProduct(mealName,Integer.valueOf(enterProductWeight.getText()),login,productName,Integer.valueOf(proteins),Integer.valueOf(fats),Integer.valueOf(carbo),Integer.valueOf(calories));
                addProductButton.getScene().getWindow().hide();
            }catch (Exception e){
                Shake shakePass = new Shake(enterProductWeight);
                shakePass.playAnim();
            }

        });

    }

}
