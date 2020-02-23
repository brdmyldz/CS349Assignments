import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SequentialAnimation extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button("Click");
        StackPane root = new StackPane(btn);
        Scene scene = new Scene(root, 400, 250);

        // Scale Transition
        Duration duration = Duration.millis(2000);
        ScaleTransition scaleTransition = new ScaleTransition(duration, btn);
        scaleTransition.setByX(1.5);
        scaleTransition.setByY(1.5);
        scaleTransition.play();

        // Rotation Transition
        RotateTransition rotateTransition = new RotateTransition(duration, btn);
        rotateTransition.setByAngle(360);

        // Sequence rotate and scale (automatic)
        SequentialTransition sequentialTransition = new SequentialTransition(rotateTransition, scaleTransition);
        sequentialTransition.play();

        // Fade Animation (on Button click)
        FadeTransition fadeTransition = new FadeTransition(duration, btn);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);

        btn.setOnAction(actionEvent -> {
            fadeTransition.play();
        });

        // Go!
        primaryStage.setTitle("Sequential Animations");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}