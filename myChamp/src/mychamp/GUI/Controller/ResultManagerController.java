/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychamp.GUI.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mychamp.BE.Result;
import mychamp.BLL.ResultManager;

/**
 * FXML Controller class
 *
 * @author jonas
 */
public class ResultManagerController implements Initializable
{
    private ResultManager resultManager = ResultManager.getInstance();
    private Result currentResult=null;

    @FXML
    private Button closeBtnresultManager;
    @FXML
    private TextField tfScoreTeam1;
    @FXML
    private TextField tfScoreTeam2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    @FXML
    private void btnCloseResultManager(ActionEvent event)
    {
        Stage stage = (Stage) closeBtnresultManager.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btnSaveResultManager(ActionEvent event)
    {
    }
    
}
