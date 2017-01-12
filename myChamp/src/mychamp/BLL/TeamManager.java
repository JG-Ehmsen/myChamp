package mychamp.BLL;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mychamp.BE.Team;
import mychamp.DAL.FileManager;

public class TeamManager
{

    /**
     * Ensures that the class can be used as a singleton, by making a static
     * instance of it, ensuring that the constructor is private, and having a
     * method that either returns the static instance if it exists, or makes a
     * new one.
     */
    private static TeamManager instance;

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

    /**
     * Gets the singleton instance of the filemanager.
     */
    FileManager fileManager = FileManager.getInstance();

    /**
     * Passes on a teamname for it to be saved in the filemanager.
     *
     * @param teamName
     */
    public void sendTeamInfo(String teamName)
    {
        fileManager.saveTeam(teamName);
    }

    /**
     * Passes on a list of all teams from the filemanager.
     *
     * @return
     * @throws IOException
     */
    public List<Team> getAllTeams() throws IOException
    {
        return fileManager.getAllTeams();
    }

    /**
     * Passes on a team ID to the filemanager to remove said team.
     *
     * @param teamId
     */
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

    /**
     * Passes on a request to clear all files, namely groups.txt and teams.txt.
     */
    public void clearAllFiles()
    {
        fileManager.clearAllFiles();
    }
}
