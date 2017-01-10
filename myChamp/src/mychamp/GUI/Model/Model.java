package mychamp.GUI.Model;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import mychamp.BLL.TeamManager;
import mychamp.BLL.GroupManager;
import mychamp.GUI.Controller.TeamsAddViewController;
import mychamp.MyChamp;

public class Model
{

    TeamManager teamManager = TeamManager.getInstance();
    GroupManager groupManager = GroupManager.getInstance();
    
 

    private static Model instance;

    public Window stage;

    public static Model getInstance()
    {

        if (instance == null)
        {
            instance = new Model();
        }
        return instance;
    }

    private Model()
    {

    }

    public void changeView(String title, String path, String type, String tournamentTitle, String noOfTeams) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MyChamp.class.getResource(path));
        AnchorPane page = (AnchorPane) loader.load();

        switch (type)
        {
            case "TeamAddView":
                TeamsAddViewController controller = loader.getController();
                controller.setInformation(tournamentTitle, noOfTeams);
                break;
        }

        Stage dialogStage = new Stage();
        dialogStage.initOwner(stage);
        dialogStage.initModality(Modality.WINDOW_MODAL);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        dialogStage.setTitle(title);

        dialogStage.show();
    }



}
