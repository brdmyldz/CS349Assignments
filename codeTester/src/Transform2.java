/*
 * Transform2: Built-in transformation methods
 * Use transformation methods from shape models. This improves on the previous
 * version by letting the shapes handle their own transformations. Shape classes
 * all have scaleProperty, rotateProperty and translateProperty methods
 */

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;


// create the window and run the demo
public class Transform2 extends Application {
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

        // create a second house and directly set scale and translate
        Polygon house2 = new Polygon();
        house2.getPoints().addAll(points);
        house2.setFill(Color.DARKGOLDENROD);
        house2.setScaleX(1.5);
        house2.setScaleY(1.5);
        house2.setTranslateX(screen_width/2);
        house2.setTranslateY(screen_height/2);
        root.getChildren().add(house2);

        // add the scene to the stage and show it
        Scene scene = new Scene(root, screen_width, screen_height, Color.WHITE);
        stage.setScene(scene);
        stage.show();
    }
}
