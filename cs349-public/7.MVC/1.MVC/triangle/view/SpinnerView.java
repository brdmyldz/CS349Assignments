package triangle.view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import triangle.model.IView;
import triangle.model.TriangleModel;

/*
 * View a triangle as values:  numbers for the base, height, and hypotenuse.
 * Manipulate with spinners. Hypotenuse reflects calculated value from model.
 *
 * @author Original code by Byron Weber Becker
 * @author Refactored into JavaFX by Jeff Avery
 */

public class SpinnerView extends Pane implements IView {
	private TriangleModel model;
	private Spinner<Integer> base = new Spinner<>(1, TriangleModel.MAX_SIDE, 1);
	private Spinner<Integer> height = new Spinner<>(1, TriangleModel.MAX_SIDE, 1);
	private Spinner<Integer> hypo = new Spinner<>(1, TriangleModel.MAX_SIDE, 1);

	public SpinnerView(TriangleModel model) {
		super();
		this.model = model;

		this.layoutView();
		this.registerControllers();
		this.model.addView(this);
	}

	private void layoutView() {
		GridPane grid = new GridPane();
		grid.add(new Label("Base:"), 0, 0);
		base.setEditable(true);
		grid.add(base, 1, 0);

		grid.add(new Label("Height:"), 0, 1);
		height.setEditable(true);
		grid.add(height, 1, 1);

		grid.add(new Label("Hypotenuse:"), 0, 2);
		hypo.setEditable(false);
		grid.add(hypo, 1, 2);

		grid.setVgap(5);
		grid.setHgap(20);
		grid.setPadding(new Insets(15));
		this.getChildren().add(grid);
	}

	private void registerControllers() {
		base.setOnMouseDragged(event -> { model.setBase(base.getValue()); } );
		height.setOnMouseDragged(event -> { model.setHeight(height.getValue()); } );
	}

	@Override
	public void updateView() {
		SpinnerValueFactory factory = base.getValueFactory();
		Object convertedValue = factory.getConverter().fromString(String.valueOf(model.getBase()));
		factory.setValue(convertedValue);

		factory = height.getValueFactory();
		convertedValue = factory.getConverter().fromString(String.valueOf(model.getHeight()));
		factory.setValue(convertedValue);

		factory = hypo.getValueFactory();
		convertedValue = factory.getConverter().fromString(String.valueOf(model.getHypotenuse()));
		factory.setValue(convertedValue);
	}
}
