/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychamp.BLL;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mychamp.BE.Team;
import mychamp.DAL.FileManager;

/**
 *
 * @author Fjord82
 */
public class TeamManager
{
    private static TeamManager instance;
    FileManager fileManager = FileManager.getInstance();
    
    public static TeamManager getInstance()
    {
        if(instance == null)
        {
            instance = new TeamManager();
        }
        return instance;
    }
    
    public void sendTeamInfo(String teamName)
    {
        fileManager.saveTeam(teamName);
    }
    
    public List<Team> getAllTeams() throws IOException
    {
        return fileManager.getTeams();
    }

    public void removeTeamInfo(int teamId)
    {
        try
        {
            fileManager.clearTeam(teamId);
        } catch (IOException ex)
        {
            Logger.getLogger(TeamManager.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
}
