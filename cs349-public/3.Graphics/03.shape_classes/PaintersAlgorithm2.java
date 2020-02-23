/*
 * PaintersAlgorithm2.java (c) 2020 Jeff Avery
 * Demonstrate stacking components on a canvas
 * Shapes  will be painted in order
 */

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class PaintersAlgorithm2 extends Application {
    @Override
    public void start(Stage stage) {

        // create a scene graph (root group + canvas)
        Group root = new Group();
        Rectangle r1 = new Rectangle(10, 10, 380, 380); r1.setFill(Color.GRAY);
        Rectangle r2 = new Rectangle(150, 150, 125, 150); r2.setFill(Color.YELLOW);
        Circle c1 = new Circle(150, 150, 60); c1.setFill(Color.BLUE);
        Rectangle r3 = new Rectangle(200, 125, 100, 125); r3.setFill(Color.GREEN);

        // add shapes to the scene graph - this replaces the explicit list!
        // shapes will be drawn in the order in which they are added
        root.getChildren().add(r1);
        root.getChildren().add(r2);
        root.getChildren().add(c1);
        root.getChildren().add(r3);

        // add scene graph to stage
        stage.setScene(new Scene(root, 400, 400));
        stage.setTitle("Painter's Algorithm 2");
        stage.show();
    }
}