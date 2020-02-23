import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.List;

// Demonstrates mouse events

public class MouseEvents extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        // components
        Label spacer1 = new Label();
        spacer1.setPrefHeight(100);

        Rectangle shape = new Rectangle();
        shape.setFill(Color.BLUE);
        shape.setHeight(300);
        shape.setWidth(400);

        Label spacer2 = new Label();
        spacer2.setPrefHeight(100);

        TextArea text = new TextArea();
        text.setPrefHeight(400);

        // capture and print events
        shape.setOnMouseClicked(mouseEvent -> { printEvent(text, mouseEvent); });
        shape.setOnMouseEntered(mouseEvent -> { printEvent(text, mouseEvent); });
        shape.setOnMouseExited(mouseEvent -> { printEvent(text, mouseEvent); });
        shape.setOnMouseDragOver(mouseEvent -> { printEvent(text, mouseEvent); });
        shape.setOnMouseDragReleased(mouseEvent -> { printEvent(text, mouseEvent); });
        shape.setOnMouseMoved(mouseEvent -> { printEvent(text, mouseEvent); });

        // setup scene
        Scene scene = new Scene(new VBox(spacer1, new StackPane(shape), spacer2, text), 700, 800);
        stage.setScene(scene);

        // setup stage
        stage.setTitle("MouseEvents Demo");
        stage.setResizable(false);
        stage.show();

    }

    void printEvent(TextArea textWidget, MouseEvent event) {
        textWidget.appendText(
                event.getEventType()
                + ": {x:" + event.getX() + "},"
                + ": {y:" + event.getY() + "},"
                + ": {sceneX:" + event.getSceneX() + "},"
                + ": {sceneY:" + event.getSceneY() + "},"
                + ": {button:" + event.getButton() + "},"
                + ": {click:" + event.getClickCount() + "}\n"
        );
    }
}
