package assignment5;

import java.net.URISyntaxException;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class MenuItem extends Pane {
    private Text text;

    private Effect shadow = new DropShadow(5, Color.BLACK);
    private Effect blur = new BoxBlur(1, 1, 3);

    static Scene gameScene;
    static Scene optionsScene;

    public MenuItem(String name) {
        Polygon bg = new Polygon(
                0, 0,
                200, 0,
                215, 15,
                200, 30,
                0, 30
        );
        bg.setStroke(Color.color(1, 1, 1, 0.75));
        bg.setEffect(new GaussianBlur());

        bg.fillProperty().bind(
                Bindings.when(pressedProperty())
                        .then(Color.color(0, 0, 0, 0.75))
                        .otherwise(Color.color(0, 0, 0, 0.25))
        );

        text = new Text(name);
        text.setTranslateX(5);
        text.setTranslateY(20);
        text.setFont(Font.loadFont(MainMenu.class.getResource("Penumbra-HalfSerif-Std_35114.ttf").toExternalForm(), 14));
        text.setFill(Color.WHITE);

        text.effectProperty().bind(
                Bindings.when(hoverProperty())
                        .then(shadow)
                        .otherwise(blur)
        );

        getChildren().addAll(bg, text);
    }


    public void setOnAction(String string) {
        setOnMouseClicked(e -> action(string));

    }

    public static void action(String cmd){
        // TODO MOD TO FIT A SCENE SWITH FOR GAME AND  PARAM
        switch (cmd) {
            case "game":
              Main game = new Main();
              try {
				game.start(MainMenu.stage);
			} catch (ClassNotFoundException | URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        //    MainMenu.switchScenes(gameScene);
                break;

            case "options":
            optionsScene = OptionScene.CreateOptionsScene();
            MainMenu.switchScenes(optionsScene);
                break;
        
            default:
                Platform.exit();
                break;
        }
    }


}