package assignment5;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage statsStage;

    @Override
    public void start(Stage primaryStage) {

        // Create the game window
        StackPane gameWindow = new StackPane();
        gameWindow.setStyle("-fx-background-color: black;");
        gameWindow.setPrefSize(600, 400);

        // Create the stats window
        statsStage = new Stage();
        statsStage.setTitle("Game Stats");
        VBox statsBox = new VBox();
        statsBox.setSpacing(0);
        statsBox.setPadding(new Insets(0));
        Scene statsScene = new Scene(statsBox, 300, 200);
        statsStage.setScene(statsScene);
        statsStage.setResizable(true);
        statsStage.initOwner(primaryStage);
        statsStage.setX(primaryStage.getX() + primaryStage.getWidth() / 2 - statsStage.getWidth() / 2);
        statsStage.setY(primaryStage.getY() + 50);

        // Create the stats button
        Button statsButton = new Button("Stats");
        statsButton.setPrefWidth(80);
        statsButton.setOnAction(e -> toggleStatsWindow());

        // Create the start button with a drop-down menu
        ChoiceBox<Integer> startChoiceBox = new ChoiceBox<>();
        startChoiceBox.getItems().addAll(1, 5, 10, 50, 100);
        startChoiceBox.setValue(1);
        Button startButton = new Button("Start");
        startButton.setPrefWidth(80);
        HBox startBox = new HBox();
        startBox.getChildren().addAll(startButton, startChoiceBox);
        startBox.setSpacing(10);

        // Create the stop button
        Button stopButton = new Button("Stop");
        stopButton.setPrefWidth(80);

        // Create the step button with a drop-down menu
        ChoiceBox<Integer> stepChoiceBox = new ChoiceBox<>();
        stepChoiceBox.getItems().addAll(1, 5, 10, 50, 100);
        stepChoiceBox.setValue(1);
        Button stepButton = new Button("Step");
        stepButton.setPrefWidth(80);
        HBox stepBox = new HBox(stepButton, stepChoiceBox);
        stepBox.setSpacing(10);

        // Create the create button with two drop-down menus
        ChoiceBox<Integer> createChoiceBox1 = new ChoiceBox<>();
        createChoiceBox1.getItems().addAll(1, 2, 3, 4, 5);
        createChoiceBox1.setValue(1);
        ChoiceBox<Integer> createChoiceBox2 = new ChoiceBox<>();
        createChoiceBox2.getItems().addAll(1, 5, 10, 50, 100);
        createChoiceBox2.setValue(1);
        Button createButton = new Button("Create");
        createButton.setPrefWidth(80);
        HBox createBox = new HBox(createButton, createChoiceBox1, createChoiceBox2);
        createBox.setSpacing(10);

        // Create the button panel
        HBox buttonPanel = new HBox(statsButton, startBox, stopButton, stepBox, createBox);
        buttonPanel.setSpacing(10);
        buttonPanel.setAlignment(Pos.CENTER);
        buttonPanel.setPadding(new Insets(10));

        // Create the main layout
        BorderPane root = new BorderPane();
        root.setCenter(gameWindow);
        root.setBottom(buttonPanel);

        // Create the scene and show the window
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("CritterScape");
        primaryStage.show();
    }

    private void toggleStatsWindow() {
        if (statsStage.isShowing()) {
            statsStage.hide();
        } else {
            statsStage.show();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}