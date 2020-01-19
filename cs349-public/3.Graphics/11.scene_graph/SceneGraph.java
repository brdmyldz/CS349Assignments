import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SceneGraph extends Application {
	final int screen_width = 500;
	final int screen_height = 500;
	double previous_x, previous_y;
	Sprite selectedSprite;

	@Override
	public void start(Stage stage) {
		// setup a canvas to use as a drawing surface
		Canvas canvas = new Canvas(screen_width, screen_height);
		Scene scene = new Scene(new Group(canvas), screen_width, screen_height);

		// create hierarchy of sprites
		Sprite root = createSprites();

		// add listeners
		// click selects the shape under the cursor
		// we have sprites do it since they track their own locations
		canvas.setOnMousePressed(mouseEvent -> {
			Sprite hit = root.getSpriteHit(mouseEvent.getX(), mouseEvent.getY());
			if (hit != null) {
				selectedSprite = hit;
				System.out.println("Selected " + selectedSprite.toString());
				previous_x = mouseEvent.getX();
				previous_y = mouseEvent.getY();
			}
		});

		// un-selects any selected shape
		canvas.setOnMouseReleased( mouseEvent -> {
			selectedSprite = null;
			System.out.println("Unselected");
		});

		// dragged translates the shape based on change in mouse position
		// since shapes are defined relative to one another, they will follow their parent
		canvas.setOnMouseDragged(mouseEvent -> {
			if (selectedSprite != null) {
				// translate shape to follow the mouse cursor
				double dx = mouseEvent.getX() - previous_x;
				double dy = mouseEvent.getY() - previous_y;
				selectedSprite.translate(dx, dy);

				// draw tree in new position
				draw(canvas, root);
				System.out.println(".. moved "
						+ selectedSprite.toString()
						+ " from (" + previous_x + "," + previous_y + ")"
						+ " to (" + mouseEvent.getX() + "," + mouseEvent.getY() + ")"
						+ " -- dx: " + dx + ", dy: " + dy);

				// save coordinates for next event
				previous_x = mouseEvent.getX();
				previous_y = mouseEvent.getY();
			}
		});

		// draw the sprites on the canvas
		draw(canvas, root);

		// show the scene including the canvas
		stage.setScene(scene);
		stage.show();
	}

	private void draw(Canvas canvas, Sprite root) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		root.draw(gc);
	}
	
	private Sprite createSprites() {
		// create a bunch of different sprites at the origin
		Sprite sprite1 = new RectangleSprite(80, 50);
		Sprite sprite2 = new RectangleSprite(50, 40);
		Sprite sprite3 = new RectangleSprite(70, 30);

		 // build scene graph aka tree from them
		sprite1.addChild(sprite2);
		sprite2.addChild(sprite3);

		// translate them to a starting position
		// this also places them beside one another
		sprite1.translate(10, 20);
		sprite2.translate(80, 5);
		sprite3.translate(50, 5);

		// return root of the tree
		return sprite1;
	}
}
