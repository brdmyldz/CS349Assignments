/*
 * Transform3
 * Draw using a canvas, and graphics context (GC).
 */

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

// Draw transformations using a canvas class.

public class Transform3 extends Application {
    final int screen_width = 500;
    final int screen_height = 500;
    final double [] xpoints = new double[]{0.0, 100.0, 100.0, 50.0, 0.0};
    final double [] ypoints = new double[]{150.0, 150.0, 50.0, 0.0, 50.0};
    final int npoints = 5;

    @Override
    public void start(Stage stage) {

        // Create a scene graph with a root node
        // This will hold the objects that we want to display on the stage
        Group root = new Group();
        Scene scene = new Scene(root, screen_width, screen_height, Color.WHITE);

        // Create a canvas as a drawing surface
        final Canvas canvas = new Canvas(screen_width, screen_height);

        // Use the graphics context to draw on the canvas
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.MEDIUMAQUAMARINE);
        gc.fillPolygon(xpoints, ypoints, npoints);

        // Use the GC scale and translate methods to change the parameters used to draw
        // This will affect anything that is drawn from this point forward
        gc.setFill(Color.BLUEVIOLET);
        gc.scale(2.0, 2.0);
        gc.translate(100, 100);
        gc.fillPolygon(xpoints, ypoints, npoints);

        // This rect will use the scale and translate from above
        gc.setFill(Color.YELLOW);
        gc.fillRect(0.0, 0.0, 25.0, 25.0);

        // Add the canvas to the scene
        root.getChildren().add(canvas);

        // Add the scene to the stage and show it
        stage.setScene(scene);
        stage.show();
    }
}