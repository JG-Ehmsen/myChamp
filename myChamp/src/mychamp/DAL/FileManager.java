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
        if (instance == null) {
            instance = new FileManager();
        }
        return instance;
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

        try (RandomAccessFile teamRAF = new RandomAccessFile(new File("teams.txt"), "rw")) {
            if (teamRAF.length() == 0) {
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

        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void saveGroup(List<Team> groupArray) throws IOException
    {
        int nextGroupId;

        try (RandomAccessFile groupRAF = new RandomAccessFile(new File("groups.txt"), "rw")) {
            if (groupRAF.length() == 0) {
                groupRAF.writeInt(1);
                groupRAF.seek(0);
            }
            nextGroupId = groupRAF.readInt();
            groupRAF.seek(0);
            groupRAF.writeInt(nextGroupId + 1);

            groupRAF.seek(getFirstAvailPointer("group"));
            groupRAF.seek(groupRAF.length());
            //groupRAF.writeInt(nextGroupId);

            for (int i = 0; i < groupArray.size(); i++) {
                groupRAF.writeInt(i);

            }

        }
    }

    public long getFirstAvailPointer(String fileType) throws FileNotFoundException, IOException
    {
        String file="";
        int recordSize=0;
        
        switch (fileType) {
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

        try (RandomAccessFile raf = new RandomAccessFile(new File(file), "r")) {
            for (long i = 0; i < raf.length(); i += recordSize) {
                raf.seek(i);
                int Id = raf.readInt();
                if (Id == -1) {
                    return i;
                }

            }
            return raf.length();

        }
    }

    public List<Team> getAllTeams() throws FileNotFoundException, IOException
    {
        try (RandomAccessFile raf = new RandomAccessFile(new File("teams.txt"), "r")) {
            List<Team> listOfTeams = new ArrayList<>();

            while (raf.getFilePointer() < raf.length()) {
                Team team = getOneTeam(raf);

                if (team != null) {
                    listOfTeams.add(team);
                }

            }
            return listOfTeams;
        }

    }

    private Team getOneTeam(RandomAccessFile raf) throws IOException
    {

        if (raf.getFilePointer() == 0) {
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

        if (teamId == -1) {
            return null;
        }
        return new Team(teamId, teamNameString, gamesPlayed, gamesWon, gamesDraw, gamesLost, goalsScored, goalsAgainst);

    }

    public void clearTeam(int id) throws IOException
    {
        try (RandomAccessFile raf = new RandomAccessFile(new File("teams.txt"), "rw")) {
            for (int pos = INT_SIZE; pos < raf.length(); pos += RECORD_SIZE_TEAMS) {
                raf.seek(pos);
                int teamId = raf.readInt();
                if (teamId == id) {
                    raf.seek(pos);
                    Integer nullId = -1;
                    raf.writeInt(nullId);
                    raf.write(new byte[RECORD_SIZE_TEAMS - INT_SIZE]);
                }

            }
        }

    }
}
