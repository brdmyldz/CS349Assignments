package screens;

import javafx.application.Application;
import javafx.stage.Stage;

public class Screens extends Application {
    @Override
    public void start(Stage stage) {
        Model model = new Model();
        Presenter presenter = new Presenter(stage, model);
        presenter.start();
    }
}