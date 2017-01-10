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
    private ComboBox<Team> cbTeam;

    TeamParser teamParser = TeamParser.getInstance();
    private Button btnBackMatchlist;
    @FXML
    private Button btnRemoveWndw;
    @FXML
    private Button btnHandleResult;

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

    private void removeTeamTour() throws IOException
    {
        /* //load the fxml file and creat a new stage for the popup dialog.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MyChamp.class.getResource("GUI/View/RemoveTeam.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        //ResultManagerController controller = loader.getController();
        //controller.setResult(result);

        // Create the dialog stage.
        Stage dialogStage = new Stage();
        //dialogStage.setTitle(remove_Controller);
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        dialogStage.showAndWait();*/
    }

    private void showResultManagerWindow() throws IOException
    {
        /*try
        {
            //load the fxml file and creat a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MyChamp.class.getResource("GUI/View/ResultManager.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            //ResultManagerController controller = loader.getController();
            //controller.setResult(result);

            // Create the dialog stage.
            Stage dialogStage = new Stage();
            //dialogStage.setTitle(result_Manager);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            dialogStage.showAndWait();
        } catch (IOException e)
        {
            e.printStackTrace();
        }*/
    }

    private void fillComboBoxRound()
    {
        ObservableList<String> comboItems
                = FXCollections.observableArrayList(null, "1", "2", "3", "4", "5", "6", "7", "8", "9");
        cbRound.setItems(comboItems);
        cbRound.getSelectionModel().selectFirst();
    }

    private void fillComboBoxGroup()
    {
        ObservableList<String> comboItems
                = FXCollections.observableArrayList(null, "A", "B", "C", "D");
        cbGroup.setItems(comboItems);
        cbGroup.getSelectionModel().selectFirst();
    }

    private void fillComboBoxTeam()
    {
        List<Team> teamList = new ArrayList();
        teamList = teamParser.getAllTeams();
        ObservableList<Team> comboItems
                = FXCollections.observableArrayList(teamList);
        cbTeam.setItems(comboItems);
        cbTeam.getSelectionModel().selectFirst();
    }

    @FXML
    private void handleRemoveWndw(ActionEvent event) throws IOException
    {
        model.changeView("Remove team ", "GUI/View/RemoveTeam.fxml", "RemoveTeam", null, null);
    }

    @FXML
    private void handleGoToResultManager(ActionEvent event) throws IOException
    {
        model.changeView("Update Result ", "GUI/View/ResultManager.fxml", "ResultManager", null, null);
    }
}
