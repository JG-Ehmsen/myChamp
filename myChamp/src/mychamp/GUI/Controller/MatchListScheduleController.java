/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 * FXML Controller class
 *
 * @author Fjord82
 */
public class MatchListScheduleController implements Initializable
{

    Model model = Model.getInstance();

    @FXML
    private Button btnGoToTable;
    private Object lblTournamentName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    @FXML
    private void handleGoToTable(ActionEvent event) throws IOException
    {

        model.changeView("Tournament " + lblTournamentName, "GUI/View/GroupStageOverview.fxml");

        // Closes the primary stage
        Stage stage = (Stage) btnGoToTable.getScene().getWindow();
        stage.close();
    }

}
