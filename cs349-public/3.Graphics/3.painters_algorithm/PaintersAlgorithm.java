/* 
 * PaintersAlgorithm.java (c) 2020 Jeff Avery
 * Demonstrate stacking components on a canvas
 * Shapes  will be painted in the order in which they are added to a list (i.e. back->front)
 */

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.ArrayList;

public class PaintersAlgorithm extends Application {
	@Override
	public void start(Stage stage) {
		// list of shapes to draw
		ArrayList<IShape> shapes = new ArrayList<>();

		// create a scene graph (root group + canvas)
		Group root = new Group();
		Canvas canvas = new Canvas(400, 400);
		root.getChildren().add(canvas);

		// add scene graph to stage
		stage.setScene(new Scene(root));
		stage.show();

		// create shapes and add to the list
		// they will be drawn in the order in which they are added to the list, so add background first!
		shapes.add(new SRectangle(10, 10, 380, 380, Color.GRAY));		// background
		shapes.add(new SRectangle(150, 150, 125, 150, Color.YELLOW));	// foreground
		shapes.add(new SCircle(100, 100, 125, Color.BLUE));					// foreground
		shapes.add(new SRectangle(200, 125, 100, 125, Color.GREEN));		// foreground

		// draw shapes on the canvas
		drawShapes(shapes, canvas);
	}

	// separate method that could be called later to draw shapes in order
	private void drawShapes(ArrayList<IShape> shapes, Canvas canvas) {
		for (IShape shape : shapes) {
			shape.draw(canvas);
		}
	}
}