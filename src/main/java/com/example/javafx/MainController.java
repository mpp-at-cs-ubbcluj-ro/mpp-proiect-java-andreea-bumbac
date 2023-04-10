package com.example.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.domain.CursaDTO;
import org.example.domain.Echipa;
import org.example.domain.Participant;
import org.example.domain.User;
import org.example.repo.*;
import org.example.srv.ServiceCursa;
import org.example.srv.ServiceEchipa;
import org.example.srv.ServiceParticipant;

import java.io.IOException;

public class MainController {

    private ServiceParticipant participantService;
    private ServiceCursa cursaService;
    private ServiceEchipa echipaService;
    private Properties properties;
    private Stage primaryStage;
    private Stage currentStage;
    private User currentUser;

    public MainController(){

    }

    @FXML
    private TextField search;

    @FXML
    private TextField name;

    @FXML
    private TextField lname;

    @FXML
    private TextField team;

    @FXML
    private TextField motor;

    @FXML
    private Button srcButton;

    @FXML
    private Button add;

    @FXML
    private TableView<Participant> srcTable;

    @FXML
    private TableView<CursaDTO> motoTable;
    @FXML
    private TableColumn<CursaDTO, Integer> motorColumn;
    @FXML
    private TableColumn<CursaDTO, Integer> motoColumn;
    @FXML
    private TableColumn<CursaDTO, String> nameColumn;
    @FXML
    private TableColumn<CursaDTO, String> prenumeColumn;

    @FXML
    private TableColumn<CursaDTO, String> numeColumn;
    @FXML
    private TableColumn<CursaDTO, Integer> participantColumn;

    @FXML
    private Button refresh;

    @FXML
    private Button logout;

    @FXML
    private Label mesaj;

    public void init(Properties properties, Stage primaryStage) {

        this.properties = properties;
        this.primaryStage = primaryStage;
        IParticipantRepo participantRepo = new ParticipantRepoDB(properties);
        ICursaRepo cursaRepo = new CursaRepoDB(properties);
        participantService = new ServiceParticipant(participantRepo);
        echipaService = new ServiceEchipa(new EchipaRepoDB(properties));
        cursaService = new ServiceCursa(cursaRepo, new InscrisRepoDB(properties, cursaRepo, participantRepo));

        motoTable.getItems().clear();
        motorColumn.setCellValueFactory(new PropertyValueFactory<>("engineCapacity"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        participantColumn.setCellValueFactory(new PropertyValueFactory<>("participants"));
        Collection<CursaDTO> curse = cursaService.getRacesWithParticipants();
        motoTable.getItems().addAll(curse);
    }

    private void clearFields() {
        name.clear();
        lname.clear();
        motor.clear();
        team.clear();
    }

    public void logoutfnc(ActionEvent event) throws IOException{
        HelloApplication m = new HelloApplication();
        m.changeScene("login2.fxml");
    }

    public void searching(ActionEvent event) throws IOException{
        String teamName = search.getText();

        Long id = echipaService.getTeamByName(teamName);

        Collection<Participant> parts = participantService.getParticipantsByTeam(id);

        srcTable.getItems().clear();

        motoColumn.setCellValueFactory(new PropertyValueFactory<>("capacitateMotor"));
        numeColumn.setCellValueFactory(new PropertyValueFactory<>("nume"));
        prenumeColumn.setCellValueFactory(new PropertyValueFactory<>("prenume"));
        srcTable.getItems().addAll(parts);

        search.clear();
    }

    public void adaugare(ActionEvent event) throws  IOException{

        if (name.getText().isEmpty()) {
            mesaj.setText("Error : wrong first name!");
            clearFields();
        }
        else if (lname.getText().isEmpty()) {
            mesaj.setText("Error : wrong last name!");
            clearFields();
        }
        else if (team.getText().isEmpty()) {
            mesaj.setText("Error : wrong team!");
            clearFields();
        }
        else if (motor.getText().isEmpty()) {
            mesaj.setText("Error : wrong engine capacity!");
            clearFields();
        }else {

            String firstName = name.getText();
            String lastName = lname.getText();
            Integer engineCapacity = Integer.parseInt(motor.getText().replace(",", ""));
            Long teamt = Long.parseLong(team.getText().replace(",", ""));

            Participant newParticipant = new Participant(firstName, lastName, engineCapacity, teamt);
            mesaj.setText("Good!");
            clearFields();
            participantService.save(newParticipant);

        }

    }
}
