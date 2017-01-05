/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychamp.GUI.Model;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import mychamp.MyChamp;

/**
 *
 * @author Kristoffers
 */
public class Model
{
    public Window generatorStage;

    /**
     * Loads the FrontView (MainView) FXML file.
     *
     * @param title
     * @throws IOException
     */
    public void changeView(String title, String path) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MyChamp.class.getResource(path));
        AnchorPane page = (AnchorPane) loader.load();

        Stage dialogStage = new Stage();
        dialogStage.initOwner(generatorStage);
        dialogStage.initModality(Modality.WINDOW_MODAL);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        dialogStage.setTitle(title);

        dialogStage.show();
    }

}
