import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/*
 * Layouts: show examples of common layouts in JavaFX
 *  BasicWindow class contains defaults for the Window
 *  ButtonFactory creates buttons using default properties
 */
public class Layouts extends Application {
    final double STAGE_WIDTH = 400;
    final double STAGE_HEIGHT = 300;

    final double BUTTON_MIN_WIDTH = 50;
    final double BUTTON_PREF_WIDTH = 100;
    final double BUTTON_MAX_WIDTH = 200;

    final double LABEL_X = 10;
    final double BUTTON_X = 75;

    @Override
    public void start(Stage stage) {
        // root of our tree, doesn't handle resizing or layout in this case
        Pane pane = new Pane();

        // button to create the windows
        Button start = new Button("Click here to create layouts");
        start.relocate(BUTTON_X, 25);
        start.setOnAction(actionEvent -> {
            new PaneWindow(50, 50);
            new StackPaneWindow(100, 100);
            new FlowPaneWindow(150, 150);
            new HBoxWindow(200, 200);
            new VBoxWindow(250, 250);
            new GridPaneWindow(300, 300);
            new TilePaneWindow(350, 350);
            new BorderPaneWindow(400, 400);
        });
        pane.getChildren().add(start);

        // button to demonstrate minimum size
        Label min_label = new Label("Minimum");
        min_label.relocate(LABEL_X, 100);
        pane.getChildren().add(min_label);

        Button min = new Button(String.valueOf(BUTTON_MIN_WIDTH));
        min.setPrefWidth(BUTTON_MIN_WIDTH);
        min.relocate(BUTTON_X, 100);
        pane.getChildren().add(min);

        // button to demonstrate preferred size
        Label pref_label = new Label("Preferred");
        pref_label.relocate(LABEL_X, 150);
        pane.getChildren().add(pref_label);

        Button pref = new Button(String.valueOf(BUTTON_PREF_WIDTH));
        pref.setPrefWidth(BUTTON_PREF_WIDTH);
        pref.relocate(BUTTON_X, 150);
        pane.getChildren().add(pref);

        // button to demonstrate max size
        Label max_label = new Label("Maximum");
        max_label.relocate(LABEL_X, 200);
        pane.getChildren().add(max_label);

        Button max = new Button(String.valueOf(BUTTON_MAX_WIDTH));
        max.setPrefWidth(BUTTON_MAX_WIDTH);
        max.relocate(BUTTON_X, 200);
        pane.getChildren().add(max);

        // scene holding this layout
        Scene scene = new Scene(pane);

        // setup starting window
        stage.setX(0);
        stage.setY(0);
        stage.setWidth(300);
        stage.setHeight(300);
        stage.setScene(scene);
        stage.setTitle("Layouts Demo");
        stage.setOnCloseRequest(windowEvent -> {
            System.exit(0);
        });
        stage.show();
    }

    /*
     * Base class for all of our windows
     * Used to set default values for all windows
     */
    private class StandardWindow extends Stage {
        StandardWindow() {
            this(0f, 0f);
        }

        StandardWindow(float x, float y) {
            super();
            this.setX(x);
            this.setY(y);
            this.setWidth(STAGE_WIDTH);
            this.setHeight(STAGE_HEIGHT);
            this.setResizable(true);
        }
    }

    // Customized button
    // Used to set default values for all buttons
    // Lets us manipulate MIN, MAX, PREFERRED sizes in one place for all demos
    private class StandardButton extends Button {
        StandardButton() {
            this("Untitled");
        }

        StandardButton(String caption) {
            super(caption);
            // setText(caption); // call to super class already does this
            setVisible(true);
            setMinWidth(BUTTON_MIN_WIDTH);
            setPrefWidth(BUTTON_PREF_WIDTH);
            setMaxWidth(BUTTON_MAX_WIDTH);
        }
    }

    /*
     * Classes for each type of Layout that we wish to demo
     * These classes need to instantiate and add a reasonable number of buttons
     * to demo that particular functionality.
     */
    class PaneWindow extends StandardWindow {
        PaneWindow() {
            this(0f, 0f);
        }

        PaneWindow(float x, float y) {
            super(x, y);
            Button button1 = new StandardButton("Button One");
            Button button2 = new StandardButton("Button Two");
            Button button3 = new StandardButton("Button Three");
            Button button4 = new StandardButton("Button Four");
            Button button5 = new StandardButton("Button Five");
            Button button6 = new StandardButton("Button Six");
            Button button7 = new StandardButton("Button Seven");
            Button button8 = new StandardButton("Button Eight");
            Button button9 = new StandardButton("Button Nine");

            Pane root = new Pane();
            root.getChildren().add(button1);
            root.getChildren().add(button2);
            root.getChildren().add(button3);
            root.getChildren().add(button4);
            root.getChildren().add(button5);
            root.getChildren().add(button6);
            root.getChildren().add(button7);
            root.getChildren().add(button8);
            root.getChildren().add(button9);

            this.setScene(new Scene(root));
            this.setTitle("Pane");
            this.show();
        }
    }

    class StackPaneWindow extends StandardWindow {
        StackPaneWindow() {
            this(0f, 0f);
        }

        StackPaneWindow(float x, float y) {
            super(x, y);
            Button button1 = new StandardButton("Button One");
            Button button2 = new StandardButton("Button Two");
            Button button3 = new StandardButton("Button Three");
            Button button4 = new StandardButton("Button Four");
            StackPane root = new StackPane(button1, button2, button3, button4);

            this.setScene(new Scene(root));
            this.setTitle("StackPane");
            this.show();
        }
    }

    class FlowPaneWindow extends StandardWindow {
        FlowPaneWindow() {
            this(0f, 0f);
        }

        FlowPaneWindow(float x, float y) {
            super(x, y);
            Button button1 = new StandardButton("Button One");
            Button button2 = new StandardButton("Button Two");
            Button button3 = new StandardButton("Button Three");
            Button button4 = new StandardButton("Button Four");
            Button button5 = new StandardButton("Button Five");
            Button button6 = new StandardButton("Button Six");
            FlowPane root = new FlowPane(button1, button2, button3, button4, button5, button6);
            root.setAlignment(Pos.CENTER);
            root.setPadding(new Insets(10.0));

            this.setScene(new Scene(root));
            this.setTitle("FlowPane");
            this.show();
        }
    }

    class HBoxWindow extends StandardWindow {
        HBoxWindow() {
            this(0f, 0f);
        }

        HBoxWindow(float x, float y) {
            super(x, y);
            Button button1 = new StandardButton("Button One");
            Button button2 = new StandardButton("Button Two");
            Button button3 = new StandardButton("Button Three");
            Button button4 = new StandardButton("Button Four");
            HBox root = new HBox(button1, button2, button3, button4);
            root.setAlignment(Pos.CENTER);
            root.setPadding(new Insets(10.0));
            root.setSpacing(10.0);

            this.setScene(new Scene(root));
            this.setTitle("HBox");
            this.show();
        }
    }

    class VBoxWindow extends StandardWindow {
        VBoxWindow() {
            this(0f, 0f);
        }

        VBoxWindow(float x, float y) {
            super(x, y);
            Button button1 = new StandardButton("Button One");
            Button button2 = new StandardButton("Button Two");
            Button button3 = new StandardButton("Button Three");
            Button button4 = new StandardButton("Button Four");
            VBox root = new VBox(button1, button2, button3, button4);
            root.setAlignment(Pos.CENTER);
            root.setPadding(new Insets(10.0));
            root.setSpacing(10.0);

            this.setScene(new Scene(root));
            this.setTitle("VBox");
            this.show();
        }
    }

    class GridPaneWindow extends StandardWindow {
        GridPaneWindow() {
            this(0f, 0f);
        }

        GridPaneWindow(float x, float y) {
            super(x, y);

            Button button1 = new StandardButton("Button One");
            Button button2 = new StandardButton("Button Two");
            Button button3 = new StandardButton("Button Three");
            Button button4 = new StandardButton("Button Four");
            Button button5 = new StandardButton("Button Five");
            Button button6 = new StandardButton("Button Six");
            Button button7 = new StandardButton("Button Seven");
            Button button8 = new StandardButton("Button Eight");
            Button button9 = new StandardButton("Button Nine");

            GridPane root = new GridPane();
            root.add(button1, 0, 0);
            root.add(button2, 1, 0);
            root.add(button3, 2, 0);
            root.add(button4, 0, 1);
            root.add(button5, 1, 1);
            root.add(button6, 2, 1);
            root.add(button7, 0, 2);
            root.add(button8, 1, 2);
            root.add(button9, 2, 2);
            root.setAlignment(Pos.CENTER);
            root.setPadding(new Insets(10.0));
            root.setHgap(10);
            root.setVgap(10);

            this.setScene(new Scene(root));
            this.setTitle("GridPane");
            this.show();
        }
    }

    class TilePaneWindow extends StandardWindow {
        TilePaneWindow() {
            this(0f, 0f);
        }

        TilePaneWindow(float x, float y) {
            super(x, y);
            Button button1 = new StandardButton("Button One");
            Button button2 = new StandardButton("Button Two");
            Button button3 = new StandardButton("Button Three");
            Button button4 = new StandardButton("Button Four");
            Button button5 = new StandardButton("Button Five");
            Button button6 = new StandardButton("Button Six");
            Button button7 = new StandardButton("Button Seven");
            Button button8 = new StandardButton("Button Eight");
            Button button9 = new StandardButton("Button Nine");

            TilePane root = new TilePane();
            root.setTileAlignment(Pos.CENTER);
            root.setPrefColumns(3);
            root.setPrefRows(3);
            root.getChildren().add(button1);
            root.getChildren().add(button2);
            root.getChildren().add(button3);
            root.getChildren().add(button4);
            root.getChildren().add(button5);
            root.getChildren().add(button6);
            root.getChildren().add(button7);
            root.getChildren().add(button8);
            root.getChildren().add(button9);
            root.setAlignment(Pos.CENTER);
            root.setPadding(new Insets(10.0));

            this.setScene(new Scene(root));
            this.setTitle("TilePane");
            this.show();
        }
    }

    class BorderPaneWindow extends StandardWindow {
        BorderPaneWindow() {
            this(0f, 0f);
        }

        BorderPaneWindow(float x, float y) {
            super(x, y);
            Button button1 = new StandardButton("Button One");
            Button button2 = new StandardButton("Button Two");
            Button button3 = new StandardButton("Button Three");
            Button button4 = new StandardButton("Button Four");
            Button button5 = new StandardButton("Button Five");

//            button1.setMaxWidth(1000);

            BorderPane root = new BorderPane();
            root.setTop(button1);
            BorderPane.setAlignment(button1, Pos.CENTER);
            root.setCenter(button2);
            root.setBottom(button3);
            BorderPane.setAlignment(button3, Pos.CENTER);
            root.setLeft(button4);
            BorderPane.setAlignment(button4, Pos.CENTER);
            root.setRight(button5);
            BorderPane.setAlignment(button5, Pos.CENTER);
            root.setPadding(new Insets(10.0));

            this.setScene(new Scene(root));
            this.setTitle("BorderPane");
            this.show();
        }
    }
}

