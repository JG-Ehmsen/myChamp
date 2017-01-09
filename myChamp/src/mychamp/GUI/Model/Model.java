/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychamp.GUI.Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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

/**
 *
 * @author Kristoffers
 */
public class Model
{

    TeamManager teamManager = TeamManager.getInstance();

    List<Team> shuffleTeams = new ArrayList();
    Queue<Team> teamQueue;

    private static Model instance;

    public Window generatorStage;
    
    public static Model getInstance()
    {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

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
    
    public void loadTeamAddView(String title, String path, String tournamentTitle, String noOfTeams) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MyChamp.class.getResource(path));
        AnchorPane page = (AnchorPane) loader.load();
        TeamsAddViewController controller = loader.getController();
        controller.setInformation(tournamentTitle, noOfTeams);
        

        Stage dialogStage = new Stage();
        dialogStage.initOwner(generatorStage);
        dialogStage.initModality(Modality.WINDOW_MODAL);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        
        
        
        //dialogStage.setTitle(title);
        
        dialogStage.show();
    }

    private void shuffleTeams() throws IOException
    {
        shuffleTeams = teamManager.getAllTeams();
        Collections.shuffle(shuffleTeams);

        teamQueue = new LinkedList(shuffleTeams);
    }

    // retrieves and removes the head team from our queue
    private Team dequeueTeam()
    {
        return teamQueue.remove();
    }

    public void sortTeamsIntoGroups()
    {
        List<Team> group1 = new ArrayList();
        List<Team> group2 = new ArrayList();
        List<Team> group3 = new ArrayList();
        List<Team> group4 = new ArrayList();

        int groupCounter = 1;

        while (teamQueue.peek() != null) {
            if (groupCounter == 1) {
                group1.add(dequeueTeam());
            } else if (groupCounter == 2) {
                group2.add(dequeueTeam());
            } else if (groupCounter == 3) {
                group3.add(dequeueTeam());
            } else {
                group4.add(dequeueTeam());
                groupCounter = 0;
            }
            groupCounter++;
        }
    }

}
