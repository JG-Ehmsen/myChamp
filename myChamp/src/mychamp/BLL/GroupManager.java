package mychamp.BLL;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import mychamp.BE.Team;
import mychamp.DAL.FileManager;
import mychamp.GUI.Model.Model;

public class GroupManager
{

    /**
     * Ensures that the class can be used as a singleton, by making a static
     * instance of it, ensuring that the constructor is private, and having a
     * method that either returns the static instance if it exists, or makes a
     * new one.
     */
    private static GroupManager instance;

    public static GroupManager getInstance()
    {
        if (instance == null)
        {
            instance = new GroupManager();
        }
        return instance;
    }

    private GroupManager()
    {
    }

    /**
     * Gets the singleton instance of the filemanager.
     */
    FileManager fileManager = FileManager.getInstance();

    List<Team> shuffleTeams; // Contains the shuffled teams.
    Queue<Team> teamQueue; // Queue of teams ready to get shuffled.

    List<Team> groupA = new ArrayList();// New ArrayList that contains groupA
    List<Team> groupB = new ArrayList();// New ArrayList that contains groupB
    List<Team> groupC = new ArrayList();// New ArrayList that contains groupC
    List<Team> groupD = new ArrayList();// New ArrayList that contains groupD

    /**
     * Shuffles all teams stored in the random access file and puts them in a
     * new linked list.
     */
    private void shuffleTeams() throws IOException
    {
        shuffleTeams = fileManager.getAllTeams();

        Collections.shuffle(shuffleTeams);

        teamQueue = new LinkedList(shuffleTeams);
    }

    /**
     * Retrieves and removes the head team from our queue.
     */
    private Team dequeueTeam()
    {
        return teamQueue.remove();
    }

    /**
     * Sorts the teams in the queue into one of four groups.
     */
    public void sortTeamsIntoGroups()
    {
        try
        {
            shuffleTeams();
            int groupCounter = 1;

            while (teamQueue.peek() != null)
            {
                switch (groupCounter)
                {
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

        } catch (IOException ex)
        {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * returns a list of teams that are in group A.
     *
     * @return
     */
    public List<Team> getGroupA()
    {
        return groupA;
    }

    /**
     * returns a list of teams that are in group B.
     *
     * @return
     */
    public List<Team> getGroupB()
    {
        return groupB;
    }

    /**
     * returns a list of teams that are in group C.
     *
     * @return
     */
    public List<Team> getGroupC()
    {
        return groupC;
    }

    /**
     * returns a list of teams that are in group D.
     *
     * @return
     */
    public List<Team> getGroupD()
    {
        return groupD;
    }

    /**
     * Sends all teams to have their ID's extracted, and then saves all groups.
     */
    public void sendGroupInfo()
    {
        fileManager.saveAllGroups(
                extractTeamIDs(getGroupA()),
                extractTeamIDs(getGroupB()),
                extractTeamIDs(getGroupC()),
                extractTeamIDs(getGroupD())
        );
    }

    /**
     * Gets a list of teams(a group) and returns a list of all their ID's.
     *
     * @param group
     * @return
     */
    private List<Integer> extractTeamIDs(List<Team> group)
    {
        List<Integer> groupWithTeamIDs = new ArrayList();

        for (Team team : group)
        {
            groupWithTeamIDs.add(team.getTeamID());
        }

        return groupWithTeamIDs;
    }

    /**
     * Passes on a string with a group name to the filemanager, and returns a
     * lit of teams.
     *
     * @param group
     * @return
     * @throws IOException
     */
    public List<Team> teamNamesInAGroup(String group) throws IOException
    {
        return fileManager.getTeamsInGroup(group);
    }

    /**
     * Passes through to the fileManager a request for checking the status of
     * the groups.txt file.
     *
     * @return
     */
    public boolean checkGroupRAF()
    {
        return fileManager.checkGroupRAF();
    }
}
