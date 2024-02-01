package com.example.caloriecounter;

import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class enterWindow {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button enterButton;

    @FXML
    private TextField enterOldLogin;

    @FXML
    private TextField enterOldPassword;

    private String loginText;
    String loginPassword;
    public String getOldLogin() {
        return loginText;
    }
    SoftReference softReference = new SoftReference<>(enterOldLogin);
    SoftReference softReference2 = new SoftReference<>(enterOldPassword);


    @FXML
    void initialize() {
        DataBaseHandler handler=new DataBaseHandler();

        enterButton.setOnAction(actionEvent -> {
           loginText = enterOldLogin.getText().trim();
           loginPassword = enterOldPassword.getText().trim();

           if(!loginText.equals("")&&!loginPassword.equals("")&&handler.signInUser(loginText,loginPassword)){
               MainWindow.login=enterOldLogin.getText().trim();

               HelloApplication.setButton(enterButton,"mainWindow.fxml");
           }
           else{
               Shake shakePass = new Shake(enterOldPassword);
               Shake shakeLogin = new Shake(enterOldLogin);

               shakePass.playAnim();
               shakeLogin.playAnim();
           }
       });
    }



}
