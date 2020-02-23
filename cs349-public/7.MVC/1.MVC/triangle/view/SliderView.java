package triangle.view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import triangle.model.IView;
import triangle.model.TriangleModel;

/*
 * View a triangle as values:  numbers for the base, height, and hypotenuse.
 * Manipulate with sliders. Hypotenuse reflects calculated value from model.
 *
 * @author Original code by Byron Weber Becker
 * @author Refactored into JavaFX by Jeff Avery
 */

public class SliderView extends Pane implements IView {
	private TriangleModel model;
	private Slider base = new Slider();
	private Slider height = new Slider();
	private Slider hypo = new Slider();

	public SliderView(TriangleModel model) {
		super();
		this.model = model;

		this.layoutView();
		this.registerControllers();
		this.model.addView(this);
	}

	private void layoutView() {
		GridPane grid = new GridPane();
		grid.add(new Label("Base:"), 0, 0);
		grid.add(base, 1, 0);

		grid.add(new Label("Height:"), 0, 1);
		grid.add(height, 1, 1);

		grid.add(new Label("Hypotenuse:"), 0, 2);
		hypo.setDisable(true);
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
		base.setValue(model.getBase());
		height.setValue(model.getHeight());
		hypo.setValue(model.getHypotenuse());
	}
}
