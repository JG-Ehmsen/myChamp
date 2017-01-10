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

public class ResultManagerController implements Initializable
{

    private ResultManager resultManager = ResultManager.getInstance();
    private Result currentResult = null;

    @FXML
    private Button closeBtnresultManager;
    @FXML
    private TextField tfScoreTeam1;
    @FXML
    private TextField tfScoreTeam2;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    /**
     * Closes this stage.
     *
     * @param event
     */
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
