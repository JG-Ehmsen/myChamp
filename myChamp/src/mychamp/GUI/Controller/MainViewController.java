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
        model.changeView("MyChamp - Create tournament", "GUI/View/GeneratorView.fxml");

        // Closes the primary stage
        Stage stage = (Stage) btnNew.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleContinueTournament(ActionEvent event) throws IOException
    {
        model.changeView("MyChamp - Continue tournament", "GUI/View/GroupStageOverview.fxml");

        Stage stage = (Stage) btnContinue.getScene().getWindow();
        stage.close();
    }
}
