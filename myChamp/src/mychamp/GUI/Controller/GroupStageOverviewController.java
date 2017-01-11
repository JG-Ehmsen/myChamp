package mychamp.GUI.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import mychamp.BE.Team;
import mychamp.BLL.MatchManager;
import mychamp.GUI.Model.GroupParser;
import mychamp.GUI.Model.Model;

public class GroupStageOverviewController implements Initializable
{

    private Model model = Model.getInstance();
    private GroupParser groupParser = GroupParser.getInstance();
    private MatchManager matchManager = MatchManager.getInstance();

    @FXML
    private TableView<Team> groupATblVw;
    @FXML
    private TableColumn<Team, String> groupAClmn;
    @FXML
    private TableView<Team> groupBTblVw;
    @FXML
    private TableColumn<Team, String> groupBClmn;
    @FXML
    private TableView<Team> groupCTblVw;
    @FXML
    private TableColumn<Team, String> groupCClmn;
    @FXML
    private TableView<Team> groupDTblVw;
    @FXML
    private TableColumn<Team, String> groupDClmn;
    @FXML
    private Button btnGoToMatchList;

    @FXML
    private TableColumn<Team, String> gamesPlayedClmA;
    @FXML
    private TableColumn<Team, String> gamesWonClmA;
    @FXML
    private TableColumn<Team, String> gamesDrawClmA;
    @FXML
    private TableColumn<Team, String> gamesLostClmA;
    @FXML
    private TableColumn<Team, String> goalsScoredClmA;
    @FXML
    private TableColumn<Team, String> goalsScoredAgainstClmA;
    @FXML
    private TableColumn<Team, String> differenceGoalsClmA;
    @FXML
    private TableColumn<Team, String> teamPointsClmA;
    @FXML
    private TableColumn<Team, String> gamesPlayedClmB;
    @FXML
    private TableColumn<Team, String> gamesWonClmB;
    @FXML
    private TableColumn<Team, String> gamesDrawClmB;
    @FXML
    private TableColumn<Team, String> gamesLostClmB;
    @FXML
    private TableColumn<Team, String> goalsScoredClmB;
    @FXML
    private TableColumn<Team, String> goalsScoredAgainstClmB;
    @FXML
    private TableColumn<Team, String> teamPointsClmB;
    @FXML
    private TableColumn<Team, String> gamesPlayedClmC;
    @FXML
    private TableColumn<Team, String> gamesWonClmC;
    @FXML
    private TableColumn<Team, String> gamesDrawClmC;
    @FXML
    private TableColumn<Team, String> gamesLostClmC;
    @FXML
    private TableColumn<Team, String> goalsScoredClmC;
    @FXML
    private TableColumn<Team, String> goalsScoredAgainstClmC;
    @FXML
    private TableColumn<Team, String> teamPointsClmC;
    @FXML
    private TableColumn<Team, String> gamesPlayedClmD;
    @FXML
    private TableColumn<Team, String> gamesWonClmD;
    @FXML
    private TableColumn<Team, String> gamesDrawClmD;
    @FXML
    private TableColumn<Team, String> gamesLostClmD;
    @FXML
    private TableColumn<Team, String> goalsScoredClmD;
    @FXML
    private TableColumn<Team, String> goalsScoredAgainstClmD;
    @FXML
    private TableColumn<Team, String> teamPointsClmD;
    @FXML
    private TableColumn<?, ?> differenceGoalsClmB;
    @FXML
    private TableColumn<?, ?> differenceGoalsClmC;
    @FXML
    private TableColumn<?, ?> differenceGoalsClmD;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        groupParser.getIsContTour();
        populateList();
    }

    /**
     * Runs the changeView method and closes this stage.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void handleGoToMatchList(ActionEvent event) throws IOException
    {
       
        model.changeView("Upcoming Matches & Results", "GUI/View/MatchListSchedule.fxml", "MatchListSchedule", null, null);

        // Closes the primary stage
        Stage stage = (Stage) btnGoToMatchList.getScene().getWindow();
        stage.close();
    }

    private void populateList()
    {
        groupAClmn.setCellValueFactory(new PropertyValueFactory("teamName"));
        gamesPlayedClmA.setCellValueFactory(new PropertyValueFactory("teamGamesPlayed"));
        gamesWonClmA.setCellValueFactory(new PropertyValueFactory("teamGamesWon"));
        gamesDrawClmA.setCellValueFactory(new PropertyValueFactory("teamGamesDraw"));
        gamesLostClmA.setCellValueFactory(new PropertyValueFactory("teamGamesLost"));
        goalsScoredClmA.setCellValueFactory(new PropertyValueFactory("teamGoalsScored"));
        goalsScoredAgainstClmA.setCellValueFactory(new PropertyValueFactory("teamGoalsScoredAgainst"));
        differenceGoalsClmA.setCellValueFactory(new PropertyValueFactory("teamGoalDifference"));
        teamPointsClmA.setCellValueFactory(new PropertyValueFactory("teamPoints"));

        groupBClmn.setCellValueFactory(new PropertyValueFactory("teamName"));
        gamesPlayedClmB.setCellValueFactory(new PropertyValueFactory("teamGamesPlayed"));
        gamesWonClmB.setCellValueFactory(new PropertyValueFactory("teamGamesWon"));
        gamesDrawClmB.setCellValueFactory(new PropertyValueFactory("teamGamesDraw"));
        gamesLostClmB.setCellValueFactory(new PropertyValueFactory("teamGamesLost"));
        goalsScoredClmB.setCellValueFactory(new PropertyValueFactory("teamGoalsScored"));
        goalsScoredAgainstClmB.setCellValueFactory(new PropertyValueFactory("teamGoalsScoredAgainst"));
        differenceGoalsClmB.setCellValueFactory(new PropertyValueFactory("teamGoalDifference"));
        teamPointsClmB.setCellValueFactory(new PropertyValueFactory("teamPoints"));

        groupCClmn.setCellValueFactory(new PropertyValueFactory("teamName"));
        gamesPlayedClmC.setCellValueFactory(new PropertyValueFactory("teamGamesPlayed"));
        gamesWonClmC.setCellValueFactory(new PropertyValueFactory("teamGamesWon"));
        gamesDrawClmC.setCellValueFactory(new PropertyValueFactory("teamGamesDraw"));
        gamesLostClmC.setCellValueFactory(new PropertyValueFactory("teamGamesLost"));
        goalsScoredClmC.setCellValueFactory(new PropertyValueFactory("teamGoalsScored"));
        goalsScoredAgainstClmC.setCellValueFactory(new PropertyValueFactory("teamGoalsScoredAgainst"));
        differenceGoalsClmC.setCellValueFactory(new PropertyValueFactory("teamGoalDifference"));
        teamPointsClmC.setCellValueFactory(new PropertyValueFactory("teamPoints"));

        groupDClmn.setCellValueFactory(new PropertyValueFactory("teamName"));
        gamesPlayedClmD.setCellValueFactory(new PropertyValueFactory("teamGamesPlayed"));
        gamesWonClmD.setCellValueFactory(new PropertyValueFactory("teamGamesWon"));
        gamesDrawClmD.setCellValueFactory(new PropertyValueFactory("teamGamesDraw"));
        gamesLostClmD.setCellValueFactory(new PropertyValueFactory("teamGamesLost"));
        goalsScoredClmD.setCellValueFactory(new PropertyValueFactory("teamGoalsScored"));
        goalsScoredAgainstClmD.setCellValueFactory(new PropertyValueFactory("teamGoalsScoredAgainst"));
        differenceGoalsClmD.setCellValueFactory(new PropertyValueFactory("teamGoalDifference"));
        teamPointsClmD.setCellValueFactory(new PropertyValueFactory("teamPoints"));

//        if (groupParser.checkGroupRAF() == false)
//        {
//            System.out.println("New tournament.");
//            ObservableList<Team> groupA = FXCollections.observableArrayList(groupParser.getGroupA());
//            groupATblVw.setItems(groupA);
//
//            ObservableList<Team> groupB = FXCollections.observableArrayList(groupParser.getGroupB());
//            groupBTblVw.setItems(groupB);
//
//            ObservableList<Team> groupC = FXCollections.observableArrayList(groupParser.getGroupC());
//            groupCTblVw.setItems(groupC);
//
//            ObservableList<Team> groupD = FXCollections.observableArrayList(groupParser.getGroupD());
//            groupDTblVw.setItems(groupD);
//        } else
//        {
            try
            {
                System.out.println("Continued tournament.");
                ObservableList<Team> groupA = FXCollections.observableArrayList(groupParser.teamNamesInAGroup("GroupA"));
                groupATblVw.setItems(groupA);

                ObservableList<Team> groupB = FXCollections.observableArrayList(groupParser.teamNamesInAGroup("GroupB"));
                groupBTblVw.setItems(groupB);

                ObservableList<Team> groupC = FXCollections.observableArrayList(groupParser.teamNamesInAGroup("GroupC"));
                groupCTblVw.setItems(groupC);

                ObservableList<Team> groupD = FXCollections.observableArrayList(groupParser.teamNamesInAGroup("GroupD"));
                groupDTblVw.setItems(groupD);
            } catch (IOException ex)
            {
                Logger.getLogger(GroupStageOverviewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        //}

    }

}
