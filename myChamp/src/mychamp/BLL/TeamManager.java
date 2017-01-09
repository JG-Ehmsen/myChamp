package mychamp.BLL;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mychamp.BE.Team;
import mychamp.DAL.FileManager;

public class TeamManager
{

    private static TeamManager instance;

    FileManager fileManager = FileManager.getInstance();

    public static TeamManager getInstance()
    {
        if (instance == null)
        {
            instance = new TeamManager();
        }
        return instance;
    }

    private TeamManager()
    {

    }

    public void sendTeamInfo(String teamName)
    {
        fileManager.saveTeam(teamName);
    }

    public List<Team> getAllTeams() throws IOException
    {
        return fileManager.getAllTeams();
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
