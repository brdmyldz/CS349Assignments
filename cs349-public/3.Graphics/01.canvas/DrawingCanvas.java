import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

// Draw using a canvas class instead.

public class DrawingCanvas extends Application {

    @Override
    public void start(Stage stage) {

        // Create a scene graph with a root node
        // This will hold the objects that we want to display on the stage
        Group root = new Group();
        Scene scene = new Scene(root, 300, 300, Color.BLACK);

        // Create a canvas as a drawing surface
        final Canvas canvas = new Canvas(250,250);

        // Use the graphics context to draw on a canvas
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(5);
        gc.setStroke(Color.WHITE);

        gc.setFill(Color.WHITE);
        gc.fillRect(75,75,100,100);

        gc.setFill(Color.AQUA);
        gc.fillRect(100,100,100,100);

        gc.setFill(Color.YELLOWGREEN);
        gc.fillRect(125,125,100,100);

        // Add the canvas to the scene
        root.getChildren().add(canvas);

        // Add the scene to the stage and show it
        stage.setTitle("Drawing Canvas");
        stage.setScene(scene);
        stage.show();
    }
}