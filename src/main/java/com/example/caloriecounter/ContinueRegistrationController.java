package com.example.caloriecounter;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class ContinueRegistrationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField ageFIeld;

    @FXML
    private Button endRegistration;

    @FXML
    private RadioButton femaleRadioButton;

    @FXML
    private ToggleGroup goal;

    @FXML
    private TextField heightField;

    @FXML
    private ToggleGroup male;

    @FXML
    private RadioButton maleRadioButton;

    @FXML
    private RadioButton naborRadioButton;

    @FXML
    private RadioButton podderzhanieRadioButton;

    @FXML
    private RadioButton pohudeniRadioButton;

    @FXML
    private TextField wightField;

    private String login;
    private String password;

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @FXML
    void initialize(){

        endRegistration.setOnAction(actionEvent -> {

            int weight = Integer.parseInt(wightField.getText());
            DataBaseHandler handler = new DataBaseHandler();
            int calories = (int) (447.593 + (9.247 * Integer.valueOf(wightField.getText())) + (3.098 * Integer.valueOf(heightField.getText()) - (4.330) * Integer.valueOf(ageFIeld.getText())));
            double proteins = weight * 1.8;
            double carbo;
            double fats = weight * 0.7;
            double ccal;


                if (naborRadioButton.isSelected()) {
                    carbo = weight * 7;
                    ccal = proteins * 4 + carbo * 4 + fats * 8;
                    handler.signUpUser(ccal, proteins, carbo, fats);
                } else if (podderzhanieRadioButton.isSelected()) {
                    carbo = weight * 4;
                    ccal = proteins * 4 + carbo * 4 + fats * 8;
                    handler.signUpUser(ccal, proteins, carbo, fats);
                } else {
                    carbo = weight * 2;
                    ccal = proteins * 4 + carbo * 4 + fats * 8;
                    handler.signUpUser(ccal, proteins * 1.1, carbo, fats);
                }


            HelloApplication.setButton(endRegistration, "mainWindow.fxml");
        });



        }


    }


