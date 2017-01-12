package mychamp.GUI.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import mychamp.BE.Match;
import mychamp.BLL.MatchManager;
import mychamp.GUI.Model.Model;

public class MatchListScheduleController implements Initializable
{

    /**
     * Gets the singleton instance of the model.
     */
    Model model = Model.getInstance();
    /**
     * Gets the singleton instance of the match manager.
     */
    MatchManager matchManager = MatchManager.getInstance();

    @FXML
    private Button btnGoToTable;
    @FXML
    private Button btnUpdateTour;
    @FXML
    private TableView<Match> tblrnd1;
    @FXML
    private TableColumn<Match, String> r1H;
    @FXML
    private TableColumn<Match, String> r1A;
    @FXML
    private TableView<Match> tblrnd2;
    @FXML
    private TableColumn<Match, String> r2H;
    @FXML
    private TableColumn<Match, String> r2A;
    @FXML
    private TableView<Match> tblrnd3;
    @FXML
    private TableColumn<Match, String> r3H;
    @FXML
    private TableColumn<Match, String> r3A;
    @FXML
    private TableView<Match> tblrnd4;
    @FXML
    private TableColumn<Match, String> r4H;
    @FXML
    private TableColumn<Match, String> r4A;
    @FXML
    private TableView<Match> tblrnd5;
    @FXML
    private TableColumn<Match, String> r5H;
    @FXML
    private TableColumn<Match, String> r5A;
    @FXML
    private TableView<Match> tblrnd6;
    @FXML
    private TableColumn<Match, String> r6H;
    @FXML
    private TableColumn<Match, String> r6A;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        populateLists();
    }

    /**
     * Runs the changeView method and closes this stage.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void handleGoToTable(ActionEvent event) throws IOException
    {

        model.changeView("Tournament Overview", "GUI/View/GroupStageOverview.fxml", "GroupStageOverview", null, null);

        // Closes the primary stage
        Stage stage = (Stage) btnGoToTable.getScene().getWindow();
        stage.close();
    }

    /**
     * Runs the changeView method and closes this stage.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void handleGoToManagerView(ActionEvent event) throws IOException
    {
        model.changeView("ManagerView - Update Tournament ", "GUI/View/ManagerView.fxml", "ManagerView", null, null);

        // Closes the primary stage
        Stage stage = (Stage) btnUpdateTour.getScene().getWindow();
        stage.close();
    }

    /**
     * Sets the cell value factories for all the columns in the view, and
     * consecutively loads the correct items into them.
     */
    private void populateLists()
    {
        r1H.setCellValueFactory(new PropertyValueFactory("homeTeamName"));
        r2H.setCellValueFactory(new PropertyValueFactory("homeTeamName"));
        r3H.setCellValueFactory(new PropertyValueFactory("homeTeamName"));
        r4H.setCellValueFactory(new PropertyValueFactory("homeTeamName"));
        r5H.setCellValueFactory(new PropertyValueFactory("homeTeamName"));
        r6H.setCellValueFactory(new PropertyValueFactory("homeTeamName"));

        r1A.setCellValueFactory(new PropertyValueFactory("awayTeamName"));
        r2A.setCellValueFactory(new PropertyValueFactory("awayTeamName"));
        r3A.setCellValueFactory(new PropertyValueFactory("awayTeamName"));
        r4A.setCellValueFactory(new PropertyValueFactory("awayTeamName"));
        r5A.setCellValueFactory(new PropertyValueFactory("awayTeamName"));
        r6A.setCellValueFactory(new PropertyValueFactory("awayTeamName"));

        ObservableList<Match> round1 = FXCollections.observableArrayList(matchManager.matchesForRound(1));
        tblrnd1.setItems(round1);

        ObservableList<Match> round2 = FXCollections.observableArrayList(matchManager.matchesForRound(2));
        tblrnd2.setItems(round2);

        ObservableList<Match> round3 = FXCollections.observableArrayList(matchManager.matchesForRound(3));
        tblrnd3.setItems(round3);

        ObservableList<Match> round4 = FXCollections.observableArrayList(matchManager.matchesForRound(4));
        tblrnd4.setItems(round4);

        ObservableList<Match> round5 = FXCollections.observableArrayList(matchManager.matchesForRound(5));
        tblrnd5.setItems(round5);

        ObservableList<Match> round6 = FXCollections.observableArrayList(matchManager.matchesForRound(6));
        tblrnd6.setItems(round6);

    }

}
