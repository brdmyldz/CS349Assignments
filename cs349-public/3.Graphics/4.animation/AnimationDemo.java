import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class AnimationDemo extends Application {
    float x=0, y=0;
    float dx=5, dy=5;
    float screen_width = 500, screen_height = 500;
    Circle ball = new Circle();

    @Override
    public void start(Stage stage) throws Exception {
        // setup ball
        Random rand = new Random();
        ball.setCenterX((rand.nextFloat() * screen_width));
        ball.setCenterY((rand.nextFloat() * screen_height));
        ball.setRadius(20);
        ball.setFill(Color.RED);

        // timer ticks every time we want to advance a frame
        // scheduled to run every 1000/FPS ms
        Timer timer = new Timer();
        TimerTask task = new TimerTask()  {
            @Override
            public void run() {
                handle_animation();
            }
        };
        final int FPS = 30;
        timer.schedule(task, 0, (1000/FPS));

        Scene scene = new Scene(new Group(ball), screen_width, screen_height);
        stage.setScene(scene);
        stage.show();
    }

    void handle_animation() {
        // if we hit the edge of the window, change direction
        if (x < 0.0f || x > (screen_width - 10.0f)) { dx *= -1.0f; }
        if (y < 0.0f || y > (screen_height - 10.0f)) { dy *= -1.0f; }

        // move the ball
        x += dx;
        y += dy;

        Platform.runLater(() -> {
            ball.setCenterX(x);
            ball.setCenterY(y);
        });
    }
}