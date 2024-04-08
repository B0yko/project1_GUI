module com.example.project1_gui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.project1_gui to javafx.fxml;
    exports com.example.project1_gui;
}