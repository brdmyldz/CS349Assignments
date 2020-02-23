/*
 * Uses event handlers to hit-test closed polygon.
 * Click to add points to the polygon
 */

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class PolygonHit3 extends Application {

    Polygon polygon = new Polygon();

    @Override
    public void start(Stage stage) {
        // Create a scene graph holding the group
        Pane group = new Pane();

        // Capture mouse interaction on the underlying group
        group.setOnMouseClicked(mouseEvent -> polygon.getPoints().addAll(
                new Double[]{mouseEvent.getX(), mouseEvent.getY()}));
        polygon.setOnMouseEntered(mouseEvent -> polygon.setFill(Color.BLUE));
        polygon.setOnMouseExited(mouseEvent -> polygon.setFill(Color.RED));

        // Add the scene to the stage and display it
        group.getChildren().add(polygon);
        Scene scene = new Scene(group, 400, 500);
        stage.setScene(scene);
        stage.show();
    }
}

