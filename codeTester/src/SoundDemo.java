import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.net.URL;

public class SoundDemo extends Application {
    @Override
    public void start(Stage stage) {
        Button button = new Button("Click Here");
        button.setOnMouseClicked(mouseEvent -> {
            String sound = getClass().getClassLoader().getResource("click.mp3").toString();
            AudioClip clip = new AudioClip(sound);
            clip.play();
        });

        // Attach the scene to the stage and show it
        StackPane root = new StackPane(button);
        Scene scene = new Scene(root, 200, 100);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Sound Demo");
        stage.show();
    }
}