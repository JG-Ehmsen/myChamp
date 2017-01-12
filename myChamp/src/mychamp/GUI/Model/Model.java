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

    /**
     * Ensures that the class can be used as a singleton, by making a static
     * instance of it, ensuring that the constructor is private, and having a
     * method that either returns the static instance if it exists, or makes a
     * new one.
     */
    private static Model instance;

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

    /**
     * Gets the singleton instance of the team manager.
     */
    TeamManager teamManager = TeamManager.getInstance();
    /**
     * Gets the singleton instance of the group manager.
     */
    GroupManager groupManager = GroupManager.getInstance();

    public Window stage;

    /**
     * Changes the view, given parameters such as type, title and the path to
     * the new view. Also optionally passes on extra parameters needed in the
     * TeamAddView.
     *
     * @param title
     * @param path
     * @param type
     * @param tournamentTitle
     * @param noOfTeams
     * @throws IOException
     */
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
