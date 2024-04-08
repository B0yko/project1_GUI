package com.example.project1_gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Part3AUKQuestionary extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Creating the root GridPane
        GridPane root = new GridPane();
        root.setPadding(new Insets(20));
        root.setHgap(10);
        root.setVgap(10);

        // Creating Labels and TextFields for user input
        Label label1 = new Label("Name:");
        TextField nameField = new TextField();

        Label label2 = new Label("Surname:");
        TextField surnameField = new TextField();

        Label label3 = new Label("Age:");
        TextField ageField = new TextField();

        Label label4 = new Label("Phone number:");
        TextField phoneField = new TextField();

        Label label5 = new Label("Email:");
        TextField emailField = new TextField();

        Label label6 = new Label("GPA:");
        TextField gpaField = new TextField();

        Label label7 = new Label("Program:");
        ChoiceBox<String> majorChoice = new ChoiceBox<>(); // Creating a CheckBox to select a program
        majorChoice.getItems().addAll("BBA", "BGM", "BMS", "BDS", "BSE");
        majorChoice.setValue("BBA"); // Setting the program default value

        Label label8 = new Label("Motivational letter:");

        // Creating a Text Area and setting its dimensions
        TextArea letterArea = new TextArea();
        letterArea.setPrefRowCount(7);
        letterArea.setMaxWidth(500);

        // Creating TextArea for reporting and displaying errors
        TextArea reportArea = new TextArea();
        letterArea.setPrefRowCount(7);
        letterArea.setMaxWidth(500);

        // Creating the submit button
        Button button1 = new Button("Submit");

        // Creating the save button
        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> saveUserInformation(nameField.getText(), surnameField.getText(), letterArea.getText()));

        // Creating labels for displaying errors
        Label errorLabel1 = new Label();
        Label errorLabel2 = new Label();
        Label errorLabel3 = new Label();
        Label errorLabel4 = new Label();
        Label errorLabel5 = new Label();
        Label errorLabel6 = new Label();

        // Assigning commands to submit button
        button1.setOnAction(event -> {
            // Clearing previous error messages
            errorLabel1.setText("");
            errorLabel2.setText("");
            errorLabel3.setText("");
            errorLabel4.setText("");
            errorLabel5.setText("");
            errorLabel6.setText("");

            boolean isValid = true;

            // Validating user input
            if (nameField.getText().isEmpty()) {
                errorLabel1.setText("Name is required");
                errorLabel1.setStyle("-fx-text-fill: red;");
                isValid = false;
            }

            if (surnameField.getText().isEmpty()) {
                errorLabel2.setText("Surname is required");
                errorLabel2.setStyle("-fx-text-fill: red;");
                isValid = false;
            }

            try {
                int age = Integer.parseInt(ageField.getText());
                if (age < 0 || age > 100) {
                    errorLabel3.setText("Invalid age");
                    errorLabel3.setStyle("-fx-text-fill: red;");
                    isValid = false;
                }
            } catch (NumberFormatException e) {
                errorLabel3.setText("Invalid age");
                errorLabel3.setStyle("-fx-text-fill: red;");
                isValid = false;
            }

            try {
                double gpa = Double.parseDouble(gpaField.getText());
                if (gpa < 0 || gpa > 4) {
                    errorLabel4.setText("GPA must be between 0 and 4.\n");
                    errorLabel4.setStyle("-fx-text-fill: red;");
                    isValid = false;
                }
            } catch (NumberFormatException e) {
                errorLabel4.setText("GPA must be a valid number.\n");
                errorLabel4.setStyle("-fx-text-fill: red;");
                isValid = false;
            }

            if (!isValidPhoneNumber(phoneField.getText())) {
                errorLabel5.setText("Invalid phone number");
                errorLabel5.setStyle("-fx-text-fill: red;");
                isValid = false;
            }

            if (!isValidEmail(emailField.getText())) {
                errorLabel6.setText("Invalid email");
                errorLabel6.setStyle("-fx-text-fill: red;");
                isValid = false;
            }

            // Generating report based on user input and displaying it
            if (isValid) {
                reportArea.setText("Application submitted successfully!");
                String userInformation = "Name: " + nameField.getText() + "\n" +
                        "Surname: " + surnameField.getText() + "\n" +
                        "Age: " + ageField.getText() + "\n" +
                        "Phone number: " + phoneField.getText() + "\n" +
                        "Email: " + emailField.getText() + "\n" +
                        "GPA: " + gpaField.getText() + "\n" +
                        "Program: " + majorChoice.getValue() + "\n" +
                        "Motivational letter: " + letterArea.getText() + "\n";
                reportArea.setText(userInformation);

                // Save the information to files
                saveToFile(nameField.getText() + "_" + surnameField.getText() + "_form.txt", userInformation);
                saveToFile(nameField.getText() + "_" + surnameField.getText() + "_motivation.txt", letterArea.getText());
            } else {
                reportArea.setText("Please correct the errors and resubmit.");
            }
        });

        // Creating VBox for labels
        VBox vBox1 = new VBox(30);
        vBox1.getChildren().addAll(label1, label2, label3, label4, label5, label6, label7, label8);

        // Creating VBox for fields
        VBox vBox2 = new VBox(20);
        vBox2.getChildren().addAll(nameField, surnameField, ageField, phoneField, emailField, gpaField, majorChoice);

        // Creating HBox for VBoxes
        HBox hBox = new HBox(20);
        hBox.getChildren().addAll(vBox1, vBox2);

        // Creating main VBox for HBox, submit button, errors and TextAreas
        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(hBox, letterArea, button1, saveButton, errorLabel1, errorLabel2, errorLabel3, errorLabel4, errorLabel5, errorLabel6, reportArea);

        // Setting up the scene
        Scene scene = new Scene(root, 500, 800);
        root.getChildren().addAll(vBox);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Application for AUK Bachelor's Program");
        primaryStage.show();
    }

    // Method to validate phone number format
    public boolean isValidPhoneNumber(String phoneField) {
        return phoneField.matches("\\d{10}");
    }

    // Method to validate email format
    public boolean isValidEmail(String email) {
        return email.contains("@");

    }
    // Part 4 File I/O App
    public void saveToFile(String fileName, String content) {
        File file = new File(fileName);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(content);
        } catch (IOException e) {
            System.err.println("Error occurred while saving to file: " + e.getMessage());
        }
    }
    // Method to save user information to a file manually
    public void saveUserInformation(String name, String surname, String letter) {
        String fileName = name + "_" + surname + "_manual_save.txt";
        saveToFile(fileName, "Name: " + name + "\nSurname: " + surname + "\nMotivational letter: " + letter);
    }
}
