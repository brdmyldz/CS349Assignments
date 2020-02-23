import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// Demonstrates key event structure

public class KeyEvents extends Application {

    @Override
    public void start(Stage stage) {
        TextField input = new TextField();
        input.setPrefHeight(25);
        input.setPrefWidth(400);
        input.setEditable(true);
        input.requestFocus();

        TextArea values = new TextArea();
        values.setPrefHeight(575);
        values.setPrefWidth(400);
        values.setEditable(false);

        // event handlers
        input.setOnKeyPressed(keyEvent -> { printEvent(values, keyEvent); });
        input.setOnKeyReleased(keyEvent -> { printEvent(values, keyEvent);  });

        // setup scene
        Scene scene = new Scene(new VBox(input, values), 800, 600);
        stage.setScene(scene);

        // setup stage
        stage.setTitle("KeyEvents Demo");
        stage.setResizable(false);
        stage.show();
    }

    void printEvent(TextArea textWidget, KeyEvent event) {
        textWidget.appendText(
                event.getEventType()
                        + ": {target:" + event.getTarget().getClass() + "},"
                        + ": {code:" + event.getCode() + "},"
                        + ": {text:" + event.getText() + "},"
                        + ": {character:" + event.getCharacter() + "}\n"
        );
    }
}
