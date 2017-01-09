package mychamp.GUI.Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import mychamp.BE.Team;
import mychamp.BLL.TeamManager;
import mychamp.GUI.Controller.TeamsAddViewController;
import mychamp.MyChamp;

public class Model {

    TeamManager teamManager = TeamManager.getInstance();

    List<Team> shuffleTeams;// = new ArrayList();
    Queue<Team> teamQueue;

    List<Team> groupA = new ArrayList();
    List<Team> groupB = new ArrayList();
    List<Team> groupC = new ArrayList();
    List<Team> groupD = new ArrayList();

    private static Model instance;

    public Window stage;

    public static Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

    private Model() {

    }

    public void changeView(String title, String path) throws IOException {
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
    public void loadTeamAddView(String title, String path, String tournamentTitle, String noOfTeams) throws IOException {
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

        dialogStage.show();
    }

    /**
     * Shuffles all teams stored in the random access file and puts them in a
     * new linked list.
     */
    private void shuffleTeams() throws IOException {
        shuffleTeams = teamManager.getAllTeams();
        Collections.shuffle(shuffleTeams);

        teamQueue = new LinkedList(shuffleTeams);
    }

    /**
     * Retrieves and removes the head team from our queue.
     */
    private Team dequeueTeam() {
        return teamQueue.remove();
    }
    
    /**
     * Sorts the teams in the queue into one of four groups. 
     */
    public void sortTeamsIntoGroups() {
        try {
            shuffleTeams();
            int groupCounter = 1;
            
            while (teamQueue.peek() != null) {
                switch (groupCounter) {
                    case 1:
                        groupA.add(dequeueTeam());
                        break;
                    case 2:
                        groupB.add(dequeueTeam());
                        break;
                    case 3:
                        groupC.add(dequeueTeam());
                        break;
                    case 4:
                        groupD.add(dequeueTeam());
                        groupCounter = 0;
                        break;
                }
                groupCounter++;
            }
        } catch (IOException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Getters for group which will show which teams are in each group.
    public List<Team> getGroupA() {
        return groupA;
    }

    public List<Team> getGroupB() {
        return groupB;
    }

    public List<Team> getGroupC() {
        return groupC;
    }

    public List<Team> getGroupD() {
        return groupD;
    }
    
    
    public void sendGroupInfo(){
      
            teamManager.sendGroupInfo(groupA);
            teamManager.sendGroupInfo(groupB);
            teamManager.sendGroupInfo(groupC);
            teamManager.sendGroupInfo(groupD);
    }
    
}
