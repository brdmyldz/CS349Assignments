package hellomvc1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

// HelloMVC1
// A simple MVC example inspired by Joseph Mack, http://www.austintek.com/mvc/
// This version doesn't use MVC at all! We'll use this to identify issues
// that we will address in later samples.

public class HelloMVC1 extends Application {

    double WINDOW_WIDTH = 200;
    double WINDOW_HEIGHT = 200;

    Button button = new Button("?");
    TextArea text = new TextArea("");
    int counter = 0;

    @Override
    public void start(Stage stage) throws Exception {
        GridPane grid = new GridPane();

        // Top group
        Pane pane1 = new Pane();
        pane1.setMinSize(WINDOW_WIDTH, WINDOW_HEIGHT/2);
        pane1.getChildren().add(button);
        grid.add(pane1, 0, 0);      // col 0, row 0

        button.setMinSize(75, 25);
        button.setMaxSize(100, 50);
        button.setOnMouseClicked(event -> { increaseCounter(); } );

        // Bottom group
        Pane pane2 = new Pane();
        pane2.setMinSize(WINDOW_WIDTH, WINDOW_HEIGHT/2);
        pane2.getChildren().add(text);
        grid.add(pane2, 0, 1);      // col 0, row 1

        text.setWrapText(true);
        text.setOnMouseClicked(event -> { increaseCounter(); } );

        // Add grid to a scene (and the scene to the stage)
        Scene scene = new Scene(grid, WINDOW_WIDTH, WINDOW_HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    void increaseCounter() {
        counter++;
        // Button displays counter value
        button.setText(Integer.toString(counter));

        // Text field shows one "X" per counter
        text.appendText("X");
    }
}