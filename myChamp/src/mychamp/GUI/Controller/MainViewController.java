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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import mychamp.MyChamp;

/**
 * FXML Controller class
 *
 * @author jeppe
 */
public class MainViewController implements Initializable
{

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
        showGeneratorView();

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

    /**
     * Loads the FXML file to create a new tournament (GeneratorView.fxml)
     *
     * @param title
     * @throws IOException
     */
    private void showGeneratorView() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MyChamp.class.getResource("GUI/View/GeneratorView.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        GeneratorViewController controller = loader.getController();

        Stage dialogStage = new Stage();
        dialogStage.initOwner(primaryStage);
        dialogStage.initModality(Modality.WINDOW_MODAL);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        dialogStage.setTitle("MyChamp - Create new tournament");

        dialogStage.show();
    }

    @FXML
    private void CloseAction(ActionEvent event)
    {
    }

}
