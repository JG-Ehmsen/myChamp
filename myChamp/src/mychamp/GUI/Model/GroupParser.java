package mychamp.GUI.Model;

import java.io.IOException;
import java.util.List;
import mychamp.BE.Team;
import mychamp.BLL.GroupManager;

public class GroupParser
{

    /**
     * Ensures that the class can be used as a singleton, by making a static
     * instance of it, ensuring that the constructor is private, and having a
     * method that either returns the static instance if it exists, or makes a
     * new one.
     */
    private static GroupParser instance;

    public static GroupParser getInstance()
    {

        if (instance == null)
        {
            instance = new GroupParser();
        }
        return instance;
    }

    private GroupParser()
    {
    }

    /**
     * Gets the singleton instance of the group manager.
     */
    GroupManager groupManager = GroupManager.getInstance();

    public List<Team> teamNamesInAGroup(String group) throws IOException
    {
        return groupManager.teamNamesInAGroup(group);
    }

    public void sortTeamsIntoGroups()
    {
        groupManager.sortTeamsIntoGroups();
    }

    public void sendGroupInfo()
    {
        groupManager.sendGroupInfo();
    }

    public List<Team> getGroupA()
    {
        return groupManager.getGroupA();
    }

    public List<Team> getGroupB()
    {
        return groupManager.getGroupB();
    }

    public List<Team> getGroupC()
    {
        return groupManager.getGroupC();
    }

    public List<Team> getGroupD()
    {
        return groupManager.getGroupD();
    }

    public boolean checkGroupRAF()
    {
        return groupManager.checkGroupRAF();
    }

    public boolean getIsContTour()
    {
        return checkGroupRAF();
    }

}
