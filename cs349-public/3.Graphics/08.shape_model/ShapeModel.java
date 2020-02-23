/*
 * SceneGraph
 * Demonstrate simple scene graph that draws a house.
 * Use the graphics context method to show cumulative effects
 */

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.stage.Stage;

public class ShapeModel extends Application {
    double scaleBy = 2;
    double rotateBy = 0;

    class HouseModel {
        double[] xpoints = new double[] { -50.0, 50.0, 50.0, 0.0, -50.0 };
        double[] ypoints = new double[] { 75.0, 75.0, -25.0, -75.0, -25.0 };
        int npoints = 5;
    }

    @Override
    public void start(Stage stage) {
        HouseModel house = new HouseModel();
        Group root = new Group();
        Scene scene = new Scene(root, 500, 500, Color.WHITE);
        Canvas canvas = new Canvas(500, 500);

        // get graphics context and use for all of our transformations
        GraphicsContext g = canvas.getGraphicsContext2D();

        // draw the house in centre of screen
        g.translate(scene.getWidth() / 2, scene.getHeight() / 2);
        g.rotate(rotateBy);
        g.scale(scaleBy, scaleBy);
        g.setFill(Color.BLUE);
        g.strokePolygon(house.xpoints, house.ypoints, house.npoints);

        // save transform for later
        Affine save = g.getTransform();

        // remember: these are all in "house coordinates"
        g.translate(-25, 0); // window centred 25 px
        g.scale(0.4, 0.4); // window is 40% house width
        drawWindow(g);

        // translate to right 50 px
        g.translate(50 / 0.4, 0);
        drawWindow(g);

        // draw third window twice to demo different coordinate frames

        // set transform to saved matrix to return to "House" coordinates
        // since this window is drawn in house coordinates, it will be
        // transformed with the house
        g.setTransform(save);
        drawWindow(g, 0, -50, 45, 0.25);

        // set transform to identity to reset
        // this means third window is drawn in World Coordinates
        // and won't be transformed with the house
        g.getTransform().setToIdentity();

        // using function which has a model-to-world transform built in
        drawWindow(g, scene.getWidth() / 2, 89, 45, 0.5);

        // Add the scene to the stage and show it
        root.getChildren().add(canvas);
        stage.setScene(scene);
        stage.show();
    }

    // 100 x 100 house shape using Polygon class
    // (model position is centred at top left corner)

    // draws 100 x 100 window shape centred at 0,0
    void drawWindow(GraphicsContext g) {
        g.setFill(Color.BLACK);
        g.fillRect(-50, -50, 100, 100);
        g.setFill(Color.WHITE);
        g.fillRect(-40, -40, 35, 35);
        g.fillRect(5, -40, 35, 35);
        g.fillRect(-40, 5, 35, 35);
        g.fillRect(5, 5, 35, 35);
    }

    // draws 100 x 100 window shape centred at 0,0
    void drawWindow(GraphicsContext g, double x, double y, double theta, double s) {

        // save the current g2 transform matrix
        Affine save = g.getTransform();

        // do the model to world transformation
        g.translate(x, y);  // T
        g.rotate(theta); // R
        g.scale(s, s);  // S

        // draws 100 x 100 window centred at 0,0
        drawWindow(g);
        // reset the transform to what it was before we drew the shape
        g.setTransform(save);
    }
}
