package mychamp.GUI.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import mychamp.BE.Team;
import mychamp.GUI.Model.Model;
import mychamp.GUI.Model.TeamParser;

public class TeamsAddViewController implements Initializable
{

    private Model model = Model.getInstance();

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

    private int maxNumOfTeams = 0;
    private int currentNumOfTeams = 0;
    private String noOfTeams = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        populateList();

        //Puts the focus on the textfield.
        Platform.runLater(new Runnable()
        {
            @Override
            public void run()
            {
                txtFldTeamName.requestFocus();
            }
        });
    }

    private void populateList()
    {
        txtFldTeamName.focusTraversableProperty();

        clnJoiningTeams.setCellValueFactory(new PropertyValueFactory("teamName"));
        tblSignedTeams.setItems(teamParser.loadTeamsIntoViewer());
    }

    public void setInformation(String tournamentTitle, String noOfTeams)
    {
        this.lblTournamentName.setText(tournamentTitle);
        this.noOfTeams = noOfTeams;
        updateCounter();

    }

    @FXML
    private void handleAddTeam(ActionEvent event)
    {
        sendTeamNames();
    }

    @FXML
    private void handleEnterPressed(KeyEvent event)
    {
        if (event.getCode() == KeyCode.ENTER)
        {
            sendTeamNames();
        }
    }

    private void sendTeamNames()
    {
        boolean canAddTeam = true;
        for (Team team : tblSignedTeams.getItems())
        {
            if (team.getTeamName().equalsIgnoreCase(txtFldTeamName.getText()))
            {
                canAddTeam = false;
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Error");
                alert.setContentText("Name already taken. Please write a different name.");
                alert.show();
            }

        }

        if (txtFldTeamName.getText().isEmpty())
        {
            canAddTeam = false;
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Error");
            alert.setContentText("No name added. Please write a different name.");
            alert.show();
        } else if (canAddTeam == true)
        {
            teamParser.addTeam(txtFldTeamName.getText());
            txtFldTeamName.clear();
            populateList();
        }
        updateCounter();
    }

    @FXML
    private void handleRemoveSignedTeam(ActionEvent event)
    {
        removeSignedTeam();

    }

    @FXML
    private void handleDeletePressed(KeyEvent event)
    {
        if (event.getCode() == KeyCode.BACK_SPACE)
        {
            removeSignedTeam();
        }
    }

    private void removeSignedTeam()
    {
        if (tblSignedTeams.getSelectionModel().getSelectedItem() == null || tblSignedTeams.getItems() == null)
        {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setContentText("Please select a team");

            alert.showAndWait();
        } else
        {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Delete Confirmation");
            alert.setContentText("Are you sure you want to remove team?");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK)
            {
                int Id = tblSignedTeams.getSelectionModel().getSelectedItem().getTeamID();
                teamParser.removeTeam(Id);
                populateList();
            }
        }

        updateCounter();
    }

    //Returns to previous window so user can change the number of teams in the tournament
    @FXML
    private void handleEditAmountTeams(ActionEvent event)
    {
        Stage stage = (Stage) btnEditNoOfTeams.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleStartTournament(ActionEvent event) throws IOException
    {
        //Sorts the teams into groups when the specified number of teams have joined.
        model.sortTeamsIntoGroups();
        model.sendGroupInfo();

        String tournamentTitle = lblTournamentName.getText();
        model.changeView("Tournament " + tournamentTitle, "GUI/View/GroupStageOverview.fxml", "GroupStageOverview", "null", "null");

        // Closes the primary stage
        Stage stage = (Stage) btnReadyOrNot.getScene().getWindow();
        stage.close();

    }

    private void updateCounter()
    {
        String stringMaxNumOfTeams;
        String stringCurrentNumOfTeams;

        currentNumOfTeams = tblSignedTeams.getItems().size();
        maxNumOfTeams = Integer.parseInt(noOfTeams);

        if (currentNumOfTeams == maxNumOfTeams)
        {
            btnReadyOrNot.setText("Ready");
            btnReadyOrNot.setDisable(false);
            btnAddTeam.setDisable(true);
            lblCountDown.setTextFill(Color.web("#7CFC00"));

        } else if (currentNumOfTeams > maxNumOfTeams)
        {
            btnReadyOrNot.setText("Not Ready");
            btnReadyOrNot.setDisable(true);
            btnAddTeam.setDisable(true);
            lblCountDown.setTextFill(Color.web("#8B0000"));
        } else if (currentNumOfTeams < maxNumOfTeams)
        {
            btnReadyOrNot.setText("Not Ready");
            btnReadyOrNot.setDisable(true);
            btnAddTeam.setDisable(false);
            lblCountDown.setTextFill(Color.web("#FFFFF0"));
        }

        stringMaxNumOfTeams = Integer.toString(maxNumOfTeams);
        stringCurrentNumOfTeams = Integer.toString(currentNumOfTeams);
        lblCountDown.setText(stringCurrentNumOfTeams + " / " + stringMaxNumOfTeams);
    }

}
