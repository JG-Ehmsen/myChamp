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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import mychamp.GUI.Model.Model;

public class GeneratorViewController implements Initializable
{

    /**
     * Gets the singleton instance of the model.
     */
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
        txtFldTournamentTitle.setPromptText("Tournament title");
        fillComboBox();
    }

    /**
     * Runs the changeView method and closes the FrontView.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void handleBack(ActionEvent event) throws IOException
    {
        model.changeView("MyChamp", "GUI/View/FrontView.fxml", "FrontView", null, null);

        // Closes the primary stage
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close();
    }

    /**
     * Runs the goToAddTeamView method.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void handleNext(ActionEvent event) throws IOException
    {
        goToAddTeamView();
    }

    /**
     * Runs the changeView method and closes this stage. Shows an alert if info
     * is missing in cBoxNoOfTeams or txtFldTournamentTitle.
     *
     * @throws IOException
     */
    private void goToAddTeamView() throws IOException
    {
        if (cBoxNoOfTeams.getValue() == null || txtFldTournamentTitle.getText().equals("") || txtFldTournamentTitle.getText() == null)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setContentText("Some fields have not been filled. Please fill in all fields.");

            alert.showAndWait();
        } else
        {
            model.changeView("MyChamp - Add Teams", "GUI/View/TeamsAddView.fxml", "TeamAddView", txtFldTournamentTitle.getText(), cBoxNoOfTeams.getValue());

            // Closes the primary stage
            Stage stage = (Stage) btnBack.getScene().getWindow();
            stage.close();
        }
    }

    /**
     * Populates the combo box to choose number of teams participating in the
     * tournament.
     */
    private void fillComboBox()
    {
        ObservableList<String> comboItems
                = FXCollections.observableArrayList(null, "12", "13", "14", "15", "16");
        cBoxNoOfTeams.setItems(comboItems);
        cBoxNoOfTeams.getSelectionModel().selectFirst();
    }

    @FXML
    private void tourTitlePressed(KeyEvent event)
    {
        if (event.getCode() == KeyCode.ENTER)
        {
            cBoxNoOfTeams.requestFocus();
        }
    }

    @FXML
    private void noOfTeamsPressed(KeyEvent event) throws IOException
    {
        if (event.getCode() == KeyCode.ENTER)
        {
            goToAddTeamView();
        }
    }
}
