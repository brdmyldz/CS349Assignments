package hellomvc2;

import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

class View2 extends Pane implements IView {

	private TextArea text = new TextArea("");
	private Model model; // reference to the model

	View2(Model model, Controller controller) {
		// keep track of the model
		this.model = model;

		// set label properties
		text.setMaxSize(HelloMVC2.WINDOW_WIDTH, HelloMVC2.WINDOW_HEIGHT/2);
		text.setWrapText(true);

		// register the controller as a handler for this view
		text.addEventHandler(MouseEvent.MOUSE_CLICKED, controller);

		// add label widget to the pane
		this.getChildren().add(text);

		// register with the model when we're ready to start receiving data
		model.addView(this);
	}

	// When notified by the model that things have changed,
	// update to display the new value
	public void updateView() {
		System.out.println("View2: updateView");

		// display an 'X' for each counter value
		StringBuilder s = new StringBuilder();
		for (int i=0; i<this.model.getCounterValue(); i++)
			s.append("X");
		this.text.setText(s.toString());
	}
}
