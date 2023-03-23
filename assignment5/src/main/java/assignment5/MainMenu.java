package assignment5;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainMenu extends Application {

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Image icon = new Image("Grow_Icon.png");
        // primaryStage.getIcons().add(icon);

        primaryStage.setTitle("Title Screen");
        Button btn1 = new Button();
        btn1.setText("Game Params");
        // btn.setOnAction(new EventHandler<ActionEvent>() {
 
        //     @Override
        //     public void handle(ActionEvent event) {
        //         System.out.println("Hello World!");
        //     }
        // });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn1);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }

    
}
