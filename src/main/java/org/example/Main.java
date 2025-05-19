package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage stage) {
        System.out.println("Applicazione JavaFX avviata");

        stage.setTitle("Libreria View");
        stage.setResizable(true);
        stage.setScene(new Scene(new Pane(), 800, 600));
        stage.show();
    }
}
