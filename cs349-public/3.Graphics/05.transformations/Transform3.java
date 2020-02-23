/*
 * Transform3
 * Draw using a canvas, and graphics context (GC). This results in a different effect than
 * the previous methods, since the operations that we define in the Graphics Context apply
 * to *everything* that is drawn on the canvas. i.e. they are cumulative.
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

    // house at the origin
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

        // Use the GC scale and translate methods
        gc.setFill(Color.BLUEVIOLET);
        gc.translate(100, 100);
        gc.scale(2.0, 2.0);
        gc.fillPolygon(xpoints, ypoints, npoints);

        // Use the scale and translate that were set in the GC above
        gc.setFill(Color.YELLOW);
        gc.fillRect(0.0, 0.0, 25.0, 25.0);

        // Add the canvas to the scene
        root.getChildren().add(canvas);

        // Add the scene to the stage and show it
        stage.setScene(scene);
        stage.show();
    }
}