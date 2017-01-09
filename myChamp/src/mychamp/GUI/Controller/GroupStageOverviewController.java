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
import mychamp.BE.Team;
import mychamp.GUI.Model.Model;

public class GroupStageOverviewController implements Initializable
{

    private Model model = Model.getInstance();

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        populateList();
    }

    @FXML
    private void handleGoToMatchList(ActionEvent event) throws IOException
    {
        model.changeView("Upcoming Matches & Resultlist ", "GUI/View/MatchListSchedule.fxml", "MatchListSchedule", "null", "null");

        // Closes the primary stage
        Stage stage = (Stage) btnGoToMatchList.getScene().getWindow();
        stage.close();
    }

    private void populateList()
    {
        groupAClmn.setCellValueFactory(new PropertyValueFactory("teamName"));
        ObservableList<Team> groupA = FXCollections.observableArrayList(model.getGroupA());
        groupATblVw.setItems(groupA);

        groupBClmn.setCellValueFactory(new PropertyValueFactory("teamName"));
        ObservableList<Team> groupB = FXCollections.observableArrayList(model.getGroupB());
        groupBTblVw.setItems(groupB);

        groupCClmn.setCellValueFactory(new PropertyValueFactory("teamName"));
        ObservableList<Team> groupC = FXCollections.observableArrayList(model.getGroupC());
        groupCTblVw.setItems(groupC);

        groupDClmn.setCellValueFactory(new PropertyValueFactory("teamName"));
        ObservableList<Team> groupD = FXCollections.observableArrayList(model.getGroupD());
        groupDTblVw.setItems(groupD);
    }

}
