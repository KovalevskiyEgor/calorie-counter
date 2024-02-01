module com.example.caloriecounter {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.caloriecounter to javafx.fxml;
    exports com.example.caloriecounter;
}