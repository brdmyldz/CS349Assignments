package screens.scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import screens.IObserver;
import screens.Presenter;
import screens.Settings;

public class Scene1 extends Scene implements IObserver {

    private Presenter presenter;
    Label javaVendor = new Label("Java Vendor: ");
    Label javaVersion = new Label ("Java Version");

    public Scene1(Presenter presenter) {
        super(new VBox(), Settings.VIEW_WIDTH, Settings.VIEW_HEIGHT);
        this.presenter = presenter;
        this.createLayout();
        update();
    }

    private void createLayout() {
        // setup top-level group
        VBox root = (VBox) this.getRoot();
        root.setPadding(new Insets(10.0));
        root.setAlignment(Pos.CENTER);

        // setup individual elements
        Label title = new Label("Java Information");
        title.setFont(Settings.TITLE_FONT);

        Region spacer1 = new Region();
        spacer1.setPrefHeight(25.0);

        Region spacer2 = new Region();
        spacer2.setPrefHeight(25.0);

        // forward and back buttons
        Button nextButton = new Button("Next");
        nextButton.setOnMouseClicked(mouseEvent -> {
            this.presenter.advance();
        });
        nextButton.requestFocus();

        Button prevButton = new Button("Previous");
        prevButton.setOnMouseClicked(mouseEvent -> {
            this.presenter.rewind();
        });

        HBox buttonRow = new HBox(prevButton, nextButton);
        buttonRow.setAlignment(Pos.CENTER);
        buttonRow.setSpacing(10.0f);

        // add everything to the scene
        // javaVendor and javaVersion will be empty until update() is called
        root.getChildren().addAll(title, spacer1, javaVendor, javaVersion, spacer2, buttonRow);
    }

    // updates are driven by the presenter, not the model
    @Override
    public void update() {
        javaVendor.setText("Java Vendor: " + presenter.getValue("java.vendor"));
        javaVersion.setText("Java Version: " + presenter.getValue("java.version"));
    }
}
