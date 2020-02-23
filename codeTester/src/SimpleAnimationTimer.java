import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.Random;

/*
 * This example is similar to SimpleAnimation,
 * but uses a JavaFX AnimationTimer instead of a Java Timer.
 */
public class SimpleAnimationTimer extends Application {
    float screen_width = 250;
    float screen_height = 250;

    Circle ball = new Circle();
    float ball_x, ball_y;
    float ball_radius = 15;
    float dx = 1.0f, dy = 1.5f;

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
        // An AnimationTimer runs at 60 FPS
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                handle_animation();
            }
        };
        timer.start(); // this timer will be stopped automatically by JavaFX when the program terminates

        Scene scene = new Scene(new Group(ball), screen_width, screen_height);
        stage.setTitle("Animation Demo");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    void handle_animation() {
        // if we hit the edge of the window, change direction
        if (ball_x < (0.0f + ball_radius) || ball_x > (screen_width - ball_radius)) { dx *= -1.0f; }
        if (ball_y < (0.0f + ball_radius) || ball_y > (screen_height - ball_radius)) { dy *= -1.0f; }

        // move the ball
        ball_x += dx;
        ball_y += dy;
        ball.setCenterX(ball_x);
        ball.setCenterY(ball_y);
    }
}