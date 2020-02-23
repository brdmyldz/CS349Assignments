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

public class PolygonHit1 extends Application {

	@Override
	public void start(Stage stage) {
		// Create a house using a standard polygon shape
		Polygon house = new Polygon();
		house.getPoints().addAll(0.0, 150.0, 100.0, 150.0,
				100.0, 50.0, 50.0, 0.0, 0.0, 50.0);

		// Create a scene graph holding the house
		Pane group = new Pane();
		group.getChildren().add(house);

		// Capture mouse interaction on the underlying group
		group.setOnMouseClicked(mouseEvent -> {
			System.out.println(mouseEvent.getX() + "," + mouseEvent.getY());

			// Checks if the house shape model contains this point
			// NOTE: checks against the POINTS not the transformed points
			Point2D point = new Point2D(mouseEvent.getX(), mouseEvent.getY());
			if (house.contains(point)) {
				house.setFill(Color.BLUE);
			} else {
				house.setFill(Color.RED);
			}
		});



		// Display the scene
		Scene scene = new Scene(group, 400, 500);
		stage.setScene(scene);
		stage.show();
	}
}