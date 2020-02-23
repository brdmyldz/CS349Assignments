package screens;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Settings {
    // Constants
    public static final int VIEW_WIDTH = 500;
    public static final int VIEW_HEIGHT = 400;
    public static final String WINDOW_TITLE = "Screens Demo";

    // Fonts
    public static final Font TITLE_FONT = Font.font("Arial", FontWeight.BOLD, 30);
    public static final Font NORMAL_FONT = Font.font("Arial", 24);


    // Images
    public static final String TITLE = "Model-View-Presenter Demo";
    public static final String SUBTITLE = "CS 349 User Interfaces";
    public static final Image TITLE_LOGO = new Image("screens/resources/logo.png");

    // Sounds
    private static final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    public static final Media BUTTON_CLICK_SOUND = new Media( classLoader.getResource("screens/resources/adriantnt_u_click.mp3").toString());
}
