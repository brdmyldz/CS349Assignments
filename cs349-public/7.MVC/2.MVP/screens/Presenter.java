package screens;

import javafx.scene.Scene;
import javafx.stage.Stage;
import screens.scenes.Instructions;
import screens.scenes.Scene1;
import screens.scenes.Scene2;

public class Presenter implements IObserver {
    private Stage stage;
    private Model model;
    private Scene currentScene;

    public Presenter(Stage stage, Model model) {
        this.stage = stage;
        this.stage.setTitle(Settings.WINDOW_TITLE);
        this.stage.setResizable(false);
        this.model = model;
    }

    // methods to manage data for the active view
    public String getValue(String key) {
        return model.getValue(key);
    }

    public void update() {
        ((IObserver) currentScene).update();
    }

    // methods to control which view is active and visible
    public void start() {
        // Create starting scene
        if (this.currentScene == null) {
            this.currentScene = new Instructions(this);
        }
        // Attach the scene to the stage and show it
        this.stage.setScene(this.currentScene);
        this.stage.show();
    }

    public void advance() {
        // Create new scene
        if (this.currentScene instanceof Instructions) {
            this.currentScene = new Scene1(this);
        } else if (this.currentScene instanceof Scene1) {
            this.currentScene = new Scene2(this);
        }

        // Attach the scene to the stage and show it
        this.stage.setScene(this.currentScene);
        this.stage.show();
    }

    public void rewind() {
        // Create new scene
        if (this.currentScene instanceof Scene2) {
            this.currentScene = new Scene1(this);
        } else if (this.currentScene instanceof Scene1) {
            this.currentScene = new Instructions(this);
        }

        // Attach the scene to the stage and show it
        this.stage.setScene(this.currentScene);
        this.stage.show();
    }
}
