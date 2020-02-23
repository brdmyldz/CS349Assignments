import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

// Use Event Filters to process mouse-clicks on the capture phase.

public class EventHandlers1 extends Application {
    final int scene_width = 400;
    final int scene_height = 300;

    @Override
    public void start(Stage stage) throws Exception {
        StackPane group = new StackPane();
        Scene scene = new Scene(group, scene_width, scene_height);

        // setup background
        scene.setFill(Color.GRAY);
        EventHandler<MouseEvent> sceneHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                System.out.println("Scene clicked (" + e.getX() + "," + e.getY() + ")");
                if (scene.getFill() == Color.GRAY) {
                    scene.setFill(Color.DARKGRAY);
                } else {
                    scene.setFill(Color.GRAY);
                }
            }
        };
        // add the handler as an EventFilter, which is fired during the CAPTURE (down) phase
        scene.addEventFilter(MouseEvent.MOUSE_CLICKED, sceneHandler);

        // add foreground shape
        Rectangle rectangle = new Rectangle(125, 150, Color.YELLOW);
        EventHandler<MouseEvent> rectangleHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                System.out.println("Shape clicked (" + e.getX() + "," + e.getY() + ")");
                if (rectangle.getFill() == Color.YELLOW) {
                    rectangle.setFill(Color.DARKGOLDENROD);
                } else {
                    rectangle.setFill(Color.YELLOW);
                }
            }
        };
        // add the handler as an EventFilter, which is fired during the CAPTURE (down) phase
        rectangle.addEventFilter(MouseEvent.MOUSE_CLICKED, rectangleHandler);

        // add to the scene and display
        group.getChildren().add(rectangle);

        stage.setOnCloseRequest(windowEvent -> {
            System.out.println("Quitting");
            System.exit(0);
        });
        stage.setTitle("EventHandlers1: Event Filters");
        stage.setScene(scene);
        stage.show();
    }
}
