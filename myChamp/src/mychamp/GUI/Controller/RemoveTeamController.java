package mychamp.GUI.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import mychamp.BE.Team;
import mychamp.GUI.Model.TeamParser;

public class RemoveTeamController implements Initializable
{
    @FXML
    private ComboBox<Team> removeTeam;
    @FXML
    private Button btnRemove;
    @FXML
    private Button btnClose;
    
    TeamParser teamParser = new TeamParser();

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    
    
    @FXML
    private void btnCloseRemoveView(ActionEvent event)
    {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }
    
    private void fillComboBoxRemoveTeam()
    {
        List<Team> teamList = new ArrayList();
        teamList = teamParser.getAllTeams();
        ObservableList<Team> comboItems = 
                FXCollections.observableArrayList(teamList);
        removeTeam.setItems(comboItems);
        removeTeam.getSelectionModel().selectFirst();
    }
}
