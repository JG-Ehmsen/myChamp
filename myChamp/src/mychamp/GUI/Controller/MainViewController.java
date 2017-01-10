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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import mychamp.GUI.Model.Model;

public class MainViewController implements Initializable
{

    Model model = Model.getInstance();

    private Window primaryStage;
    @FXML
    private Button btnNew;

    private Stage window;
    private Parent root;
    Scene scene;
    @FXML
    private Button btnContinue;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    @FXML
    private void handleNewTournament(ActionEvent event) throws IOException
    {
        newTournament();
    }

    @FXML
    private void newTourPressed(KeyEvent event) throws IOException
    {
        if (event.getCode() == KeyCode.ENTER)
        {
            newTournament();
        }
    }

    private void newTournament() throws IOException
    {
        model.changeView("MyChamp - Create tournament", "GUI/View/GeneratorView.fxml", "Generator", null, null);

        // Closes the primary stage
        Stage stage = (Stage) btnNew.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleContinueTournament(ActionEvent event) throws IOException
    {
        contTournament();
    }

    @FXML
    private void contTourPressed(KeyEvent event) throws IOException
    {
        if (event.getCode() == KeyCode.ENTER)
        {
            contTournament();
        }
    }

    private void contTournament() throws IOException
    {
        model.changeView("MyChamp - Continue tournament", "GUI/View/GroupStageOverview.fxml", "GroupStageOverview", null, null);

        Stage stage = (Stage) btnContinue.getScene().getWindow();
        stage.close();
    }
}
