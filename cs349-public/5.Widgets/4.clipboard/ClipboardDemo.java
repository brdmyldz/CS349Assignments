import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ClipboardDemo extends Application {
    @Override
    public void start(Stage stage) {
        // Create menu items
        MenuBar menubar = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem quit = new MenuItem("Quit");

        Menu editMenu = new Menu("Edit");
        MenuItem cut = new MenuItem("Cut");
        MenuItem copy = new MenuItem("Copy");
        MenuItem paste = new MenuItem("Paste");

        // Put menus together
        fileMenu.getItems().add(quit);
        editMenu.getItems().addAll(cut, copy, paste);
        menubar.getMenus().addAll(fileMenu, editMenu);

        // Create other on-screen widgets
        Label instructions1 = new Label(" Menu items use handlers that we've defined in code. ");
        Label instructions2 = new Label (" Right-click over the text field to see the built-in context menu.");
        Label spacer1 = new Label();

        Label label1 = new Label(" TextArea 1");
        TextArea text1 = new TextArea();
        text1.setPrefHeight(150);
        text1.setEditable(true);
        text1.setWrapText(true);

        Label spacer2 = new Label();
        Label label2 = new Label(" TextArea 2");
        TextArea text2 = new TextArea();
        text2.setPrefHeight(150);
        text2.setEditable(true);
        text2.setWrapText(true);

        // Map accelerator keys to menu items
        quit.setAccelerator(new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN));
        cut.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN));
        copy.setAccelerator(new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN));
        paste.setAccelerator(new KeyCodeCombination(KeyCode.V, KeyCombination.CONTROL_DOWN));

        // Create a root node and add to the scene
        // This will stack widgets vertically
        VBox root = new VBox(menubar, instructions1, instructions2, spacer1, label1, text1, spacer2, label2, text2);
        root.setSpacing(5);

        Scene scene = new Scene(root , 400, 500);

        // Setup handlers
        // We needed the scene to be created previously since we refer to it here
        fileMenu.setOnAction(actionEvent -> {
            System.exit(0);
        });

        cut.setOnAction(actionEvent -> {
            // pass in the selected text area widget
            // focus tells us which one to use as the source
            TextArea source = (TextArea) scene.focusOwnerProperty().get();
            putOnClipboard(source);
            source.clear();
        });

        copy.setOnAction(actionEvent -> {
            // pass in the selected text area widget
            // focus tells us which one to use as the source
            TextArea source = (TextArea) scene.focusOwnerProperty().get();
            putOnClipboard(source);
        });

        paste.setOnAction(actionEvent -> {
            // paste into the selected widget
            TextArea destination = (TextArea) scene.focusOwnerProperty().get();
            getFromClipboard(destination);
        });

        // Attach the scene to the stage and show it
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Clipboard Demo");
        stage.show();
    }

    // take text contents of a TextArea widget and place on clipboard
    void putOnClipboard(TextArea text) {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(text.getText());
        clipboard.setContent(content);
        System.out.println("Copy to clipboard: " + clipboard.getString());
    }

    // return the string contents of the clipboard
    void getFromClipboard(TextArea text) {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        text.appendText(clipboard.getString());
        System.out.println("Paste from clipboard: " + clipboard.getString());
    }
}