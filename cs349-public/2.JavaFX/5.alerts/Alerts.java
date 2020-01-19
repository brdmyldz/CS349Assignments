import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Alerts extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Alerts");

        Button button = new Button("Click me");
        button.setOnAction(event -> {
                    boolean answer = ConfirmBox.Display("Confirm", "Please respond.");
                    if (answer == true) {
                        System.out.println("Yes was chosen");
                    } else {
                        System.out.println("No was chosen");
                    }
                });

        StackPane layout = new StackPane();
        layout.getChildren().add(button);

        Scene scene = new Scene(layout, 200, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
