package com.example.project1_gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;
import javafx.geometry.Pos;



public class Part2JavaFXLayouts extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Creating shapes and set their parameters (Circle, Rectangle, Ellipse, Line and PolyLine
        Circle circle = new Circle(40, Color.RED);
        Rectangle rectangle = new Rectangle(80, 60, Color.YELLOW);
        Ellipse ellipse = new Ellipse(50, 40);
        ellipse.setFill(Color.NAVY);
        Line line = new Line(0, 0, 60, 40);

        Polyline polyline = new Polyline(0, 0, 30, 30, 50, 0, 70, 30, 90, 0);
        polyline.setStroke(Color.GREEN);
        polyline.setStrokeWidth(3);
        polyline.setStrokeLineCap(StrokeLineCap.BUTT);
        polyline.setStrokeLineJoin(StrokeLineJoin.MITER);
        polyline.setStrokeDashOffset(10);
        Text text = new Text("Text Example"); // Creating Text
        text.setFont(Font.font(20)); // Setting the font

        // Creating root Pane with preferred size and light lightgray background
        Pane root = new Pane();
        root.setPrefSize(500, 300);
        root.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));

        // Creating HBox with circle and rectangle
        HBox hBox = new HBox(40);
        hBox.setAlignment(Pos.CENTER); // Aligning all children to the center horizontally
        hBox.setPadding(new Insets(20)); // Adding padding around the HBox
        hBox.setLayoutX(50); // Setting the layout X coordinate
        hBox.setLayoutY(50); // Setting the layout Y coordinate
        hBox.getChildren().addAll(circle, rectangle);

        // Creating VBox with ellipse and line
        VBox vBox = new VBox(40);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(20));
        vBox.setLayoutX(300);
        vBox.setLayoutY(50);
        vBox.getChildren().addAll(ellipse, line);

        // Creating GridPane with polyline and text
        GridPane gridPane = new GridPane();
        gridPane.setHgap(40); // Setting horizontal gap between cells
        gridPane.setVgap(20); // Setting vertical gap between cells
        gridPane.setLayoutX(50); // Setting the layout X coordinate
        gridPane.setLayoutY(200); // Setting the layout Y coordinate
        gridPane.add(polyline, 0, 0); // Adding polyline to the first row and first column
        gridPane.add(text, 1, 0); // Adding text to the first row and second column

        // Adding all layouts to root Pane
        root.getChildren().addAll(vBox, hBox, gridPane);

        // Creating and setting the scene
        Scene scene = new Scene(root, 500, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Layouts Demo");
        primaryStage.show();
    }
}
