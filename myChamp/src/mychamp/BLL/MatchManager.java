package mychamp.BLL;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mychamp.BE.Match;
import mychamp.BE.Team;
import mychamp.DAL.FileManager;

public class MatchManager
{

    /**
     * Ensures that the class can be used as a singleton, by making a static
     * instance of it, ensuring that the constructor is private, and having a
     * method that either returns the static instance if it exists, or makes a
     * new one.
     */
    private static MatchManager instance;

    public static MatchManager getInstance()
    {
        if (instance == null)
        {
            instance = new MatchManager();
        }
        return instance;
    }

    private MatchManager()
    {
    }

    /**
     * Gets the singleton instance of the filemanager.
     */
    FileManager fileManager = FileManager.getInstance();

    /**
     * Given a string representing a group, this method loads the teams and
     * shuffles the list, before returning it as a 2D String array.
     *
     * @param group
     * @return
     * @throws IOException
     */
    private String[][] generateMatchList(String group) throws IOException
    {
        int noOfTeamsInGroup = fileManager.getTeamsInGroup(group).size();

        //Generates the match fixturelist using a cyclic algorithm.
        int totalRounds = noOfTeamsInGroup - 1;
        int matchesPerRound = noOfTeamsInGroup / 2;

        String[][] rounds = new String[totalRounds][matchesPerRound];

        for (int round = 0; round < totalRounds; round++)
        {
            for (int match = 0; match < matchesPerRound; match++)
            {
                int home = (round + match) % (noOfTeamsInGroup - 1);

                int away = (noOfTeamsInGroup - 1 - match + round) % (noOfTeamsInGroup - 1);

                //Last team stays in the same place, while the others rotate around it
                if (match == 0)
                {
                    away = noOfTeamsInGroup - 1;
                }

                rounds[round][match] = (home) + " v " + (away);
            }
        }

        for (int round = 0; round < rounds.length; round++)
        {
            if (round % 2 == 1)
            {
                rounds[round][0] = flip(rounds[round][0]);
            }
        }

        String[][] mirrorRounds = new String[totalRounds][matchesPerRound];

        for (int round = 0; round < totalRounds; round++)
        {
            for (int match = 0; match < matchesPerRound; match++)
            {
                mirrorRounds[round][match] = flip(rounds[round][match]);

            }
        }

        String[][] groupRounds = new String[6][2];

        for (int round = 0; round < 6; round++)
        {
            for (int match = 0; match < 2; match++)
            {
                if (round < 3 && match < 2)
                {
                    groupRounds[round][match] = rounds[round][match];
                } else
                {
                    groupRounds[round][match] = mirrorRounds[round - 3][match];
                }

            }
        }

        return groupRounds;

    }

    /**
     * Flips the string contained within a string array.
     *
     * @param match
     * @return
     */
    private String flip(String match)
    {
        String[] components = match.split(" v ");
        return components[1] + " v " + components[0];
    }

    /**
     * Gets a string representation for a group and schedules the matches within
     * the group stage.
     *
     * @param group
     * @return
     * @throws IOException
     */
    private List<Match> scheduleGroup(String group) throws IOException
    {
        String[][] generatedMatches = generateMatchList(group);

        List<Team> teamsInGroup = new ArrayList(fileManager.getTeamsInGroup(group));

        List<Match> groupMatchlist = new ArrayList();

        int roundID = 0;
        int matchID = 0;
        int homeID = 0;
        int awayID = 0;

        for (int round = 0; round < 6; round++)
        {
            roundID = round;
            for (int match = 0; match < 2; match++)
            {
                matchID = match;
                String[] string = generatedMatches[round][match].split(" v ");

                int teamHome = Integer.parseInt(string[0]);
                int teamAway = Integer.parseInt(string[1]);

                for (int index = 0; index < 4; index++)
                {
                    Team team = teamsInGroup.get(index);

                    if (teamHome == index)
                    {
                        homeID = team.getTeamID();
                    }
                    if (teamAway == index)
                    {
                        awayID = team.getTeamID();

                    }
                }
                Match newMatch = new Match(roundID, matchID, homeID, awayID, fileManager.getTeamName(homeID), fileManager.getTeamName(awayID));
                groupMatchlist.add(newMatch);
            }
        }
        return groupMatchlist;
    }

    /**
     * Given a specific round in the tournament, this method returns the matches
     * that are played in that round.
     *
     * @param round
     * @return
     */
    public List<Match> matchesForRound(int round)
    {
        List<Match> allMatches = new ArrayList();
        try
        {
            allMatches.addAll(scheduleGroup("groupA"));

            allMatches.addAll(scheduleGroup("groupB"));

            allMatches.addAll(scheduleGroup("groupC"));

            allMatches.addAll(scheduleGroup("groupD"));

        } catch (IOException ex)
        {
            Logger.getLogger(MatchManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Match> round1 = new ArrayList();
        List<Match> round2 = new ArrayList();
        List<Match> round3 = new ArrayList();
        List<Match> round4 = new ArrayList();
        List<Match> round5 = new ArrayList();
        List<Match> round6 = new ArrayList();

        for (Match match : allMatches)
        {
            int roundID = match.getRoundID();

            switch (roundID)
            {
                case 0:
                    round1.add(match);
                    break;
                case 1:
                    round2.add(match);
                    break;
                case 2:
                    round3.add(match);
                    break;
                case 3:
                    round4.add(match);
                    break;
                case 4:
                    round5.add(match);
                    break;
                case 5:
                    round6.add(match);
                    break;
            }
        }

        if (round == 1)
        {
            return round1;
        }
        if (round == 2)
        {
            return round2;
        }
        if (round == 3)
        {
            return round3;
        }
        if (round == 4)
        {
            return round4;
        }
        if (round == 5)
        {
            return round5;
        }
        if (round == 6)
        {
            return round6;
        }

        return null;
    }

}
