/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychamp.GUI.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
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

    Model model = Model.getInstance();

    private Window generatorStage;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnNext;
    @FXML
    private TextField txtFldTournamentTitle;
    @FXML
    private ComboBox<String> cBoxNoOfTeams;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        fillComboBox();
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
        if (cBoxNoOfTeams.getValue() == null)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Tournament Size Selected");
            alert.setContentText("Please select a Tournament Size");

            alert.showAndWait();
        }
        else if (cBoxNoOfTeams.getValue() != null)
        model.loadTeamAddView("MyChamp - Add Teams", "GUI/View/TeamsAddView.fxml", txtFldTournamentTitle.getText(), cBoxNoOfTeams.getValue());

    }

    private void fillComboBox()
    {
        ObservableList<String> comboItems
                = FXCollections.observableArrayList(null, "12", "13", "14", "15", "16");
        cBoxNoOfTeams.setItems(comboItems);
        cBoxNoOfTeams.getSelectionModel().selectFirst();
    }
}
