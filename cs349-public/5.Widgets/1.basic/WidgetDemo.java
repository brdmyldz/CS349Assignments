import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class WidgetDemo extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		VBox root = new VBox();
		root.setPadding(new Insets(10));
		root.setSpacing(10);
		Scene scene = new Scene(root, 500, 200, Color.WHITE);

		Label label = new Label("");

		Button button = new Button("My Button");
		button.setOnMouseClicked(mouseEvent -> { label.setText("button"); });

		CheckBox checkbox = new CheckBox("My Checkbox");
		checkbox.setSelected(false);
		checkbox.setOnAction(actionEvent -> { label.setText("checkbox " + (((CheckBox) actionEvent.getSource()).isSelected())); });

		Slider slider = new Slider(0, 100, 50);
		slider.setOnMouseDragged(mouseEvent -> {
			label.setText("slider " + ((Slider) mouseEvent.getSource()).getValue());
		});

		root.getChildren().add(button);
		root.getChildren().add(checkbox);
		root.getChildren().add(slider);
		root.getChildren().add(label);

		stage.setScene(scene);
		stage.setTitle("WidgetDemo");
		stage.setOnCloseRequest(windowEvent -> { System.exit(0); });
		stage.show();
	}
//
//
//		// create a radio button group
//		ButtonGroup radiobuttons = new ButtonGroup();
//		JPanel radioPanel = new JPanel(new GridLayout(1, 0));
//		for (String s: new String[] {"A", "B", "C"})
//		{
//			JRadioButton rb = new JRadioButton(s);
//			rb.addActionListener(radioButtonListener);
//			radiobuttons.add(rb);
//			radioPanel.add(rb);
//		}
//
//		// create a menu
//		JMenu menu = new JMenu("Choices");
//		// create some menu choices
//		for (String s: new String[] {"apple", "orange", "banana", "pear" })
//		{
//			// add this menu item to the menu
//			JMenuItem mi = new JMenuItem(s);
//			// set the listener when events occur
//			mi.addActionListener(menuItemListener);
//			// add this menu item to the menu
//			menu.add(mi);
//		}
//		JMenuBar menubar = new JMenuBar();
//		menubar.add(menu);

		// create a label
		// (we'll use this to display messages)
//		label = new JLabel("message");
//		// set some properties to customize how it looks
//		label.setPreferredSize(new Dimension(500, 60));
//		label.setHorizontalAlignment( SwingConstants.CENTER );
//		label.setBackground(Color.WHITE);
//		label.setBorder(BorderFactory.createLineBorder(Color.black));
//		label.setFont(new Font("SansSerif", Font.PLAIN, 30));



}
