package screens.scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import screens.IObserver;
import screens.Presenter;
import screens.Settings;

public class Instructions extends Scene implements IObserver {

    private Presenter presenter;

    public Instructions(Presenter presenter) {
        super(new VBox(), Settings.VIEW_WIDTH, Settings.VIEW_HEIGHT);
        this.presenter = presenter;
        this.createLayout();
    }

    private void createLayout() {
        // setup top-level group
        VBox root = (VBox) this.getRoot();
        root.setSpacing(10.0);
        root.setPadding(new Insets(10.0));
        root.setAlignment(Pos.CENTER);

        // setup individual elements
        ImageView img = new ImageView(Settings.TITLE_LOGO);
        Label title = new Label(Settings.TITLE);
        title.setFont(Settings.TITLE_FONT);

        Region spacer = new Region();
        spacer.setPrefHeight(25.0);

        Label subtitle = new Label(Settings.SUBTITLE);
        subtitle.setFont(Settings.NORMAL_FONT);

        // Button setup
        Button nextButton = new Button("Next");
        nextButton.setOnMouseClicked(mouseEvent -> {
            this.presenter.advance();
        });
        nextButton.requestFocus();

        // add everything to the scene
        root.getChildren().addAll(img, title, subtitle, spacer, nextButton);
    }

    // updates are driven by the presenter, not the model
    @Override
    public void update() {
        // we would fetch data if we had data on this screen
    }
}
