package hellomvc2;

import javafx.event.Event;
import javafx.event.EventHandler;

class Controller implements EventHandler {

	Model model;
	Controller(Model model) {
		this.model = model;
	}

	@Override
	public void handle(Event event) {
		System.out.println("Controller: changing Model (actionPerformed)");
		model.incrementCounter();
	}
}
