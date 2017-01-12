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
import mychamp.GUI.Model.GroupParser;
import mychamp.GUI.Model.Model;
import mychamp.GUI.Model.TeamParser;

public class TeamsAddViewController implements Initializable
{

    /**
     * Gets the singleton instance of the model.
     */
    private Model model = Model.getInstance();

    /**
     * Gets the singleton instance of the team parser.
     */
    private TeamParser teamParser = TeamParser.getInstance();
    /**
     * Gets the singleton instance of the group parser.
     */
    private GroupParser groupParser = GroupParser.getInstance();

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

    private String noOfTeams = null; // The max nr of teams in string form.

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

    /**
     * Runs the sendTeamName method.
     *
     * @param event
     */
    @FXML
    private void handleAddTeam(ActionEvent event)
    {
        sendTeamNames();
    }

    @FXML
    private void handleEnterPressed(KeyEvent event)
    {
        if (event.getCode() == KeyCode.ENTER && !btnAddTeam.isDisabled())
        {
            sendTeamNames();
        }
    }

    /**
     * Sets the cell value factory for the table view containing signed up
     * teams, and loads teams that have previously been added into it.
     */
    private void populateList()
    {
        clnJoiningTeams.setCellValueFactory(new PropertyValueFactory("teamName"));
        tblSignedTeams.setItems(teamParser.loadTeamsIntoViewer());
    }

    /**
     * Asks for the title of the tournament, along with the max number of teams
     * in string form, before setting a label in the view with the tournament
     * title and setting the 'noOfTeams' variable to the max number of teams.
     *
     * @param tournamentTitle
     * @param noOfTeams
     */
    public void setInformation(String tournamentTitle, String noOfTeams)
    {
        this.lblTournamentName.setText(tournamentTitle);
        this.noOfTeams = noOfTeams;
        updateCounter();

    }

    /**
     * Checks to see if the name that has currently been written is already in
     * use, or if nothing has been written at all. If not, adds a new team,
     * refreshes the table and updates the counter.
     */
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
        txtFldTeamName.requestFocus();
    }

    /**
     * Runs the removeSigneTeam method.
     *
     * @param event
     */
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

    /**
     * Runs the changeView method and closes this stage.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void handleEditAmountTeams(ActionEvent event) throws IOException
    {
        model.changeView("MyChamp - Create tournament", "GUI/View/GeneratorView.fxml", "Generator", null, null);

        Stage stage = (Stage) btnEditNoOfTeams.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleStartTournament(ActionEvent event) throws IOException
    {
        //Sorts the teams into groups when the specified number of teams have joined.
        groupParser.sortTeamsIntoGroups();
        groupParser.sendGroupInfo();

        String tournamentTitle = lblTournamentName.getText();
        model.changeView("Tournament " + tournamentTitle, "GUI/View/GroupStageOverview.fxml", "GroupStageOverview", null, null);

        // Closes the primary stage
        Stage stage = (Stage) btnReadyOrNot.getScene().getWindow();
        stage.close();

    }

    /**
     * Gets information about the currently selected team in the list, and
     * deletes it. Produces an error message if no team has been selected. Also
     * asks for confirmation before deleting. The request is then relayed
     * downwards through the layers. Finally calls a request to update the
     * counter.
     */
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
                tblSignedTeams.getSelectionModel().select(0);
            }
        }

        updateCounter();
    }

    /**
     * Updates the counter for the amount of teams added/maximum team capacity,
     * and disables buttons accordingly.
     */
    private void updateCounter()
    {
        String stringMaxNumOfTeams; // The integer 'maxNumOfTeams' parsed to a string.
        String stringCurrentNumOfTeams; // The integer 'currentNumOfTeams' parsed to a string.
        int maxNumOfTeams = 0; // An integer representing the maximum number of teams in a tournament.
        int currentNumOfTeams = 0; // An integer representing the currently added number of teams in a tournament.

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
