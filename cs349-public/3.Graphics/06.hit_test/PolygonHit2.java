/*
 * Uses the Node's built-in contains() method
 * to check if mouse coordinates pass inside hit-test
 */

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicReference;

public class PolygonHit2 extends Application {

    @Override
    public void start(Stage stage) {
        // Create a house using a standard polygon shape
        Polygon house = new Polygon();
        house.getPoints().addAll(0.0, 150.0, 100.0, 150.0,
                100.0, 50.0, 50.0, 0.0, 0.0, 50.0);

        // Create a scene graph holding the house
        Pane group = new Pane();
        group.getChildren().add(house);

        // Check the house separately
        house.setOnMouseClicked(mouseEvent -> {
            System.out.println(mouseEvent.getX() + "," + mouseEvent.getY());
            house.setFill(Color.BLUE);
            mouseEvent.consume();
        });

        // Check the group separately
        group.setOnMouseClicked(mouseEvent -> {
            System.out.println(mouseEvent.getX() + "," + mouseEvent.getY());
            house.setFill(Color.RED);
        });

        // Display the scene
        Scene scene = new Scene(group, 400, 500);
        stage.setScene(scene);
        stage.show();
    }
}