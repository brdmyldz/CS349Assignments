package hellomvc2;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

class View1 extends Pane implements IView {
	private Button button = new Button("?");
	private Model model; // reference to the model

	View1(Model model, Controller controller) {
		// keep track of the model
		this.model = model;

		// setup the view (i.e. group+widget)
		this.setMinSize(HelloMVC2.WINDOW_WIDTH, HelloMVC2.WINDOW_HEIGHT/2);
		button.setMinSize(75, 25);
		button.setMaxSize(100, 50);

		// register the controller as a handler for this view
		button.addEventHandler(MouseEvent.MOUSE_CLICKED, controller);

		// add button widget to the pane
		this.getChildren().add(button);

		// register with the model when we're ready to start receiving data
		model.addView(this);
	}

	// When notified by the model that things have changed,
	// update to display the new value
	public void updateView() {
		System.out.println("View: updateView");
		button.setText(Integer.toString(model.getCounterValue()));
	}
}
