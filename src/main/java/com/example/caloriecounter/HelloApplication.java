package com.example.caloriecounter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("firstWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);
        stage.setTitle("calorie counter");
        stage.setScene(scene);
        stage.show();
    }

    public static void setText(String fxmlName, String text) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxmlName));
        Parent root = fxmlLoader.load();
        Label label = (Label) fxmlLoader.getNamespace().get("label");
        label.setText(text);
    }

    public static void setButton(Button button,String name){
            button.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource(name));

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
        public static void openNewWindow(String name){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource(name));

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





    public static void main(String[] args) {
        launch();
    }
}