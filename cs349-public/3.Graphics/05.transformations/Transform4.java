/*
 * Transform4
 * Draw using built-in shape classes.
 */

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Transform4 extends Application {
    final int screen_width = 500;
    final int screen_height = 500;

    @Override
    public void start(Stage stage) {
        // Shapes to draw
        Rectangle shape1 = new Rectangle(100, 100, Color.BLUE);
        shape1.setTranslateX(0);  // works like setTranslateProperty()
        shape1.setTranslateY(0);
        shape1.setRotate(15);

        Rectangle shape2 = new Rectangle(100, 100, Color.YELLOW);
        shape2.setTranslateX(50);
        shape2.setTranslateY(50);
        shape2.setRotate(15);

        Rectangle shape3 = new Rectangle(100, 100, Color.BLUEVIOLET);
        shape3.setTranslateX(100);
        shape3.setTranslateY(100);
        shape3.setRotate(15);

        Rectangle shape4 = new Rectangle(100, 100, Color.CHOCOLATE);
        shape4.setTranslateX(150);
        shape4.setTranslateY(150);
        shape4.setRotate(15);

        // OPTION A: Just add them without transformations

//        Group root = new Group();
//        root.getChildren().add(shape1);
//        root.getChildren().add(shape2);
//        root.getChildren().add(shape3);
//        root.getChildren().add(shape4);
//        Scene scene = new Scene(root, screen_width, screen_height);

        // OPTION B: Transform each shape and NEST a second group
        // Notice the the second group inherits transforms from the parent.

        // Create a top-level group
        // Transformations in a group apply to all of its children
        Group root1 = new Group();
        root1.setRotate(15);
        root1.getChildren().add(shape1);
        root1.getChildren().add(shape2);

        // Create a second level group
        // It inherits its parents transformations and adds its own
        Group root2 = new Group();
        root2.setScaleX(0.5);
        root2.setScaleY(0.5);
        root2.setRotate(15);
        root2.setTranslateX(50);
        root2.setTranslateY(50);
        root2.getChildren().add(shape3);
        root2.getChildren().add(shape4);

        // Create a scene graph holding the group
        root1.getChildren().add(root2);
        Scene scene = new Scene(root1, screen_width, screen_height);

        // Add the scene to the stage and display it
        stage.setScene(scene);
        stage.show();
    }
}