package hellomvc2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

// HelloMVC2
// A simple MVC example inspired by Joseph Mack, http://www.austintek.com/mvc/
// This version uses MVC: two views coordinated with the observer pattern and a separate controller.

public class HelloMVC2 extends Application {

	static double WINDOW_WIDTH = 200;
	static double WINDOW_HEIGHT = 200;

	@Override
	public void start(Stage stage) throws Exception {
		// create and initialize the Model to hold our counter
		Model model = new Model();

		// create the Controller, and tell it about Model
		// the controller will handle input and pass requests to the model
		Controller controller = new Controller(model);

		// create each view, and tell them about model and controller
		// the views will register themselves with these components
		// and handle displaying the data from the model
		View1 view1 = new View1(model, controller);
		View2 view2 = new View2(model, controller);

		GridPane grid = new GridPane();
		grid.add(view1, 0, 0);      // top-view
		grid.add(view2, 0, 1);      // bottom-view

		// Add grid to a scene (and the scene to the stage)
		Scene scene = new Scene(grid, WINDOW_WIDTH, WINDOW_HEIGHT);
		stage.setScene(scene);
		stage.show();
	}
}
