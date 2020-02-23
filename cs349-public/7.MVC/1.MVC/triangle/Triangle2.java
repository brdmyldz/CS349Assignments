package triangle;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import triangle.model.TriangleModel;
import triangle.view.ButtonView;
import triangle.view.SliderView;
import triangle.view.SpinnerView;
import triangle.view.TextView;

public class Triangle2 extends Application {
	@Override
	public void start(Stage stage) throws Exception {
		// create and initialize the Model to hold our counter
		TriangleModel model = new TriangleModel();

		// create and register views
		TextView view1 = new TextView(model);
		ButtonView view2 = new ButtonView(model);
		SliderView view3 = new SliderView(model);
		SpinnerView view4 = new SpinnerView(model);

		// setup grid with views
		VBox group = new VBox();
		group.setSpacing(15);
		group.setPadding(new Insets(15));
		group.getChildren().add(view1);
		group.getChildren().add(view2);
		group.getChildren().add(view3);
		group.getChildren().add(view4);

		// add grid to the scene and stage
		Scene scene = new Scene(group, 300, 500);
		stage.setScene(scene);
		stage.setTitle("Triangle2");
		stage.show();
	}
}
