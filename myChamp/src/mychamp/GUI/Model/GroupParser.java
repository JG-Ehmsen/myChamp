package mychamp.GUI.Model;

import java.io.IOException;
import java.util.List;
import mychamp.BE.Team;
import mychamp.BLL.GroupManager;

public class GroupParser
{

    private static GroupParser instance;
    GroupManager groupManager = GroupManager.getInstance();

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
    
    public List<Team> teamNamesInAGroup(String group) throws IOException{
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
}
