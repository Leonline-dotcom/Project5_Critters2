package assignment5;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.*;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage statsStage;
    
    private static GraphicsContext gc;

    @Override
    public void start(Stage primaryStage) {

        // Create the game window
        Pane gameWindow = new Pane();

        // Create a canvas for the game view
        Canvas canvas = new Canvas(820, 820);

        // Get the graphics context for the canvas
        gc = canvas.getGraphicsContext2D();

        // Create a noise texture for the grass
        double[][] noise = new double[(int) canvas.getWidth()][(int) canvas.getHeight()];
        for (int x = 0; x < canvas.getWidth(); x++) {
            for (int y = 0; y < canvas.getHeight(); y++) {
                noise[x][y] = Math.random();
            }
        }

        // Loop through the noise texture and draw pixels on the canvas
        for (int x = 0; x < canvas.getWidth(); x += 5) {
            for (int y = 0; y < canvas.getHeight(); y += 5) {
                double value = noise[x][y] * 100;
                gc.setFill(Color.rgb(0, (int) (100 + value), 0));
                gc.fillRect(x, y, 5, 5);
            }
        }

        // Draw the fence around the edge of the game window
        gc.setStroke(Color.PERU);
        gc.setLineWidth(10);
        gc.strokeRect(0, 0, 820, 820);
        for (int i = 10; i < 810; i += 20) {
            gc.setFill(Color.BURLYWOOD);
            gc.fillRect(i, 0, 10, 10);
            gc.fillRect(i, 810, 10, 10);
        }
        for (int j = 10; j < 810; j += 20) {
            gc.setFill(Color.BURLYWOOD);
            gc.fillRect(0, j, 10, 10);
            gc.fillRect(810, j, 10, 10);
        }

        // Add the canvas to the game window
        gameWindow.getChildren().add(canvas);

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
        Scene scene = new Scene(root, 1020, 1200);
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

    // private void removeCritters(Critter[] critRemove, GraphicsContext gc) {
    // 	for(Critter critter : critRemove) {
    //     // Get the x and y coordinates of the critter
    //     int x = critter.getX() * 10;
    //     int y = critter. getY() * 10;

    //     // Fill a rectangle with the background color at the position of the critter
    //     gc.setFill(Color.rgb(0, (int) (100 + noise[x][y] * 100), 0));
    //     gc.fillRect(x, y, 10, 10);
    // 	}
    // }
    // private void drawCritters(Critter[] critters, GraphicsContext gc) {
    //     for (Critter critter : critters) {
    //         // Get the x and y coordinates of the critter
    //         int x = critter.getX();
    //         int y = critter.getY();

    //         // Draw the critter at its respective position on the canvas
    //         gc.setFill(critter.getColor());
    //         gc.fillRect(x * 10, y * 10, 10, 10);
    //     }
    // }
    
    public static void main(String[] args) {
        launch(args);
    }
}
        		