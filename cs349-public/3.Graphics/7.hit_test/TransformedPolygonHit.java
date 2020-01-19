/*
 * TransformedPolygonHit
 * Hit test on polygons, using built-in shape manipulations
 */

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

// create the window and run the demo
public class TransformedPolygonHit extends Application {
    final int screen_width = 500;
    final int screen_height = 500;
    Double [] points = new Double[]{0.0, 150.0, 100.0, 150.0, 100.0, 50.0, 50.0, 0.0, 0.0, 50.0};

    @Override
    public void start(Stage stage) {
        // top-level group to hold objects to draw
        Group root = new Group();

        // create a house and directly set scale and translate
        Polygon house = new Polygon();
        house.getPoints().addAll(points);
        house.setFill(Color.DARKGOLDENROD);
        house.scaleXProperty().set(1.5);
        house.scaleYProperty().set(1.5);
        house.translateXProperty().set(200);
        house.translateYProperty().set(150);
        root.getChildren().add(house);

        house.setOnMouseClicked(mouseEvent -> {
            if (house.getFill() == Color.BLUE) {
                house.setFill(Color.RED);
            } else {
                house.setFill(Color.BLUE);
            }
        });

        // add the scene to the stage and show it
        Scene scene = new Scene(root, screen_width, screen_height, Color.WHITE);
        stage.setScene(scene);
        stage.show();
    }
}
