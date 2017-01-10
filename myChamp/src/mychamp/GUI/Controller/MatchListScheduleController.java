package mychamp.GUI.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import mychamp.GUI.Model.Model;

public class MatchListScheduleController implements Initializable
{

    Model model = Model.getInstance();

    @FXML
    private Button btnGoToTable;

    private Object lblTournamentName;

    @FXML
    private Button btnUpdateTour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
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

        model.changeView("Tournament " + lblTournamentName, "GUI/View/GroupStageOverview.fxml", "GroupStageOverview", null, null);

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

}
