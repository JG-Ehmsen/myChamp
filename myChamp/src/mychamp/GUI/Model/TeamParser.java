/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychamp.GUI.Model;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import mychamp.BE.Team;
import mychamp.BLL.TeamManager;

/**
 *
 * @author Fjord82
 */
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
    
    public List<Team> getTeams()
    {
        return teamManager.getTeams();
    }
}
