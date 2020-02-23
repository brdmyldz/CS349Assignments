import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class HelloFX extends Application {

//    public static void main(String[] args) {
//        System.out.println("Main");
//        launch();
//    }

//    @Override
//    public void init() throws Exception {
//        // executed after launch() is called above
//        super.init();
//        System.out.println("Init");
//    }

    @Override
    public void start(Stage stage) throws Exception {
        // executed after init() method
        Label label = new Label("Hello JavaFX");
        Scene scene = new Scene(new StackPane(label), 320, 240);

        stage.setTitle("HelloFX");
        stage.setScene(scene);
        stage.show();
        System.out.println("Start");
    }
//
//    @Override
//    public void stop() throws Exception {
//        // executed when app is shutdown
//        super.stop();
//        System.out.println("Stop");
//    }
}


