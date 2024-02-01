package com.example.caloriecounter;

import javafx.event.EventHandler;
import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

public class firstWindow {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button newUserButton;

    @FXML
    private Button oldUserButton;

    @FXML
    void initialize() throws IOException {


        newUserButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->
                HelloApplication.setButton(newUserButton,"registrationWindow.fxml"));

        oldUserButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->{
                HelloApplication.setButton(oldUserButton, "enterWindow.fxml");
        });


    }

}
