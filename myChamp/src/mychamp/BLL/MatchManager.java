package mychamp.BLL;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import mychamp.BE.Match;
import mychamp.BE.Team;
import mychamp.DAL.FileManager;

public class MatchManager
{

    FileManager fileManager = FileManager.getInstance();

    private static MatchManager instance;

    public static MatchManager getInstance()
    {
        if (instance == null)
        {
            instance = new MatchManager();
        }
        return instance;
    }
    private String group;

    private MatchManager()
    {

    }

    List<Match> round1 = new ArrayList();
    List<Match> round2 = new ArrayList();
    List<Match> round3 = new ArrayList();
    List<Match> round4 = new ArrayList();
    List<Match> round5 = new ArrayList();
    List<Match> round6 = new ArrayList();

    private String[][] generateMatchList(String group) throws IOException
    {
        int noOfTeamsInGroup = fileManager.getTeamsInGroup(group).size();

        //If there are odd number of teams then we create a local ghost team.
        boolean ghost = false;

        if (noOfTeamsInGroup % 2 == 1)
        {
            noOfTeamsInGroup++;
            ghost = true;
        }

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

    private String flip(String match)
    {
        String[] components = match.split(" v ");
        return components[1] + " v " + components[0];
    }

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

                Match newMatch = new Match(roundID, matchID, homeID, awayID);
                groupMatchlist.add(newMatch);
            }

        }

        return groupMatchlist;
    }

//    public void printGroups() throws IOException
//    {
//        List<Match> list = scheduleGroup("groupA");
//
//        for (Match match : list)
//        {
//            System.out.println("matchID: " + match.getMatchID() + " roundID: " + match.getRoundID() + " home: " + match.getHomeTeamID() + " away: " + match.getAwayTeamID());
//        }
//        List<Team> teamsInGroup = fileManager.getTeamsInGroup("groupA");
//
//        for (Team team : teamsInGroup)
//        {
//           System.out.println("teamID: " + team.getTeamID());
//        }
//    for (int round = 0; round < 6; round++)
//        {
//            for (int match = 0; match < 2; match++)
//            {
//                System.out.println(generatedMatches[round][match]);
//            }
//
//        }
//        
//        
//        for (Team team : teamsInGroup)
//        {
//            System.out.println("teamID: " + team.getTeamID());
//        }
//    }
}
