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

    private static GroupManager instance;

    public static GroupManager getInstance()
    {
        if (instance == null)
        {
            instance = new GroupManager();
        }
        return instance;
    }

    public GroupManager()
    {
    }

    FileManager fileManager = FileManager.getInstance();

    List<Team> shuffleTeams;// = new ArrayList();
    Queue<Team> teamQueue;

    List<Team> groupA = new ArrayList();
    List<Team> groupB = new ArrayList();
    List<Team> groupC = new ArrayList();
    List<Team> groupD = new ArrayList();

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

    //Getters for group which will show which teams are in each group.
    public List<Team> getGroupA()
    {
        return groupA;
    }

    public List<Team> getGroupB()
    {
        return groupB;
    }

    public List<Team> getGroupC()
    {
        return groupC;
    }

    public List<Team> getGroupD()
    {
        return groupD;
    }

    public void sendGroupInfo()
    {
        try
        {
            fileManager.saveGroup(groupA);
            fileManager.saveGroup(groupB);
            fileManager.saveGroup(groupC);
            fileManager.saveGroup(groupD);
        } catch (IOException ex)
        {
            Logger.getLogger(GroupManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
