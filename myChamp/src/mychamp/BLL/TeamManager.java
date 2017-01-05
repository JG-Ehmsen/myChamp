/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychamp.BLL;

import java.io.IOException;
import java.util.List;
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
}
