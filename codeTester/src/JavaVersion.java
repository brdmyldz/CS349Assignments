import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// Display an image and some text in the scene

public class JavaVersion extends Application {

    // We don't need a main method, since JavaFX runs from start() below.

    @Override
    public void start(Stage stage) {
        Image image = new Image("java.png", 175, 175, true, true);
        ImageView imageView = new ImageView(image);
        Label label = new Label(System.getProperty("java.vendor")
                + "Java " + System.getProperty("java.version") + "\n"
                + "Gluon JavaFX " + System.getProperty("javafx.version"));

        VBox box = new VBox(imageView, label);
        VBox.setMargin(label, new Insets(10.0));

        Scene scene = new Scene(box, 175, 225);
        stage.setScene(scene);
        stage.show();
    }
}