package assignment5;

import java.util.ArrayList;
import java.util.List;

import assignment5.Critter.CritterShape;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.*;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application {

    private Stage statsStage;
    
    private static GraphicsContext gc;
    private static Pane gameWindow;
    
    private static String critType;
    private static int critAmount;
    private static int runCount;
    private static int StartStepAmount;
    private static int StepStepAmount;
    private static boolean running;
    
    
    
    @Override
    public void start(Stage primaryStage) {

        // Create the game window
        gameWindow = new Pane();

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
        gc.strokeRect(5, 5, Params.WORLD_WIDTH*8 + 25, Params.WORLD_HEIGHT*8 + 25);
        for (int i = 15; i < Params.WORLD_WIDTH*8 + 10; i += 20) {
            gc.setFill(Color.BURLYWOOD);
            gc.fillRect(i, 5, 10, 10);
            gc.fillRect(i, Params.WORLD_WIDTH*8 + 20, 10, 10);
        }
        for (int j = 15; j < Params.WORLD_HEIGHT*8 + 10; j += 20) {
            gc.setFill(Color.BURLYWOOD);
            gc.fillRect(5, j, 10, 10);
            gc.fillRect(Params.WORLD_HEIGHT*8 + 20, j, 10, 10);
        }

        // Add the canvas to the game window
        gameWindow.getChildren().add(canvas);

        // Create the stats window
        statsStage = new Stage();
        statsStage.setTitle("Game Stats");
        VBox statsBox = new VBox();
        
        TextArea textbox = new TextArea();
        textbox.setEditable(false);
        textbox.setText("");
        
        ChoiceBox<String> statsChoice = new ChoiceBox();
        statsChoice.setPrefWidth(300);
        statsChoice.getItems().addAll("Critter1", "Critter2");
        statsChoice.setValue("Choose type:");
        statsChoice.setOnAction(e -> {
			try {
				
				textbox.setText("");
				textbox.setText(textbox.getText() + Critter.runStats(Critter.getInstances(statsChoice.getValue())));
			} catch (InvalidCritterException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		});
        
       
        statsBox.setSpacing(0);
        statsBox.setPadding(new Insets(0));
        statsBox.getChildren().addAll(statsChoice, textbox);
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
        
//        Shape star = new Circle(200,200,10);
//        gameWindow.getChildren().add(star);

        // Create the start button with a drop-down menu
        ChoiceBox<Integer> startChoiceBox = new ChoiceBox<>();
        startChoiceBox.getItems().addAll(1, 5, 10, 50, 100);
        //startChoiceBox.setValue(1);
        startChoiceBox.setOnAction(e -> StartStepAmount = startChoiceBox.getValue());
        Button startButton = new Button("Start");
        startButton.setPrefWidth(80);
        startButton.setOnAction(e->{
        	startChoiceBox.setDisable(true);
        	handleStartButton(StartStepAmount);
        	}
        );
        
        
        
        HBox startBox = new HBox();
        startBox.getChildren().addAll(startButton, startChoiceBox);
        startBox.setSpacing(10);

        // Create the stop button
        Button stopButton = new Button("Stop");
        stopButton.setPrefWidth(80);
        stopButton.setOnAction(e -> {
        	startChoiceBox.setDisable(false);
        	handleStopButton();
        });

        // Create the step button with a drop-down menu
        ChoiceBox<Integer> stepChoiceBox = new ChoiceBox<>();
        stepChoiceBox.getItems().addAll(1, 5, 10, 50, 100);
       // stepChoiceBox.setValue(1);
        stepChoiceBox.setOnAction(e -> StepStepAmount = stepChoiceBox.getValue());
        Button stepButton = new Button("Step");
        stepButton.setPrefWidth(80);
        stepButton.setOnAction(e-> step(StepStepAmount));
        
        
        HBox stepBox = new HBox(stepButton, stepChoiceBox);
        stepBox.setSpacing(10);

        // Create the create button with two drop-down menus
        ChoiceBox<String> createChoiceBox1 = new ChoiceBox<>();
        //TODO
        //make it choose from array of available critter classes
        createChoiceBox1.getItems().addAll("Critter1", "Critter2");
        createChoiceBox1.setValue("Type");
        //sets the type of critter variable critType
        createChoiceBox1.setOnAction(e-> critType = createChoiceBox1.getValue().toString());
        
        ChoiceBox<Integer> createChoiceBox2 = new ChoiceBox<>();
        createChoiceBox2.getItems().addAll(1, 5, 10, 100);
       // createChoiceBox2.setValue(1);
        
        createChoiceBox2.setOnAction(e -> critAmount = createChoiceBox2.getValue());
        Button createButton = new Button("Create");
        createButton.setPrefWidth(80);
        
        //calls createCritter a certain number of times and of a certain type
        createButton.setOnAction(e -> {
        	if(createChoiceBox1.getValue() != "Type") {
        		try {
        			for(int i = 1;i<= critAmount;i++) {
        				Critter.createCritter(critType);
        			}
        		} catch (InvalidCritterException e1) {
				// TODO Auto-generated catch block
        			e1.printStackTrace();
        			}
        	}
			
		});
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
        Scene scene = new Scene(root, 820,950);
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

    public static void removeCritters() {
    	while(!CritterShapes.isEmpty()) {
    		Shape crit = CritterShapes.get(CritterShapes.size()-1);
    		gameWindow.getChildren().remove(crit);
    		CritterShapes.remove(CritterShapes.size()-1);
     
    	}
    }
    
    private static ArrayList<Shape> CritterShapes = new ArrayList<>(); //list of critters to be drawn and to remove
    
    public static void drawCritter(Critter critter, int x , int y) {
    	x+=2;
    	y+=2;
    	CritterShape shape = critter.viewShape();
    	
    	if(shape == CritterShape.CIRCLE) {
    		Shape circle = new Circle(x*8, y*8, 4);
    		circle.setFill(critter.viewFillColor());
    		circle.setStroke(critter.viewOutlineColor());
    		gameWindow.getChildren().add(circle);
    		CritterShapes.add(circle);
    	}
    	if(shape == CritterShape.SQUARE) {
    		Shape square = new Rectangle(x*8, y*8, 10, 10);
    		square.setFill(critter.viewFillColor());
    		square.setStroke(critter.viewOutlineColor());
    		gameWindow.getChildren().add(square);
    		CritterShapes.add(square);
    	}
    	
    	if(shape == CritterShape.TRIANGLE) {
    		Polygon triangle = new Polygon((x*8),(y*8 + 5), (x*8 - 5),(y*8 - 5),  (x*8 +5),(y*8 - 5));
    		triangle.setFill(critter.viewFillColor());
    		triangle.setStroke(critter.viewOutlineColor());
    		gameWindow.getChildren().add(triangle);
    		CritterShapes.add(triangle);
    	}
    	
    	if(shape == CritterShape.STAR) {
    		Polygon star1 = new Polygon((x*8),(y*8 + 6), (x*8 - 5),(y*8 - 4),  (x*8 +5),(y*8 - 4));
    		Polygon star2 = new Polygon((x*8),(y*8 - 6), (x*8 - 5),(y*8 + 4),  (x*8 +5),(y*8 + 4));
    		Shape star = Polygon.union(star1,  star2);
    		star.setFill(critter.viewFillColor());
    		star.setStroke(critter.viewOutlineColor());
    		gameWindow.getChildren().add(star);
    		CritterShapes.add(star);
    	}
    	
    	if(shape == CritterShape.DIAMOND) {
    		Shape diamond =  new Polygon((x*8),(y*8 + 5), (x*8 - 5),(y*8 - 5), (x*8 - 3.5),(y*8 - 7.5), (x*8 +3.5),(y*8 - 7.5), (x*8 +5),(y*8 - 5));
    		//Shape diamond2 = new Polygon((x*10 - 5),(y*10 - 5),  (x*10 +5),(y*10 - 5),   (x*10 - 3.5),(y*10 - 7.5),  (x*10 +3.5),(y*10 - 7.5));
    		//Shape diamond = Polygon.union(diamond1, diamond2);
    		diamond.setFill(critter.viewFillColor());
    		diamond.setStrokeWidth(2);
    		diamond.setStroke(critter.viewOutlineColor());
    		gameWindow.getChildren().add(diamond);
    		CritterShapes.add(diamond);
    	}
        
    }
    
   
    
   
    
    
    
    
    
    private Timeline animation;

    private void handleStartButton(int n) {
    	if(!running) {
    		running = true;
        // Create a KeyFrame that represents one frame of animation
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(.5), event -> {
            // Call the step() method 
            step(n);
        });

        // Create a new Timeline object and add the KeyFrame to it
        animation = new Timeline(keyFrame);

        // Set the cycle count to INDEFINITE to make the animation run forever
        animation.setCycleCount(Timeline.INDEFINITE);

        // Start the animation
        animation.play();
    	}
    }

    private void handleStopButton() {
    	running=false;
        // Stop the animation
        animation.stop();
    }
    
    
    private void step(int n) {
    	
    	while(n>0) {
    	Critter.worldTimeStep();
    	n--;
    	}
    	Critter.displayWorld(gameWindow);
		
    }
    
    
    
    public static void main(String[] args) {
        launch(args);
//       
        	
        }
    }

        		