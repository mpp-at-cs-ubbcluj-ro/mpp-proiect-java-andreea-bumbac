package com.example.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class HelloApplication extends Application {

    public static Stage getStg() {
        return stg;
    }

    public static Stage stg;

    @Override
    public void start(Stage stage) throws IOException {


        final Properties properties = new Properties();
        try {
            properties.load(new FileReader("bd.properties"));

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login2.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            LoginController controller = fxmlLoader.getController();
            controller.init(properties, stage);
            stage.setTitle("Moto!");
            stage.setScene(scene);
            stage.show();

        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }


        stg = stage;
        stg.setResizable(false);


    }


    public void changeScene(String fxml) throws IOException{
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }


    public static void main(String[] args) {
        launch();
    }
}