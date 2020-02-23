import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

// Draw using JavaFX shape classes
// These are built-in subclasses of javafx.scene.shape

public class DrawShapes extends Application {

    @Override
    public void start(Stage stage) {
        // Create a lots of shapes to display
        Rectangle rectangle = new Rectangle(100, 100);
        rectangle.setFill(Color.RED);
        rectangle.setX(50);
        rectangle.setY(50);
        rectangle.setOnMouseClicked(mouseEvent -> System.out.println("Rectangle clicked"));

        Polygon polygon = new Polygon(new double[] {250.0, 175.0, 350.0, 175.0, 350.0, 75.0, 300.0, 25.0, 250.0, 75.0} );
        polygon.setStroke(Color.LIGHTYELLOW);
        polygon.setFill(Color.BLUE);
        polygon.setOnMouseClicked(mouseEvent -> System.out.println("Polygon clicked"));

        Circle circle = new Circle(50);
        circle.setFill(Color.YELLOW);
        circle.setCenterX(175);
        circle.setCenterY(325);
        circle.setOnMouseClicked(mouseEvent -> System.out.println("Circle clicked"));

        Text text = new Text(50, 225, "The javafx.scene.shape package contains many shapes");
        text.setTextAlignment(TextAlignment.LEFT);
        text.setOnMouseClicked(mouseEvent -> System.out.println("Text clicked"));
        text.setOnMouseClicked(mouseEvent -> System.out.println("Text clicked"));

        Line line = new Line(325, 275, 275, 450);
        line.setFill(Color.GRAY);
        line.setStrokeWidth(10);
        line.setOnMouseClicked(mouseEvent -> System.out.println("Line clicked"));

        Ellipse ellipse = new Ellipse();
        ellipse.setFill(Color.AQUA);
        ellipse.setCenterX(125.0f);
        ellipse.setCenterY(425.0f);
        ellipse.setRadiusX(50.0f);
        ellipse.setRadiusY(25.0f);
        ellipse.setOnMouseClicked(mouseEvent -> System.out.println("Ellipse clicked"));

        // Create a group to hold everything
        Group group = new Group();
        group.getChildren().add(rectangle);
        group.getChildren().add(circle);
        group.getChildren().add(line);
        group.getChildren().add(text);
        group.getChildren().add(polygon);
        group.getChildren().add(ellipse);

        // Create a scene graph holding the group
        Scene scene = new Scene(group, 400, 500);

        // Add the scene to the stage and display it
        stage.setTitle("Drawing Shapes");
        stage.setScene(scene);
        stage.show();
    }
}