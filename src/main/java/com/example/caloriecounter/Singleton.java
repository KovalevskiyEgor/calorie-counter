package com.example.caloriecounter;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public final class Singleton {
    private static Singleton instance;
    public String value;

    Singleton(String value) {
        this.value = value;
    }

    public static Singleton getInstance(String value) {
        if (instance == null) {
            instance = new Singleton(value);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("mainWindow.fxml"));

            try{
                loader.load();
            }catch (IOException e){
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }


        return instance;
    }
}
