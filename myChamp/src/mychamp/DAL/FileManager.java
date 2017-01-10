package mychamp.DAL;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mychamp.BE.Team;

public class FileManager
{

    private static final int INT_SIZE = Integer.BYTES;

    //Team file final variables
    private static final int TEAM_NAME_SIZE = 50;
    private static final int RECORD_SIZE_TEAMS = TEAM_NAME_SIZE + (INT_SIZE * 7);

    //Group file final variables
    private static final int RECORD_SIZE_GROUPS = (INT_SIZE * 5);

    //Round file final variables - still need to do this
    private static final int RECORD_SIZE_ROUNDS = (INT_SIZE * 5);

    private static FileManager instance;

    public static FileManager getInstance()
    {
        if (instance == null)
        {
            instance = new FileManager();
        }
        return instance;
    }

    private FileManager()
    {

    }

    public void saveTeam(String teamName)
    {
        int nextId;
        //To be implemented : when the ManagerGUIView has been developed!
        int gamesPlayed = 0;
        int gamesWon = 0;
        int gamesDraw = 0;
        int gamesLost = 0;
        int goalsScored = 0;
        int goalsAgainst = 0;

        try (RandomAccessFile teamRAF = new RandomAccessFile(new File("teams.txt"), "rw"))
        {
            if (teamRAF.length() == 0)
            {
                teamRAF.writeInt(1);
                teamRAF.seek(0);
            }
            nextId = teamRAF.readInt();
            teamRAF.seek(0);
            teamRAF.writeInt(nextId + 1);

            teamRAF.seek(getFirstAvailPointer("team"));

            teamRAF.writeInt(nextId);
            teamRAF.writeBytes(String.format("%-" + TEAM_NAME_SIZE + "s", teamName).substring(0, TEAM_NAME_SIZE));
            teamRAF.writeInt(gamesPlayed);
            teamRAF.writeInt(gamesWon);
            teamRAF.writeInt(gamesDraw);
            teamRAF.writeInt(gamesLost);
            teamRAF.writeInt(goalsScored);
            teamRAF.writeInt(goalsAgainst);

        } catch (IOException ex)
        {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void saveGroup(List<Integer> groupArray) throws IOException
    {
        int nextGroupId;

        try (RandomAccessFile groupRAF = new RandomAccessFile(new File("groups.txt"), "rw"))
        {
            if (groupRAF.length() == 0)
            {
                groupRAF.writeInt(1);
                groupRAF.seek(0);
            }
            nextGroupId = groupRAF.readInt();
            groupRAF.seek(0);
            groupRAF.writeInt(nextGroupId + 1);

            groupRAF.seek(getFirstAvailPointer("group"));
            groupRAF.writeInt(nextGroupId);

            if (groupArray.size() == 4)
            {
                for (Integer teamID : groupArray)
                {
                    groupRAF.writeInt(teamID);
                }

            } else
            { //Only accounts for 3 teams in the group. Writes 0 for the ID of the 4th team.
                for (Integer teamID : groupArray)
                {
                    groupRAF.writeInt(teamID);
                    

                }
                groupRAF.writeInt(0);
            }

        }
    }

    public long getFirstAvailPointer(String fileType) throws FileNotFoundException, IOException
    {
        String file = "";
        int recordSize = 0;

        switch (fileType)
        {
            case "team":
                file = "teams.txt";
                recordSize = RECORD_SIZE_TEAMS;
                break;
            case "group":
                file = "groups.txt";
                recordSize = RECORD_SIZE_GROUPS;
                break;
            case "round":
                file = "rounds.txt";
                recordSize = RECORD_SIZE_ROUNDS;
                break;
        }

        try (RandomAccessFile raf = new RandomAccessFile(new File(file), "rw"))
        {
            for (long i = 0; i < raf.length(); i += recordSize)
            {
                raf.seek(i);
                int Id = raf.readInt();
                if (Id == -1)
                {
                    return i;
                }

            }
            return raf.length();

        }
    }

    public List<Team> getAllTeams() throws FileNotFoundException, IOException
    {
        try (RandomAccessFile raf = new RandomAccessFile(new File("teams.txt"), "rw"))
        {
            List<Team> listOfTeams = new ArrayList<>();

            while (raf.getFilePointer() < raf.length())
            {
                Team team = getOneTeam(raf);

                if (team != null)
                {
                    listOfTeams.add(team);
                }

            }
            return listOfTeams;
        }

    }

    private Team getOneTeam(RandomAccessFile raf) throws IOException
    {

        if (raf.getFilePointer() == 0)
        {
            raf.seek(INT_SIZE);
        }
        int teamId = raf.readInt();

        byte[] teamName = new byte[TEAM_NAME_SIZE];
        raf.read(teamName);
        String teamNameString = new String(teamName).trim();

        int gamesPlayed = raf.readInt();
        int gamesWon = raf.readInt();
        int gamesDraw = raf.readInt();
        int gamesLost = raf.readInt();
        int goalsScored = raf.readInt();
        int goalsAgainst = raf.readInt();

        if (teamId == -1)
        {
            return null;
        }
        return new Team(teamId, teamNameString, gamesPlayed, gamesWon, gamesDraw, gamesLost, goalsScored, goalsAgainst);

    }

    private Team getTeamByID(int id) throws IOException
    {
        String teamNameString = "";

        int gamesPlayed = 0, gamesWon = 0, gamesDraw = 0, gamesLost = 0, goalsScored = 0, goalsAgainst = 0;

        try (RandomAccessFile teamRAF = new RandomAccessFile(new File("teams.txt"), "r"))
        {

            for (int pos = INT_SIZE; pos < teamRAF.length(); pos += RECORD_SIZE_TEAMS)
            {
                teamRAF.seek(pos);
                int teamId = teamRAF.readInt();
                if (teamId == id)
                {

                    byte[] teamName = new byte[TEAM_NAME_SIZE];
                    teamRAF.read(teamName);
                    teamNameString = new String(teamName).trim();

                    gamesPlayed = teamRAF.readInt();
                    gamesWon = teamRAF.readInt();
                    gamesDraw = teamRAF.readInt();
                    gamesLost = teamRAF.readInt();
                    goalsScored = teamRAF.readInt();
                    goalsAgainst = teamRAF.readInt();
                }

            }
            return new Team(id, teamNameString, gamesPlayed, gamesWon, gamesDraw, gamesLost, goalsScored, goalsAgainst);
        }

    }

    public List<Team> getTeamsInGroup(String group) throws IOException
    {
        int offset = 0;
        switch (group)
        {
            case "GroupA":
                offset = INT_SIZE;
                break;
            case "GroupB":
                offset = INT_SIZE+RECORD_SIZE_GROUPS;
                break;
            case "GroupC":
                offset = INT_SIZE+RECORD_SIZE_GROUPS * 2;
                break;
            case "GroupD":
                offset = INT_SIZE+RECORD_SIZE_GROUPS * 3;
                break;
        }

        List<Team> teamsInGroup = new ArrayList();

        try (RandomAccessFile groupRAF = new RandomAccessFile(new File("groups.txt"), "r"))
        {
            int finalID;

            groupRAF.seek(offset);
            teamsInGroup.add(getTeamByID(groupRAF.readInt()));
            teamsInGroup.add(getTeamByID(groupRAF.readInt()));
            teamsInGroup.add(getTeamByID(groupRAF.readInt()));

            finalID = groupRAF.readInt();
            if (finalID > 0)
            {
                teamsInGroup.add(getTeamByID(finalID));
            }
        }
        return teamsInGroup;

    }

    public void clearTeam(int id) throws IOException
    {
        try (RandomAccessFile raf = new RandomAccessFile(new File("teams.txt"), "rw"))
        {
            for (int pos = INT_SIZE; pos < raf.length(); pos += RECORD_SIZE_TEAMS)
            {
                raf.seek(pos);
                int teamId = raf.readInt();
                if (teamId == id)
                {
                    raf.seek(pos);
                    Integer nullId = -1;
                    raf.writeInt(nullId);
                    raf.write(new byte[RECORD_SIZE_TEAMS - INT_SIZE]);
                }

            }
        }

    }
}
