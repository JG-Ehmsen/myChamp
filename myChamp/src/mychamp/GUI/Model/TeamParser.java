/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychamp.GUI.Model;

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
        if(instance == null)
        {
            instance = new TeamParser();
        }
        return instance;
    }
   
    public void addTeam(String teamName)
    {
        teamManager.sendTeamInfo(teamName);
    }
}
