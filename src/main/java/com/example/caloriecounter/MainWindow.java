package com.example.caloriecounter;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainWindow {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addBreakfastButton;

    @FXML
    private Button addDinnerButton;

    @FXML
    private Button addLunchButton;

    @FXML
    private Text addedCaloriesBreakfast;

    @FXML
    private Text addedCaloriesDinner;

    @FXML
    private Text addedCaloriesLunch;

    @FXML
    private Text addedCarbsBreakfast;

    @FXML
    private Text addedCarbsDinner;

    @FXML
    private Text addedCarbsLunch;

    @FXML
    private Text addedFatsBreakfast;

    @FXML
    private Text addedFatsDinner;

    @FXML
    private Text addedFatsLunch;

    @FXML
    private Text addedProteinsBreakfast;

    @FXML
    private Text addedProteinsDinner;

    @FXML
    private Text addedProteinsLunch;

    @FXML
    private Text neededCaloriesText;

    @FXML
    private Text neededCarbsText;

    @FXML
    private Text neededFatsText;

    @FXML
    private Text neededProteinText;

    @FXML
    private Button seeBreakfastButton;

    @FXML
    private Button seeDinnerButton;

    @FXML
    private Button seeLunchButton;
    static String login;
    @FXML
    private Text allAddedCaloriesText;

    @FXML
    private Text allAddedCarbsText;

    @FXML
    private Text allAddedFatsText;

    @FXML
    private Text allAddedProteinText;

void setBreakfastButtonHide(){
    seeBreakfastButton.getScene().getWindow().hide();
}
void setLunchButtonHide(){
    seeLunchButton.getScene().getWindow().hide();
}void setDinnerButtonHide(){
    seeDinnerButton.getScene().getWindow().hide();
}

    @FXML
    void initialize() throws InterruptedException {
        ArrayList<Text> breakfast = new ArrayList(Arrays.asList(addedProteinsBreakfast,addedFatsBreakfast,addedCarbsBreakfast,addedCaloriesBreakfast));
        ArrayList<Text> lunch = new ArrayList(Arrays.asList(addedProteinsLunch,addedFatsLunch,addedCarbsLunch,addedCaloriesLunch));
        ArrayList<Text> dinner = new ArrayList(Arrays.asList(addedProteinsDinner,addedFatsDinner,addedCarbsDinner,addedCaloriesDinner));
        ArrayList<Text> all = new ArrayList(Arrays.asList(allAddedProteinText,allAddedFatsText,allAddedCarbsText,allAddedCaloriesText));

        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        for(int i=0;i<4;i++){
            String breakfastAmount=dataBaseHandler.getNeededNutrient("added_products_2",login,breakfast.get(i).getText(),"Завтрак");
            String lunchAmount=dataBaseHandler.getNeededNutrient("added_products_2",login,lunch.get(i).getText(),"Обед");
            String dinnerAmount=dataBaseHandler.getNeededNutrient("added_products_2",login,dinner.get(i).getText(),"Ужин");
            int summ=Integer.valueOf(breakfastAmount)+Integer.valueOf(lunchAmount)+Integer.valueOf(dinnerAmount);

            breakfast.get(i).setText(breakfast.get(i).getText()+":"+breakfastAmount);
            lunch.get(i).setText(lunch.get(i).getText()+":"+lunchAmount);
            dinner.get(i).setText(dinner.get(i).getText()+":"+dinnerAmount);

            all.get(i).setText(all.get(i).getText()+":"+summ);
        }

        seeLunchButton.addEventHandler(MouseEvent.MOUSE_CLICKED,mouseEvent -> {
            addedProductsList.mealName="Обед";
            HelloApplication.setButton(seeLunchButton,"addedProductsList.fxml");
            DeleteProductController.button=seeLunchButton;

        });
        seeDinnerButton.addEventHandler(MouseEvent.MOUSE_CLICKED,mouseEvent -> {
            addedProductsList.mealName="Ужин";
            HelloApplication.setButton(seeDinnerButton,"addedProductsList.fxml");
            DeleteProductController.button=seeDinnerButton;
        });
        seeBreakfastButton.addEventHandler(MouseEvent.MOUSE_CLICKED,mouseEvent -> {
            addedProductsList.mealName="Завтрак";
            HelloApplication.setButton(seeBreakfastButton,"addedProductsList.fxml");
            DeleteProductController.button=seeBreakfastButton;

        });


        AddProductController.login=login;
        addedProductsList.login=login;

        neededProteinText.setText(neededProteinText.getText()+":"+dataBaseHandler.getNeededNutrient("users",login,"Белки",""));
        neededFatsText.setText(neededFatsText.getText()+":"+dataBaseHandler.getNeededNutrient("users",login,"Жиры",""));
        neededCarbsText.setText(neededCarbsText.getText()+":"+dataBaseHandler.getNeededNutrient("users",login,"Углеводы",""));
        neededCaloriesText.setText(neededCaloriesText.getText()+":"+dataBaseHandler.getNeededNutrient("users",login,"Калории",""));

        addBreakfastButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            HelloApplication.setButton(addBreakfastButton,"products_list.fxml");
            AddProductController.mealName="Завтрак";
        });
        addDinnerButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            HelloApplication.setButton(addDinnerButton,"products_list.fxml");
            AddProductController.mealName="Ужин";
        });
        addLunchButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            HelloApplication.setButton(seeLunchButton,"products_list.fxml");
            AddProductController.mealName="Обед";
        });


}


}
