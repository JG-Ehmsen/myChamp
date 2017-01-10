package mychamp.GUI.Controller;

import java.io.IOException;
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
import javafx.stage.Window;
import mychamp.BE.Team;
import mychamp.GUI.Model.Model;
import mychamp.GUI.Model.TeamParser;

public class ManagerViewController implements Initializable
{

    private Model model = Model.getInstance();

    private Window primaryStage;

    @FXML
    private ComboBox<String> cbRound;
    @FXML
    private ComboBox<String> cbGroup;
    @FXML
    private ComboBox<String> cbTeam;

    TeamParser teamParser = TeamParser.getInstance();
    private Button btnBackMatchlist;
    @FXML
    private Button btnRemoveWndw;
    @FXML
    private Button btnHandleResult;
    private Object team;
    @FXML
    private Button btnBack;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        fillComboBoxRound();
        fillComboBoxGroup();
        fillComboBoxTeam();
    }

    /**
     * Populates the combo box with the rounds.
     */
    private void fillComboBoxRound()
    {
        ObservableList<String> comboItems
                = FXCollections.observableArrayList(null, "1", "2", "3", "4", "5", "6", "Quarter-finals", "Semi-finals", "Final");
        cbRound.setItems(comboItems);
        cbRound.getSelectionModel().selectFirst();
    }

    /**
     * Populates the combo box with the groups.
     */
    private void fillComboBoxGroup()
    {
        ObservableList<String> comboItems
                = FXCollections.observableArrayList(null, "A", "B", "C", "D");
        cbGroup.setItems(comboItems);
        cbGroup.getSelectionModel().selectFirst();
    }

    /**
     * Populates the combo box with the teams.
     */
    private void fillComboBoxTeam()
    {
        List<Team> teamList = teamParser.getAllTeams();
        List<String> teamNameList = new ArrayList();
        for (Team team : teamList)
        {
            teamNameList.add(team.getTeamName());
        }
        ObservableList<String> comboItems = FXCollections.observableArrayList(teamNameList);
        cbTeam.setItems(comboItems);
        cbTeam.getSelectionModel().selectFirst();
    }

    /**
     * Runs the changeView method.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void handleRemoveWndw(ActionEvent event) throws IOException
    {
        model.changeView("Remove team ", "GUI/View/RemoveTeam.fxml", "RemoveTeam", null, null);
    }

    /**
     * Runs the changeView method.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void handleGoToResultManager(ActionEvent event) throws IOException
    {
        model.changeView("Update Result ", "GUI/View/ResultManager.fxml", "ResultManager", null, null);
    }

    /**
     * Runs the changeView method and closes this stage.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void handleBackButton(ActionEvent event) throws IOException
    {
        model.changeView("Upcoming Matches & Results ", "GUI/View/MatchListSchedule.fxml", "MatchListSchedule", null, null);

        // Closes the primary stage
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close();
    }
}
