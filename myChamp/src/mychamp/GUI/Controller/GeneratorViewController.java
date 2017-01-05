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
import javafx.stage.Window;
import mychamp.GUI.Model.Model;

/**
 * FXML Controller class
 *
 * @author Kristoffers
 */
public class GeneratorViewController implements Initializable
{
    Model model = new Model();

    private Window generatorStage;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnNext;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    /**
     * Runs the changeView method and closes the GeneratorView.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void handleBack(ActionEvent event) throws IOException
    {
        model.changeView("MyChamp", "GUI/View/FrontView.fxml");

        // Closes the primary stage
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleNext(ActionEvent event) throws IOException
    {
        model.changeView("MyChamp - Add Teams", "GUI/View/TeamsAddView.fxml");
    }
    
}