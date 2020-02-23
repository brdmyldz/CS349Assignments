import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

// Credit to https://www.genuinecoder.com/javafx-animation-tutorial/

public class TransitionAnimation extends Application {
    @Override
    public void start(Stage primaryStage) {
        // setup widgets
        Button button = new Button("Start");
        Image image = new Image("dressed_up_kitten.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(300);

        Group group = new Group(imageView, button);
        Scene scene = new Scene(group, 300, 200);

        // Create new translate transition -- affects button only!
        Duration duration = Duration.millis(2500);
        TranslateTransition transition = new TranslateTransition(duration, button);

        // Move by (200, 100);
        transition.setByX(200);
        transition.setByY(100);

        // Repeat animation twice
        // Go back to previous position the second loop
        transition.setCycleCount(2);
        transition.setAutoReverse(true);

        // Run the transition on click
        button.setOnAction(actionEvent -> {
            transition.play();
        });

        primaryStage.setTitle("Transition Animation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}