package triangle;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import triangle.model.TriangleModel;
import triangle.view.TextView;

public class Triangle1 extends Application {
	@Override
	public void start(Stage stage) throws Exception {
		// create and initialize the Model to hold our counter
		TriangleModel model = new TriangleModel();

		// create a view, and tell it about the model
		TextView view = new TextView(model);

		// add view to the scene (and the scene to the stage)
		Scene scene = new Scene(view, 300, 150);
		stage.setScene(scene);
		stage.setTitle("Triangle1");
		stage.show();
	}
}
