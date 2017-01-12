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

    /**
     * Ensures that the class can be used as a singleton, by making a static
     * instance of it, ensuring that the constructor is private, and having a
     * method that either returns the static instance if it exists, or makes a
     * new one.
     */
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

    /**
     * Gets a string for a team name and saves said team to teams.txt. A new
     * team record will always have variables starting at 0.
     *
     * @param teamName
     */
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

            teamRAF.seek(getFirstAvailPointer());

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

    /**
     * Gets lists of integers representing the teams that are in a group, and
     * saves them as a single record along with a group ID.
     *
     * @param groupAID
     * @param groupBID
     * @param groupCID
     * @param groupDID
     */
    public void saveAllGroups(List<Integer> groupAID, List<Integer> groupBID, List<Integer> groupCID, List<Integer> groupDID)
    {
        int nextGroupId = 1;
        try
        {
            RandomAccessFile groupRAF = new RandomAccessFile(new File("groups.txt"), "rw");

            groupRAF.writeInt(nextGroupId);
            nextGroupId++;
            saveGroup(groupRAF, groupAID);
            groupRAF.writeInt(nextGroupId + 1);

            saveGroup(groupRAF, groupBID);
            groupRAF.writeInt(nextGroupId + 1);

            saveGroup(groupRAF, groupCID);
            groupRAF.writeInt(nextGroupId + 1);

            saveGroup(groupRAF, groupDID);

        } catch (FileNotFoundException ex)
        {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex)
        {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Method for saving a sinle group, given a RAF instance and a list of ID's
     * that need to be saved. Effectively saves one group.
     *
     * @param groupRAF
     * @param groupArray
     * @throws IOException
     */
    public void saveGroup(RandomAccessFile groupRAF, List<Integer> groupArray) throws IOException
    {
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
            groupRAF.writeInt(-2);
        }

    }

    /**
     * Returns a long that represents the a pointer for a random access file.
     * Enables us to write into records that have previously been cleared, so
     * that empty spots in the file are utilized, and so that the file size will
     * grow unnecessarily large.
     *
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public long getFirstAvailPointer() throws FileNotFoundException, IOException
    {

        try (RandomAccessFile raf = new RandomAccessFile(new File("teams.txt"), "rw"))
        {
            for (long i = INT_SIZE; i < raf.length(); i += RECORD_SIZE_TEAMS)
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

    /**
     * Retunrs a list of teams containing all the teams that have previously
     * been added.
     *
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
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

    /**
     * Is handed a random access file instance and reads the records for a
     * single team, before creating a new instance with said data and returning
     * it.
     *
     * @param raf
     * @return
     * @throws IOException
     */
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

    /**
     * Is handed a single team ID and searches teams.txt for a team with a
     * matching ID, then returns said team if found.
     *
     * @param id
     * @return
     * @throws IOException
     */
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

    /**
     * Given a team ID, this searches the teams.txt file for a matching ID and
     * then returns the team name associated with it.
     *
     * @param id
     * @return
     * @throws IOException
     */
    public String getTeamName(int id) throws IOException
    {
        String teamNameString = "";

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

                }

            }
            return teamNameString;
        }

    }

    /**
     * Given a string representation of a group, this returns a list containing
     * all teams in said group.
     *
     * @param group
     * @return
     * @throws IOException
     */
    public List<Team> getTeamsInGroup(String group) throws IOException
    {
        int offset = 0;
        switch (group)
        {
            case "GroupA":
                offset = INT_SIZE;
                break;
            case "GroupB":
                offset = INT_SIZE + RECORD_SIZE_GROUPS;
                break;
            case "GroupC":
                offset = INT_SIZE + RECORD_SIZE_GROUPS * 2;
                break;
            case "GroupD":
                offset = INT_SIZE + RECORD_SIZE_GROUPS * 3;
                break;
        }

        List<Team> teamsInGroup = new ArrayList();

        try (RandomAccessFile groupRAF = new RandomAccessFile(new File("groups.txt"), "r"))
        {
            int finalID;

            groupRAF.seek(offset);
            for (int i = 0; i < 4; i++)
            {
                int ID = 0;

                ID = groupRAF.readInt();

                if (ID != -2)
                {
                    teamsInGroup.add(getTeamByID(ID));

                }

            }

        }

        return teamsInGroup;

    }

    /**
     * Given a team ID, this searched the teams.txt file for a matching ID, and
     * if found, deletes the records associated with it.
     *
     * @param id
     * @throws IOException
     */
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

    /**
     * Checks whether or not groups.txt has any content, or exists at all.
     *
     * @return
     */
    public boolean checkGroupRAF()
    {
        try (RandomAccessFile groupRAF = new RandomAccessFile(new File("groups.txt"), "r"))
        {
            return groupRAF.length() != 0;
        } catch (FileNotFoundException ex)
        {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex)
        {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Clears both groups.txt and teams.txt file, making ready for a new
     * tournament to be created.
     */
    public void clearAllFiles()
    {
        try
        {
            RandomAccessFile groupRAF = new RandomAccessFile(new File("groups.txt"), "rw");
            RandomAccessFile teamRAF = new RandomAccessFile(new File("teams.txt"), "rw");
            groupRAF.setLength(0);
            teamRAF.setLength(0);
        } catch (FileNotFoundException ex)
        {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex)
        {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
