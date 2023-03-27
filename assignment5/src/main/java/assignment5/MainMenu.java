package assignment5;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;
import java.util.Arrays;
import java.util.List;
public class MainMenu extends Application {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;

    private static List<Pair<String, String>> menuData1 = Arrays.asList(
            new Pair<String, String>("Start Game", "game"),
            new Pair<String, String>("Game Options", "options"),
            new Pair<String, String>("Exit to Desktop", "quit")
    );
    
    private static Stage stage;
    private static Scene main;
    private static Pane root = new Pane();
    private static VBox menuBox = new VBox(-5);
    private  static Line line;

   


    private static Parent createContent() {
        addBackground();
        addTitle();

        double lineX = WIDTH / 2 - 100;
        double lineY = HEIGHT / 3 + 50;

        addLine(lineX, lineY);
        addMenu(lineX + 5, lineY + 5);

        startAnimation();

        return root;
    }

    private static void addBackground() {
        ImageView imageView = new ImageView(new Image(MainMenu.class.getResource("res/background.jpg").toExternalForm()));
        imageView.setFitWidth(WIDTH);
        imageView.setFitHeight(HEIGHT);

        root.getChildren().add(imageView);
    }

    private static void addTitle() {
        MainTitle title = new MainTitle("CRITTERVERSE");
        title.setTranslateX(WIDTH / 2 - title.getTitleWidth() / 2);
        title.setTranslateY(HEIGHT / 3);

        root.getChildren().add(title);
    }

    private static void addLine(double x, double y) {
        line = new Line(x, y, x, y + 113);
        line.setStrokeWidth(3);
        line.setStroke(Color.BLACK);
        line.setEffect(new DropShadow(5, Color.WHITE));
        line.setScaleY(0);

        root.getChildren().add(line);
    }

    private static void startAnimation() {
        ScaleTransition st = new ScaleTransition(Duration.seconds(1), line);
        st.setToY(1);
        st.setOnFinished(e -> {

            for (int i = 0; i < menuBox.getChildren().size(); i++) {
                Node n = menuBox.getChildren().get(i);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(1 + i * 0.15), n);
                tt.setToX(0);
                tt.setOnFinished(e2 -> n.setClip(null));
                tt.play();
            }
        });
        st.play();
    }

    private static void addMenu(double x, double y) {
        menuBox.setTranslateX(x);
        menuBox.setTranslateY(y);
        menuData1.forEach(data -> {
            MenuItem item = new MenuItem(data.getKey());
            item.setOnAction(data.getValue());
            item.setTranslateX(-300);

            Rectangle clip = new Rectangle(300, 30);
            clip.translateXProperty().bind(item.translateXProperty().negate());

            item.setClip(clip);

            menuBox.getChildren().addAll(item);
        });

        root.getChildren().add(menuBox);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        main = new Scene(createContent());
        primaryStage.setTitle("Critter Game");
        primaryStage.setScene(main);
        primaryStage.show();
    }

    // Switch Scenes in single Stage
	public static void switchScenes(Scene scene) {
		stage.setScene(scene);
	}

    public static  Scene getMainScene(){
        return main;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
