package hellomvc3;

import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

class View2 extends Pane implements IView {

	private TextArea text = new TextArea("");
	private Model model; // reference to the model

	View2(Model model) {
		// keep track of the model
		this.model = model;

		// set label properties
		text.setMaxSize(HelloMVC3.WINDOW_WIDTH, HelloMVC3.WINDOW_HEIGHT/2);
		text.setWrapText(true);

		// the previous controller code will just be handled here
		// we don't need always need a separate controller class!
		text.setOnMouseClicked(mouseEvent -> {
			System.out.println("Controller: changing Model (actionPerformed)");
			model.incrementCounter();
		});

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
