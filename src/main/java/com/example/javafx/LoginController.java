package com.example.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.Main;
import org.example.domain.User;
import org.example.repo.ParticipantRepoDB;
import org.example.srv.ServiceParticipant;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class LoginController {

    public LoginController(){

    }
    private Properties properties;
    private Stage primaryStage;
    @FXML
    private Button login;

    public String getUsername() {
        return username.getText();
    }

    public String getPassword() {
        return password.getText();
    }

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Label gresit;

    public void userLogin(ActionEvent event) throws IOException{

        // properties.load(new FileReader("bd.config"));
        //ParticipantRepoDB participantDBRepository = new ParticipantRepoDB(properties);
        //ServiceParticipant srvParticipant = new ServiceParticipant(participantDBRepository);

        //if(srvParticipant == null)
            //System.out.println("null srv");

        if(username.getText().toString().isEmpty()){
            gresit.setText("Wrong username!");

        }

        else if(password.getText().toString().isEmpty()){
            gresit.setText("Wrong password!");
        }

        else {
            gresit.setText("Good!");

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main2.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            MainController controller = fxmlLoader.getController();
            controller.init(properties, primaryStage);
            primaryStage.setTitle("Moto!");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }

    public void init(Properties properties, Stage primaryStage) {
        this.properties = properties;
        this.primaryStage = primaryStage;
    }
}
