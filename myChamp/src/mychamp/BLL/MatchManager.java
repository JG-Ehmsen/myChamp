/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychamp.BLL;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import mychamp.BE.Match;
import mychamp.BE.Team;
import mychamp.DAL.FileManager;

/**
 *
 * @author Fjord82
 */
public class MatchManager
{

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

    FileManager fileManager = FileManager.getInstance();
    //List<Team> generateMatchList; // = new ArrayList();

    List<Match> round1 = new ArrayList();
    List<Match> round2 = new ArrayList();
    List<Match> round3 = new ArrayList();
    List<Match> round4 = new ArrayList();
    List<Match> round5 = new ArrayList();
    List<Match> round6 = new ArrayList();

    private void generateMatchList(String group) throws IOException
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

                rounds[round][match] = (home + 1) + " v " + (away + 1);
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

    }

    private static String flip(String match)
    {
        String[] components = match.split(" v ");
        return components[1] + " v " + components[0];
    }

}
