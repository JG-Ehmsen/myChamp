package mychamp.GUI.Model;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mychamp.BE.Team;
import mychamp.BLL.TeamManager;

public class TeamParser
{

    private static TeamParser instance;
    TeamManager teamManager = TeamManager.getInstance();

    public static TeamParser getInstance()
    {
        if (instance == null)
        {
            instance = new TeamParser();
        }
        return instance;
    }

    private TeamParser()
    {
    }

    public void addTeam(String teamName)
    {
        teamManager.sendTeamInfo(teamName);
    }

    public ObservableList<Team> loadTeamsIntoViewer()
    {

        try
        {
            ObservableList<Team> teamLibrary = FXCollections.observableArrayList(teamManager.getAllTeams());
            return teamLibrary;
        } catch (IOException ex)
        {
            Logger.getLogger(TeamParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void removeTeam(int teamId)
    {
        teamManager.removeTeamInfo(teamId);
    }

    public List<Team> getAllTeams()
    {
        try
        {
            return teamManager.getAllTeams();
        } catch (IOException ex)
        {
            Logger.getLogger(TeamParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
