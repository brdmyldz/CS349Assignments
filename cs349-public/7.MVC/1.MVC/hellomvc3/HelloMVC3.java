package hellomvc3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

// HelloMVC
// A simple MVC example inspired by Joseph Mack, http://www.austintek.com/mvc/
// This version uses MVC: two views coordinated with the observer pattern, but no separate controller.

public class HelloMVC3 extends Application {

	static double WINDOW_WIDTH = 200;
	static double WINDOW_HEIGHT = 200;

	@Override
	public void start(Stage stage) throws Exception {
		// create and initialize the Model to hold our counter
		Model model = new Model();

		// create each view, and tell them about the model
		// the views will register themselves with the model
		View1 view1 = new View1(model);
		View2 view2 = new View2(model);

		GridPane grid = new GridPane();
		grid.add(view1, 0, 0);      // top-view
		grid.add(view2, 0, 1);      // bottom-view

		// Add grid to a scene (and the scene to the stage)
		Scene scene = new Scene(grid, WINDOW_WIDTH, WINDOW_HEIGHT);
		stage.setScene(scene);
		stage.show();
	}
}
