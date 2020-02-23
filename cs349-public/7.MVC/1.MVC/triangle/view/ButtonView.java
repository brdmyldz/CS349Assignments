package triangle.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import triangle.model.IView;
import triangle.model.TriangleModel;

import java.text.NumberFormat;

/*
 * View a triangle as values:  numbers for the base, height, and hypotenuse.
 * Manipulate with +/- buttons
 *
 * @author Original code by Byron Weber Becker
 * @author Refactored into JavaFX by Jeff Avery
 */

public class ButtonView extends Pane implements IView {
	private TriangleModel model;
	private Label base = new Label("0.0");
	private Label height = new Label("0.0");
	private Label hypo = new Label("0.0");

	private Button baseDn = new Button("-");
	private Button baseUp = new Button("+");
	private Button heightDn = new Button("-");
	private Button heightUp = new Button("+");

	private NumberFormat formatter = NumberFormat.getNumberInstance();

	public ButtonView(TriangleModel model) {
		super();
		this.model = model;

		this.layoutView();
		this.registerControllers();
		this.model.addView(this);
	}

	private void layoutView() {
		GridPane grid = new GridPane();
		grid.add(new Label("Base:"), 0, 0);
		grid.add(baseDn, 2, 0);
		grid.add(baseUp, 1, 0);
		grid.add(base, 3, 0);

		grid.add(new Label("Height:"), 0, 1);
		grid.add(heightDn, 2, 1);
		grid.add(heightUp, 1, 1);
		grid.add(height, 3, 1);

		grid.add(new Label("Hypotenuse:"), 0, 2);
		grid.add(hypo, 1, 2);

		grid.setVgap(5);
		grid.setHgap(20);
		grid.setPadding(new Insets(15));
		this.getChildren().add(grid);
	}

	private void registerControllers() {
		this.baseUp.setOnAction(event -> { model.setBase(model.getBase() + 1); } );
		this.baseDn.setOnAction(event -> { model.setBase(model.getBase() - 1); } );
		this.heightUp.setOnAction(event -> { model.setHeight(model.getHeight() + 1); } );
		this.heightDn.setOnAction(event -> { model.setHeight(model.getHeight() - 1); } );
	}

	@Override
	public void updateView() {
		base.setText(formatter.format(model.getBase()));
		height.setText(formatter.format(model.getHeight()));
		hypo.setText(formatter.format(model.getHypotenuse()));

		// Updating the view includes enabling/disabling components!
		baseUp.setDisable(model.getBase() > TriangleModel.MAX_SIDE);
		baseDn.setDisable(model.getBase() < 1);
		heightUp.setDisable(model.getHeight() > TriangleModel.MAX_SIDE);
		heightDn.setDisable(model.getHeight() < 1);
	}
}
