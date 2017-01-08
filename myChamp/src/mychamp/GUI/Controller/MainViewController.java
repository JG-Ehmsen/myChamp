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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Window;
import mychamp.GUI.Model.Model;

/**
 * FXML Controller class
 *
 * @author jeppe
 */
public class MainViewController implements Initializable
{
    Model model = Model.getInstance();

    private Window primaryStage;
    @FXML
    private Button btnNew;

    private Stage window;
    private Parent root;
    Scene scene;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    /**
     * Runs the showGeneratorView method and closes the primary stage.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void handleNewTournament(ActionEvent event) throws IOException
    {
        model.changeView("MyChamp - Create tournament", "GUI/View/GeneratorView.fxml");

        // Closes the primary stage
        Stage stage = (Stage) btnNew.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleContinueTournament(ActionEvent event)
    {
    }

    @FXML
    private void handleSeeHistory(ActionEvent event)
    {
    }
    
    @FXML
    private void CloseAction(ActionEvent event)
    {
    }

}
