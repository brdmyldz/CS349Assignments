import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ScaleAnimation extends Application {

    @Override
    public void start(Stage primaryStage) {
        Image image = new Image("sad_kitten.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(250);

        StackPane root = new StackPane(imageView);
        Scene scene = new Scene(root, 500, 500);

        // Create new scale transition
        Duration duration = Duration.millis(2500);
        ScaleTransition scaleTransition = new ScaleTransition(duration, imageView);

        // How much to scale
        scaleTransition.setByX(2.0);
        scaleTransition.setByY(2.0);

        // Trigger on mouse click
        scene.setOnMouseClicked(mouseEvent -> {
            scaleTransition.play();
        });

        primaryStage.setTitle("Scale Animation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}