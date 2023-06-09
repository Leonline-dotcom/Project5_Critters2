package assignment5;

import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class MainTitle extends Pane {
    private Text text;

    public MainTitle(String name) {
        String spread = "";
        for (char c : name.toCharArray()) {
            spread += c + " ";
        }

        text = new Text(spread);
        text.setFont(Font.loadFont(MainMenu.class.getResource("Penumbra-HalfSerif-Std_35114.ttf").toExternalForm(), 48));
        text.setFill(Color.BLACK);
        text.setEffect(new DropShadow(30, Color.WHITE));

        getChildren().addAll(text);
    }

    public double getTitleWidth() {
        return text.getLayoutBounds().getWidth();
    }

    public double getTitleHeight() {
        return text.getLayoutBounds().getHeight();
    }
}