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
        shape1.setTranslateX(0);
        shape1.setTranslateY(0);

        Rectangle shape2 = new Rectangle(100, 100, Color.YELLOW);
        shape2.setTranslateX(50);
        shape2.setTranslateY(50);

        Rectangle shape3 = new Rectangle(100, 100, Color.BLUEVIOLET);
        shape3.setTranslateX(100);
        shape3.setTranslateY(100);

        Rectangle shape4 = new Rectangle(100, 100, Color.CHOCOLATE);
        shape4.setTranslateX(150);
        shape4.setTranslateY(150);

        // Create a top-level group
        // Transformations here apply to everything below
        Group root = new Group();
        root.setTranslateX(100);
        root.setTranslateY(100);
        root.getChildren().add(shape1);
        root.getChildren().add(shape2);

        // Create a second level group
        Group root2 = new Group();
        root2.setScaleX(0.5);
        root2.setScaleY(0.5);
        root2.setRotate(45);
        root2.getChildren().add(shape3);
        root2.getChildren().add(shape4);
        root.getChildren().add(root2);

        // Create a scene graph holding the group
        Scene scene = new Scene(root, screen_width, screen_height);

        // Add the scene to the stage and display it
        stage.setScene(scene);
        stage.show();
    }
}