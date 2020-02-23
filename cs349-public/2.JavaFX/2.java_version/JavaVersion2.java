import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// Display an image and some text in the scene

public class JavaVersion2 extends Application {

    // We won't include a main method anymore, since JavaFX run from start() below.
    // public static void main(String[] args) { launch(); }

    @Override
    public void start(Stage stage) {
        Label label = new Label(System.getProperty("java.vendor") + "Java " + System.getProperty("java.version") 
            + "\n & Gluon JavaFX " + System.getProperty("javafx.version"));
        Image image = new Image("java.png", 150, 150, true, true);
        ImageView imageView = new ImageView(image);
        VBox box = new VBox(imageView, label);
        box.setMargin(label, new Insets(10.0));

        Scene scene = new Scene(box, 150, 200);
        stage.setScene(scene);
        stage.show();
    }
}