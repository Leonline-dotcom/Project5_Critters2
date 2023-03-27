package assignment5;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType; 
import javafx.scene.effect.DropShadow; 
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class OptionScene {

    private static StackPane fullScene;
    private static GridPane OpScene;
    private static int[] worldOptions = {10, 20, 30, 40, 50,
                                        60, 70, 80, 90, 100, 
                                        120                 };

    private static int[] moveOptions = {1,2,4,5,8,10};

    private static int[] energyOptions = {50,100,200,300,400};

   private static ComboBox <Integer> width, height, sEnergy, lookVal, sitVal, walkVal, runVal, reprodVal, photoVal;
    
    


    public static Scene CreateOptionsScene() {
        //TODO: Fill out CreateOptionsScene()
        fullScene = new StackPane();
        addBackground();
        OpScene = gridSpecs();
        addLabels();
        addButtons();
        addComboBoxes();
        fullScene.getChildren().add(OpScene);
        //OpScene.setGridLinesVisible(true);
        return new Scene(fullScene);
    }



  


    private static void addBackground() {
        ImageView imageView = new ImageView(new Image(MainMenu.class.getResource("res/background.jpg").toExternalForm()));
        imageView.setFitWidth(600);
        imageView.setFitHeight(400);

        fullScene.getChildren().add(imageView);
    }






    private static GridPane gridSpecs(){
         // Instantiate a new Grid Pane
         GridPane gridPane = new GridPane();

        
         // Position the pane at the center of the screen, both vertically and horizontally
         gridPane.setAlignment(Pos.CENTER);
 
         // Set a padding of 40px on each side
         gridPane.setPadding(new Insets(40, 40, 40, 40));
 
         // Set the horizontal gap between columns
         gridPane.setHgap(10);
 
         // Set the vertical gap between rows
         gridPane.setVgap(10);
 
         // Add Column Constraints
 
        //  // columnOneConstraints will be applied to all the nodes placed in column one.
          ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
          columnOneConstraints.setHgrow(Priority.ALWAYS);
          //  columnOneConstraints.setHalignment(HPos.CENTER);
 
        //  // columnTwoConstraints will be applied to all the nodes placed in column two.
          ColumnConstraints columnTwoConstrains = new ColumnConstraints(200,200, Double.MAX_VALUE);
          columnTwoConstrains.setHgrow(Priority.ALWAYS);
 
          gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);
 
         return gridPane;
    }

  private static void addLabels(){

    //Instantiating the Shadow class 
    DropShadow dropShadow = new DropShadow(); 
      
    //setting the type of blur for the shadow 
    dropShadow.setBlurType(BlurType.GAUSSIAN); 

    //Setting color for the shadow 
    dropShadow.setColor(Color.BLACK); 
      
    //Setting the height of the shadow
    dropShadow.setHeight(5); 
    
    //Setting the width of the shadow 
    dropShadow.setWidth(5); 
    
    //Setting the radius of the shadow 
    dropShadow.setRadius(5); 
    
    //setting the offset of the shadow 
    dropShadow.setOffsetX(3); 
    dropShadow.setOffsetY(2); 
    
    //Setting the spread of the shadow 
    dropShadow.setSpread(12);  

    Label worldW, worldH, startEnergy, look, sit, walk, run, minReprodEnergy, photosynthesis ;

    worldW = new Label("World Width");
    worldH = new Label("World Height");
    startEnergy = new Label("Creature Starting Energy");
    look = new Label("Energy to Look");
    sit = new Label("Energy to Sit");
    walk= new Label("Energy to Walk");
    run = new Label("Energy to Run");
    minReprodEnergy = new Label("Energy required to reproduce");
    photosynthesis = new Label("Energy gained from Sun" );

    worldW.setFont(Font.loadFont(MainMenu.class.getResource("res/Penumbra-HalfSerif-Std_35114.ttf").toExternalForm(), 10));
    worldH.setFont(Font.loadFont(MainMenu.class.getResource("res/Penumbra-HalfSerif-Std_35114.ttf").toExternalForm(), 10));
    startEnergy.setFont(Font.loadFont(MainMenu.class.getResource("res/Penumbra-HalfSerif-Std_35114.ttf").toExternalForm(), 10));
    look.setFont(Font.loadFont(MainMenu.class.getResource("res/Penumbra-HalfSerif-Std_35114.ttf").toExternalForm(), 10));
    sit.setFont(Font.loadFont(MainMenu.class.getResource("res/Penumbra-HalfSerif-Std_35114.ttf").toExternalForm(), 10));
    walk.setFont(Font.loadFont(MainMenu.class.getResource("res/Penumbra-HalfSerif-Std_35114.ttf").toExternalForm(), 10));
    run.setFont(Font.loadFont(MainMenu.class.getResource("res/Penumbra-HalfSerif-Std_35114.ttf").toExternalForm(), 10));
    minReprodEnergy.setFont(Font.loadFont(MainMenu.class.getResource("res/Penumbra-HalfSerif-Std_35114.ttf").toExternalForm(), 10));
    photosynthesis.setFont(Font.loadFont(MainMenu.class.getResource("res/Penumbra-HalfSerif-Std_35114.ttf").toExternalForm(), 10));


    worldW.setTextFill(Color.WHITE);
    worldH.setTextFill(Color.WHITE);
    startEnergy.setTextFill(Color.WHITE);
    look.setTextFill(Color.WHITE);
    sit.setTextFill(Color.WHITE);
    walk.setTextFill(Color.WHITE);
    run.setTextFill(Color.WHITE);
    minReprodEnergy.setTextFill(Color.WHITE);
    photosynthesis.setTextFill(Color.WHITE);

    worldW.setEffect(dropShadow);
    worldH.setEffect(dropShadow);
    startEnergy.setEffect(dropShadow);
    look.setEffect(dropShadow);
    sit.setEffect(dropShadow);
    walk.setEffect(dropShadow);
    run.setEffect(dropShadow);
    minReprodEnergy.setEffect(dropShadow);
    photosynthesis.setEffect(dropShadow);


    OpScene.add(worldW, 0, 0);
    OpScene.add(worldH, 0, 1);
    OpScene.add(startEnergy, 0, 2);
    OpScene.add(look, 0, 3);
    OpScene.add(sit, 0, 4);
    OpScene.add(walk, 0, 5);
    OpScene.add(run, 0, 6);
    OpScene.add(minReprodEnergy, 0, 7);
    OpScene.add(photosynthesis, 0, 8);
    


    GridPane.setHalignment(worldW, HPos.RIGHT);
    GridPane.setHalignment(worldH, HPos.RIGHT);
    GridPane.setHalignment(startEnergy, HPos.RIGHT);
    GridPane.setHalignment(look, HPos.RIGHT);
    GridPane.setHalignment(sit, HPos.RIGHT);
    GridPane.setHalignment(walk, HPos.RIGHT);
    GridPane.setHalignment(run, HPos.RIGHT);
    GridPane.setHalignment(minReprodEnergy, HPos.RIGHT);
    GridPane.setHalignment(photosynthesis, HPos.RIGHT);
    
    

  }

  private static void addComboBoxes() {
    width = new ComboBox<>();
    height = new ComboBox<>();
    sEnergy = new ComboBox<>();
    lookVal = new ComboBox<>();
    sitVal = new ComboBox<>();
    walkVal = new ComboBox<>();
    runVal = new ComboBox<>();
    reprodVal = new ComboBox<>();
    photoVal = new ComboBox<>();

    width.setPromptText(Integer.toString(Params.WORLD_WIDTH));
    height.setPromptText(Integer.toString(Params.WORLD_HEIGHT));
    sEnergy.setPromptText(Integer.toString(Params.START_ENERGY));
    lookVal.setPromptText(Integer.toString(Params.LOOK_ENERGY_COST));
    sitVal.setPromptText(Integer.toString(Params.REST_ENERGY_COST));
    walkVal.setPromptText(Integer.toString(Params.WALK_ENERGY_COST));
    runVal.setPromptText(Integer.toString(Params.RUN_ENERGY_COST));
    reprodVal.setPromptText(Integer.toString(Params.MIN_REPRODUCE_ENERGY));
    photoVal.setPromptText(Integer.toString(Params.PHOTOSYNTHESIS_ENERGY_AMOUNT));
    
    width.getEditor().setFont(Font.loadFont(MainMenu.class.getResource("res/Penumbra-HalfSerif-Std_35114.ttf").toExternalForm(), 14));
    
   
    for(int option : worldOptions){
        width.getItems().add(option);
        height.getItems().add(option);
    }

    for(int option : moveOptions){
        lookVal.getItems().add(option);
        sitVal.getItems().add(option);
        walkVal.getItems().add(option);
        runVal.getItems().add(option);
        photoVal.getItems().add(option);
    }

    for(int option : energyOptions){
        reprodVal.getItems().add(option);
        sEnergy.getItems().add(option);
    }


    OpScene.add(width, 1, 0);
    OpScene.add(height, 1, 1);
    OpScene.add(sEnergy, 1, 2);
    OpScene.add(lookVal, 1, 3);
    OpScene.add(sitVal, 1, 4);
    OpScene.add(walkVal, 1, 5);
    OpScene.add(runVal, 1, 6);
    OpScene.add(reprodVal, 1, 7);
    OpScene.add(photoVal, 1, 8);

}


private static void addButtons() {
    HBox navBox = new HBox();
    navBox.getChildren().add(createSaveButton());
    navBox.getChildren().add(createMenuButton());

    navBox.setSpacing(10);
    navBox.setAlignment(Pos.BOTTOM_RIGHT);
    OpScene.add(navBox, 1, 9);
}

private static Button createMenuButton() {
    Button backButton = new Button("Back to Main Menu");
     backButton.setOnAction(e -> {
         MainMenu.switchScenes(MainMenu.getMainScene());});
    return backButton;
}


private static Button createSaveButton() {
Button saveButton = new Button("Save configuration");
saveButton.setOnAction(e -> saveConfiguration());

return saveButton;
}


private static void saveConfiguration() {
    if(!(width.getValue() == null)){Params.WORLD_WIDTH = width.getValue();}
    if(!(height.getValue() == null)){Params.WORLD_HEIGHT = height.getValue();}
    if(!(sEnergy.getValue() == null)){Params.START_ENERGY = sEnergy.getValue();}
    if(!(lookVal.getValue() == null)){Params.LOOK_ENERGY_COST = lookVal.getValue();}
    if(!(sitVal.getValue() == null)){Params.REST_ENERGY_COST = sitVal.getValue();}
    if(!(walkVal.getValue() == null)){Params.WALK_ENERGY_COST = walkVal.getValue();}
    if(!(runVal.getValue() == null)){Params.RUN_ENERGY_COST= runVal.getValue();}
    if(!(reprodVal.getValue() == null)){Params.MIN_REPRODUCE_ENERGY= reprodVal.getValue();}
    if(!(photoVal.getValue() == null)){Params.PHOTOSYNTHESIS_ENERGY_AMOUNT = photoVal.getValue();}
    
}


}
