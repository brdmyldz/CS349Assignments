import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

// Demonstrates switching between scenes at runtime

public class SwitchScenes extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("Switch Scenes");

        // scene one
        Button button1 = new Button("Go To Scene2");
        Scene scene1 = new Scene(new StackPane(button1), 300, 150);

        // scene two
        Button button2 = new Button("Go To Scene1");
        Scene scene2 = new Scene(new StackPane(button2), 300, 150);

        // tell the buttons what to do when clicked (we discuss under Events later)
        // delay adding handlers until everything is instantiated
        button1.setOnAction(event -> {
            stage.setTitle("Scene 2");
            stage.setScene(scene2);
        });
        button2.setOnAction(event -> {
            stage.setTitle("Scene 1");
            stage.setScene(scene1);
        });

        // show starting scene
        stage.setTitle("Scene 1");
        stage.setScene(scene1);
        stage.show();
    }
}
