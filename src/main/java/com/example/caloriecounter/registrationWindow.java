package com.example.caloriecounter;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

public class registrationWindow {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button continueButton;

    @FXML
    private TextField enterNewLogin;

    @FXML
    private TextField enterNewPassword;

    ContinueRegistrationController crc = new ContinueRegistrationController();

    @FXML
    void initialize() {
        continueButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                    String loginText = enterNewLogin.getText().trim();
                    String loginPassword = enterNewPassword.getText().trim();

                    if (!loginText.equals("") && !loginPassword.equals("")) {
                        MainWindow.login=enterNewLogin.getText();

                        try {
                            DataBaseHandler handler = new DataBaseHandler();
                            handler.signUpUser(enterNewLogin.getText(), enterNewPassword.getText());
                            HelloApplication.setButton(continueButton, "continueRegistration.fxml");
                        } catch (SQLException | ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        Shake shakeLogin = new Shake(enterNewLogin);
                        Shake shakePass = new Shake(enterNewPassword);

                        shakeLogin.playAnim();
                        shakePass.playAnim();
                    }
                });
    }
}


