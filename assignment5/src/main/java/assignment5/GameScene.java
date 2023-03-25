package assignment5;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class GameScene {
    private static BorderPane root = new BorderPane();


    public static Scene CreateGameScene(){
        root.setLeft(addWorld());
        root.setRight(addStatusWindow());
        root.setBottom(addButtons());

        return new Scene(root);
    }

    

    private static GridPane addWorld(){
        //TODO: Add the world
        return null;
    }

    private static VBox addStatusWindow(){
        //TODO: Add the StatusWindow
        return null;
    }

    private static GridPane addButtons() {
        //TODO: Add Create(Critter)(Number), Start (Frame Rate), Stop, Step(Number) )
        return null;
    }
}
