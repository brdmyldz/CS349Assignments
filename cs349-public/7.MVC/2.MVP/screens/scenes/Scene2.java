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

public class Scene2 extends Scene implements IObserver {

    private Presenter presenter;
    Label osName = new Label("Name");
    Label osVendor = new Label("Vendor");
    Label osVersion = new Label("Version");

    public Scene2(Presenter presenter) {
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
        Label title = new Label("OS Information");
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
        root.getChildren().addAll(title, spacer1, osName, osVendor, osVersion, spacer2, buttonRow);
    }

    // updates are driven by the presenter, not the model
    @Override
    public void update() {
        osName.setText("OS Name: " + presenter.getValue("os.name"));
        osVendor.setText("OS Vendor: " + presenter.getValue("os.arch"));
        osVersion.setText("OS Version: " + presenter.getValue("os.version"));
    }
}
