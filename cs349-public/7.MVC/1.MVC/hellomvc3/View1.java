package hellomvc3;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

class View1 extends Pane implements IView {
	private Button button = new Button("?");
	private Model model; // reference to the model

	View1(Model model) {
		// keep track of the model
		this.model = model;

		// setup the view (i.e. group+widget)
		this.setMinSize(HelloMVC3.WINDOW_WIDTH, HelloMVC3.WINDOW_HEIGHT/2);
		button.setMinSize(75, 25);
		button.setMaxSize(100, 50);

		// the previous controller code will just be handled here
		// we don't need always need a separate controller class!
		button.setOnMouseClicked(mouseEvent -> {
			System.out.println("Controller: changing Model (actionPerformed)");
			model.incrementCounter();
		});

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
