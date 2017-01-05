/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychamp.GUI.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import mychamp.BE.Team;
import mychamp.GUI.Model.TeamParser;

/**
 * FXML Controller class
 *
 * @author Fjord82
 */
public class TeamsAddViewController implements Initializable
{
    private TeamParser teamParser = TeamParser.getInstance();
    
    @FXML
    private TableColumn<Team, String> clnJoiningTeams;
    @FXML
    private TextField txtFldTeamName;
    @FXML
    private Button btnAddTeam;
    @FXML
    private Button btnRemoveTeam;
    @FXML
    private Button btnReadyOrNot;
    @FXML
    private Label lblCountDown;
    @FXML
    private Label lblTournamentName;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnEditNoOfTeams;
    @FXML
    private TableView<Team> tblSignedTeams;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        populateList();
    }    

    
    @FXML
    private void handleAddTeam(ActionEvent event)
    {
        teamParser.addTeam(txtFldTeamName.getText());
        txtFldTeamName.clear();
        populateList();
    }

    @FXML
    private void handleRemoveSignedTeam(ActionEvent event)
    {
    }

    @FXML
    private void handleBackToPrevious(ActionEvent event)
    {
    }

    @FXML
    private void handleEditAmountTeams(ActionEvent event)
    {
    }
    
    private void populateList()
    {
        clnJoiningTeams.setCellValueFactory(new PropertyValueFactory("teamName"));
        tblSignedTeams.setItems(teamParser.loadTeamsIntoViewer());
        
    }
    
    
    
    
}
