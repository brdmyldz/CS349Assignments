import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class OkDialog extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        TextArea text = new TextArea("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.");
        text.setWrapText(true);
        text.setPrefWidth(280);
        text.setPrefHeight(125);
        text.relocate(10, 10);
        text.setEditable(false);

        Button ok = new Button("Ok");
        ok.setPrefWidth(75);
        ok.relocate(130, 155);

        Button cancel = new Button("Cancel");
        cancel.setPrefWidth(75);
        cancel.relocate(215, 155);

        Scene scene = new Scene(new Pane(
                text, ok, cancel), 300, 200);
        stage.setScene(scene);
        stage.setTitle("Dialog Box");
        stage.setResizable(false);
        stage.setAlwaysOnTop(true);
        stage.show();
    }
}