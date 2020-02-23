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

public class SimpleAnimation extends Application {
    float screen_width = 250;
    float screen_height = 250;

    Circle ball = new Circle();
    float ball_x, ball_y;
    float ball_radius = 15;
    float dx = 2, dy = 3;

    Timer timer;

    @Override
    public void start(Stage stage) {
        // randomize starting position
        Random rand = new Random();
        ball_x = (rand.nextFloat() * screen_width);
        ball_y = (rand.nextFloat() * screen_height);

        // setup ball
        ball.setCenterX(ball_x);
        ball.setCenterY(ball_y);
        ball.setRadius(ball_radius);
        ball.setFill(Color.RED);

        // timer ticks every time we want to advance a frame
        // scheduled to run every 1000/FPS ms
        timer = new Timer();
        TimerTask task = new TimerTask()  {
            @Override
            public void run() {
                handle_animation();
            }
        };
        final int FPS = 30;
        timer.schedule(task, 0, (1000/FPS));

        Scene scene = new Scene(new Group(ball), screen_width, screen_height);
        stage.setTitle("Animation Demo");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        // stop the timer when the program is terminated
        timer.cancel();
    }

    void handle_animation() {
        // if we hit the edge of the window, change direction
        if (ball_x < (0.0f + ball_radius) || ball_x > (screen_width - ball_radius)) { dx *= -1.0f; }
        if (ball_y < (0.0f + ball_radius) || ball_y > (screen_height - ball_radius)) { dy *= -1.0f; }

        // move the ball
        ball_x += dx;
        ball_y += dy;

        Platform.runLater(() -> {
            ball.setCenterX(ball_x);
            ball.setCenterY(ball_y);
        });
    }
}