import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

// Use Event Handler convenience methods which fire on the Bubble (up) phase

public class EventHandlers3 extends Application {
    final int scene_width = 400;
    final int scene_height = 300;

    @Override
    public void start(Stage stage) throws Exception {
        StackPane group = new StackPane();
        Scene scene = new Scene(group, scene_width, scene_height);

        // setup background
        scene.setFill(Color.GRAY);
        scene.setOnMouseClicked(e -> {
            System.out.println("Scene clicked (" + e.getX() + "," + e.getY() + ")");
            if (scene.getFill() == Color.GRAY) {
                scene.setFill(Color.DARKGRAY);
            } else {
                scene.setFill(Color.GRAY);
            }
            e.consume();   // this will stop the event from being passed along
        });

        // add foreground shape
        Rectangle rectangle = new Rectangle(125, 150, Color.YELLOW);
        rectangle.setOnMouseClicked(e -> {
            System.out.println("Shape clicked (" + e.getX() + "," + e.getY() + ")");
            if (rectangle.getFill() == Color.YELLOW) {
                rectangle.setFill(Color.DARKGOLDENROD);
            } else {
                rectangle.setFill(Color.YELLOW);
            }
            e.consume();   // this will stop the event from being passed along
        });

        // add to the scene and display
        group.getChildren().add(rectangle);

        stage.setOnCloseRequest(windowEvent -> {
            System.out.println("Quitting");
            System.exit(0);
        });
        stage.setTitle("EventFilters3: Convenience Methods");
        stage.setScene(scene);
        stage.show();
    }
}
