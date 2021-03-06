package mychamp.GUI.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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

    /**
     * Gets the singleton instance of the team parser.
     */
    TeamParser teamParser = TeamParser.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    /**
     * Closes this stage.
     *
     * @param event
     */
    @FXML
    private void handleCloseView(ActionEvent event)
    {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    private void fillComboBoxRemoveTeam()
    {
        List<Team> teamList = new ArrayList();
        teamList = teamParser.getAllTeams();
        ObservableList<Team> comboItems
                = FXCollections.observableArrayList(teamList);
        removeTeam.setItems(comboItems);
        removeTeam.getSelectionModel().selectFirst();
    }

    @FXML
    private void removeTeamFromTournament(ActionEvent event)
    {
        if (removeTeam.getSelectionModel().getSelectedItem() == null || removeTeam.getItems() == null)
        {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Team Selected");
            alert.setContentText("Please Select a Team");

            alert.showAndWait();
        } else
        {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Delete Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to remove team?");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK)
            {
                int Id = removeTeam.getSelectionModel().getSelectedItem().getTeamID();
                teamParser.removeTeam(Id);
            }

        }
    }

}
