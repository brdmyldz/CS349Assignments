package triangle.view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import triangle.model.IView;
import javafx.scene.layout.Pane;
import triangle.model.TriangleModel;

/*
 * View a triangle as text:  numbers for the base, height, and hypotenuse.
 * This view leaves a lot to be desired in terms of "polish".
 * -- inconsistent decimal precision displayed, esp. in hypotenuse
 * -- tabbing or clicking out of a text field doesn't update
 * -- can edit the hypotenuse field
 *
 * @author Original code by Byron Weber Becker
 * @author Refactored into JavaFX by Jeff Avery
 */
public class TextView extends Pane implements IView {

	private TriangleModel model;

	private TextField baseTF = new TextField();
	private TextField heightTF = new TextField();
	private Label hypoTF = new Label();

	public TextView(TriangleModel model) {
		super();
		this.model = model;
		this.layoutView();
		this.registerControllers();

		// Add a this view as a listener to the model
		this.model.addView(this);
	}

	/**
	 * What to do when the model changes.
	 */
	public void updateView() {
		baseTF.setText("" + model.getBase());
		heightTF.setText("" + model.getHeight());
		hypoTF.setText("" + model.getHypotenuse());
	}

	private void layoutView() {
		GridPane grid = new GridPane();
		grid.add(new Label("Base:"), 0, 0);
		grid.add(baseTF, 1, 0);
		grid.add(new Label("Height:"), 0, 1);
		grid.add(heightTF, 1, 1);
		grid.add(new Label("Hypotenuse:"), 0, 2);
		grid.add(hypoTF, 1, 2);

		grid.setVgap(5);
		grid.setHgap(20);
		grid.setPadding(new Insets(15));
		this.getChildren().add(grid);
	}

	private void registerControllers() {
		// Add a controller to interpret user actions in the base text field
		this.baseTF.setOnAction(event -> {
			double base = Double.parseDouble(baseTF.getText());
			model.setBase(base);
		});

		// Add a controller to interpret user actions in the height text field
		this.heightTF.setOnAction(event -> {
			double height = Double.parseDouble(heightTF.getText());
			model.setHeight(height);
		});
	}
}