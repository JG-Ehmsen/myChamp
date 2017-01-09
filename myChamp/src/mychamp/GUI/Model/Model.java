package mychamp.GUI.Model;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import mychamp.BLL.TeamManager;
import java.util.List;
import mychamp.BE.Team;
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

    public void changeView(String title, String path) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MyChamp.class.getResource(path));
        AnchorPane page = (AnchorPane) loader.load();

        Stage dialogStage = new Stage();
        dialogStage.initOwner(stage);
        dialogStage.initModality(Modality.WINDOW_MODAL);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        dialogStage.setTitle(title);

        dialogStage.show();
    }

    //this method can be re-written using the one above, so we dont have repeat of the same code
    public void loadTeamAddView(String title, String path, String tournamentTitle, String noOfTeams) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MyChamp.class.getResource(path));
        AnchorPane page = (AnchorPane) loader.load();
        TeamsAddViewController controller = loader.getController();
        controller.setInformation(tournamentTitle, noOfTeams);

        Stage dialogStage = new Stage();
        dialogStage.initOwner(stage);
        dialogStage.initModality(Modality.WINDOW_MODAL);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        dialogStage.setTitle(title);

        //dialogStage.setTitle(title);
        dialogStage.show();
    }

    public void sortTeamsIntoGroups()
    {
        groupManager.sortTeamsIntoGroups();
    }

    public void sendGroupInfo()
    {
        groupManager.sendGroupInfo();
    }

    public List<Team> getGroupA()
    {
        return groupManager.getGroupA();
    }

    public List<Team> getGroupB()
    {
        return groupManager.getGroupB();
    }

    public List<Team> getGroupC()
    {
        return groupManager.getGroupC();
    }

    public List<Team> getGroupD()
    {
        return groupManager.getGroupD();
    }

}
