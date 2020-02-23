/*
* Transform1: Updating points
* Shows how to "manually" transform a shape model by writing custom
* functions to update the shape model's points. Later examples demonstrate
* more scalable and less error prone methods of accomplising the same thing.
*/

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;


// create the window and run the demo
public class Transform1 extends Application {
    final int screen_width = 500;
    final int screen_height = 500;
    Double [] points = new Double[]{0.0, 150.0, 100.0, 150.0, 100.0, 50.0, 50.0, 0.0, 0.0, 50.0};

    @Override
    public void start(Stage stage) {
        // top-level group to hold objects to draw
        Group root = new Group();

        // create a house shape model at the origin
        Polygon house = new Polygon();
        house.getPoints().addAll(points);
        house.setFill(Color.BLUEVIOLET);
        root.getChildren().add(house);

        // create a second house that we'll scale and translate
        Polygon house2 = new Polygon();
        house2.getPoints().addAll(points);
        house2.setFill(Color.DARKGOLDENROD);
        root.getChildren().add(house2);

        scale(house2, 1.5, 1.5);
        translate(house2, screen_width / 2, screen_height / 2); // past the centre :/

        // add the scene to the stage and show it
        Scene scene = new Scene(root, screen_width, screen_height, Color.WHITE);
        stage.setScene(scene);
        stage.show();
    }

    // non-matrix translate
    void translate(Polygon polygon, double tx, double ty) {
        for (int i = 0; i < polygon.getPoints().size(); ++i) {
            double oldVal = polygon.getPoints().get(i);
            double delta = (i % 2 == 0) ? tx : ty;
            polygon.getPoints().set(i, (oldVal + delta));
        }
    }

    // non-matrix scale
    void scale(Polygon polygon, double sx, double sy) {
        for (int i = 0; i < polygon.getPoints().size(); ++i) {
            double oldVal = polygon.getPoints().get(i);
            double scale = (i % 2 == 0) ? sx : sy;
            polygon.getPoints().set(i, (oldVal * scale));
        }
    }
}
