package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GuiApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("Hello, Energy World!");
        StackPane root = new StackPane(label);
        Scene scene = new Scene(root, 400, 200);

        primaryStage.setTitle("Energy Community GUI");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
