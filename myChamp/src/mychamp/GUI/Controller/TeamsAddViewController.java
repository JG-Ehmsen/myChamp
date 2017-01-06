/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychamp.GUI.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
        
        if(tblSignedTeams.getSelectionModel().getSelectedItem() == null || tblSignedTeams.getItems() == null)
        {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Team Selected");
            alert.setContentText("Please select a Team");
            
            alert.showAndWait();
        }else 
        {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Delete Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to remove team?");
            
        Optional<ButtonType> result = alert.showAndWait();
        
        if(result.get() == ButtonType.OK)
        {
        int Id = tblSignedTeams.getSelectionModel().getSelectedItem().getTeamID();
        teamParser.removeTeam(Id);
        populateList();
        }
        }
        
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
