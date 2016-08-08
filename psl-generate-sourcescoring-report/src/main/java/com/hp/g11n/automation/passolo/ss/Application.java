package com.hp.g11n.automation.passolo.ss;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;


public class Application extends javafx.application.Application {
    public Parent getMainView() throws IOException {
        return loadView("fxml/mainView.fxml");
    }
    protected Parent loadView(String url) throws IOException {
        InputStream fxmlStream = null;
        try {
            fxmlStream = getClass().getClassLoader().getResourceAsStream(url);
            FXMLLoader loader = new FXMLLoader();
            loader.load(fxmlStream);
            return loader.getRoot();// loader.getController());
        } finally {
            if (fxmlStream != null) {
                fxmlStream.close();
            }
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Source Scoring !!");
        stage.setScene(new Scene(getMainView()));
        stage.setResizable(true);
        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
