import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        // Fetch system info from JVM (reflection ftw!)
        SystemInfo info = new SystemInfo();

        // Create a root node (holding a label) and add to the scene
        StackPane root = new StackPane(new Label(info.toString()));
        Scene scene = new Scene(root , 200, 125);

        // Attach the scene to the stage and show it
        stage.setScene(scene);
        stage.setTitle("Java Info");
        stage.show();
    }
}