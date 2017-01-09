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
public class GroupStageOverviewController implements Initializable
{
    Model model = new Model();

    @FXML
    private Button btnGoToMatchList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    @FXML
    private void handleGoToMatchList(ActionEvent event) throws IOException
    {
        model.changeView("Upcoming Matches & Resultlist ", "GUI/View/RootLayoutMatchList.fxml");

        // Closes the primary stage
        Stage stage = (Stage) btnGoToMatchList.getScene().getWindow();
        stage.close();
    }
    
}
