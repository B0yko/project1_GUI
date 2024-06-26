package com.example.project1_gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.input.KeyCode;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class Part1CrazyJavaFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Creating buttons
        Button button1 = new Button("Button 1");
        Button button2 = new Button("Button 2");
        Button button3 = new Button("Button 3");
        Button resetButton = new Button("Reset");

        // Creating Text Fields
        TextField textField1 = new TextField("Text Field 1");
        TextField textField2 = new TextField("Text Field 2");
        TextField textField3 = new TextField("Text Field 3");

        // Creating a Text Area and setting its dimensions
        TextArea textArea = new TextArea("Initial Text");
        textArea.setPrefRowCount(5);
        textArea.setMaxWidth(200);

        // Setting TextFields to non-editable by default
        textField1.setEditable(false);
        textField2.setEditable(false);
        textField3.setEditable(false);

        Label label = new Label("Label"); // Creating Label

        CheckBox editModeCheckBox = new CheckBox("Edit Mode"); // Creating CheckBox

        // ToggleGroup creation
        ToggleGroup toggleGroup = new ToggleGroup();

        RadioButton option1Button = new RadioButton("Red");
        RadioButton option2Button = new RadioButton("Green");
        RadioButton option3Button = new RadioButton("Blue");

        // Adding Radio Buttons to the toggle group
        option1Button.setToggleGroup(toggleGroup);
        option2Button.setToggleGroup(toggleGroup);
        option3Button.setToggleGroup(toggleGroup);

        Region spacer = new Region(); // Creating a spacer for installing passes in containers

        VBox textBox = new VBox(50); // Creating VBox for text elements
        textBox.getChildren().addAll(textField1, textField2, label, textField3, option1Button, option2Button, option3Button, textArea);

        VBox buttonBox = new VBox(50); // Creating VBox for buttons
        buttonBox.getChildren().addAll(spacer, button1, button2, button3, resetButton);

        // Creating ComboBox for selecting colors
        ComboBox<String> colorComboBox = new ComboBox<>();
        colorComboBox.getItems().addAll("Black", "Red", "Green", "Blue");

        // Creating the main HBox
        HBox root = new HBox(50);
        root.setPadding(new Insets(30));
        root.getChildren().addAll(editModeCheckBox, textBox, buttonBox, colorComboBox);

        // Assigning commands to buttons
        button1.setOnAction(event -> textField2.setText(textField1.getText()));
        button2.setOnAction(event -> textField3.setText(textField2.getText()));
        button3.setOnAction(event -> textField1.setText(textField3.getText()));

        // Assigning commands to reset button
        resetButton.setOnAction(event -> {
            textField1.setText("Text Field 1");
            textField2.setText("Text Field 2");
            textField3.setText("Text Field 3");

            textField1.setStyle("-fx-background-color: white; -fx-border-color: black;");
            textField2.setStyle("-fx-background-color: white; -fx-border-color: black;");
            textField3.setStyle("-fx-background-color: white; -fx-border-color: black;");

            root.setStyle("-fx-background-color: white;");
        });

        // Assigning commands to color selection ComboBox
        colorComboBox.setOnAction(event -> {
            String selectedColor = colorComboBox.getValue();
            switch (selectedColor) {
                case "Black":
                    textField1.setStyle("-fx-text-fill: black;");
                    textField2.setStyle("-fx-text-fill: black;");
                    textField3.setStyle("-fx-text-fill: black;");
                    break;
                case "Red":
                    textField1.setStyle("-fx-text-fill: red;");
                    textField2.setStyle("-fx-text-fill: red;");
                    textField3.setStyle("-fx-text-fill: red;");
                    break;
                case "Green":
                    textField1.setStyle("-fx-text-fill: green;");
                    textField2.setStyle("-fx-text-fill: green;");
                    textField3.setStyle("-fx-text-fill: green;");
                    break;
                case "Blue":
                    textField1.setStyle("-fx-text-fill: blue;");
                    textField2.setStyle("-fx-text-fill: blue;");
                    textField3.setStyle("-fx-text-fill: blue;");
                    break;
            }
        });

        // Assigning commands to Enter key pressed on TextField2
        textField2.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                label.setText(textField2.getText());
            }
        });

        // Assigning commands to Enter key pressed on TextArea
        textArea.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                label.setText(textArea.getText());
            }
        });

        // Assigning commands to edit mode CheckBox
        editModeCheckBox.setOnAction(event -> {
            boolean editMode = editModeCheckBox.isSelected();
            textField1.setEditable(editMode);
            textField2.setEditable(editMode);
            textField3.setEditable(editMode);
        });

        // Event listener for radio button selection change
        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            RadioButton selectedRadioButton = (RadioButton) newValue;
            if (selectedRadioButton != null) {
                String color = selectedRadioButton.getText();
                switch (color) {
                    case "Red":
                        root.setStyle("-fx-background-color: red;");
                        break;
                    case "Green":
                        root.setStyle("-fx-background-color: green;");
                        break;
                    case "Blue":
                        root.setStyle("-fx-background-color: blue;");
                        break;
                }
            }
        });

        // Creating and setting the scene
        Scene scene = new Scene(root, 700, 900);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Crazy JavaFX");
        primaryStage.show();
    }
}
