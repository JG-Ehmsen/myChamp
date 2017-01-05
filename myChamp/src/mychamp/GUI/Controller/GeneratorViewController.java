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
 * @author Kristoffers
 */
public class GeneratorViewController implements Initializable
{

    private Window generatorStage;
    @FXML
    private Button btnBack;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    @FXML
    private void handleBack(ActionEvent event) throws IOException
    {
        showFrontView();

        // Closes the primary stage
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close();
    }


    private void showFrontView() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MyChamp.class.getResource("GUI/View/FrontView.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        MainViewController controller = loader.getController();

        Stage dialogStage = new Stage();
        dialogStage.initOwner(generatorStage);
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(generatorStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        dialogStage.setTitle("MyChamp");

        dialogStage.show();
    }

}
