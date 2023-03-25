package assignment5;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;

    // private List<Pair<String, Runnable>> menuData = Arrays.asList(
    //         new Pair<String, Runnable>("Start Game", () -> {}),
    //         new Pair<String, Runnable>("Game Options", () -> {}),
    //         new Pair<String, Runnable>("Credits", () -> {}),
    //         new Pair<String, Runnable>("Exit to Desktop", Platform::exit)
    // );

    private List<Pair<String, String>> menuData1 = Arrays.asList(
            new Pair<String, String>("Start Game", "() -> {}"),
            new Pair<String, String>("Game Options", "() -> {}"),
            new Pair<String, String>("Exit to Desktop", "quit")
    );
    private Stage stage;
    private Pane root = new Pane();
    private VBox menuBox = new VBox(-5);
    private Line line;

    private Parent createContent() {
        addBackground();
        addTitle();

        double lineX = WIDTH / 2 - 100;
        double lineY = HEIGHT / 3 + 50;

        addLine(lineX, lineY);
        addMenu(lineX + 5, lineY + 5);

        startAnimation();

        return root;
    }

    private void addBackground() {
        ImageView imageView = new ImageView(new Image(getClass().getResource("res/background.jpg").toExternalForm()));
        imageView.setFitWidth(WIDTH);
        imageView.setFitHeight(HEIGHT);

        root.getChildren().add(imageView);
    }

    private void addTitle() {
        MainTitle title = new MainTitle("CRITTERVERSE");
        title.setTranslateX(WIDTH / 2 - title.getTitleWidth() / 2);
        title.setTranslateY(HEIGHT / 3);

        root.getChildren().add(title);
    }

    private void addLine(double x, double y) {
        line = new Line(x, y, x, y + 113);
        line.setStrokeWidth(3);
        line.setStroke(Color.BLACK);
        line.setEffect(new DropShadow(5, Color.WHITE));
        line.setScaleY(0);

        root.getChildren().add(line);
    }

    private void startAnimation() {
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

    private void addMenu(double x, double y) {
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
        Scene scene = new Scene(createContent());
        Scene gameScene = GameScene.CreateGameScene();
        Scene optionsScene = OptionScene.CreateOptionsScene();

        primaryStage.setTitle("Critter Main Menu");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Switch Scenes in single Stage
	public void switchScenes(Scene scene) {
		stage.setScene(scene);
	}

    public static void main(String[] args) {
        launch(args);
    }
}
