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
    List<Team> generateMatchList; // = new ArrayList();
    
    List<Match> round1 = new ArrayList();
    List<Match> round2 = new ArrayList();
    List<Match> round3 = new ArrayList();
    List<Match> round4 = new ArrayList();
    List<Match> round5 = new ArrayList();
    List<Match> round6 = new ArrayList();
    
    
    private void generateMatchList() throws IOException
    {
        generateMatchList = fileManager.getAllTeams();
                //getTeamsInGroup(group);
        
        //if odd number of teams create ghostTeam
        boolean ghost = false;
        int teams = 0;//need to extract noOfTeams from each group
        if(teams % 2 == 1)
        {
            teams++;
            ghost = true;
        }
        
        //Generates the match fixturelist using a cyclic algorithm
        int totalRounds = teams - 1;
        int matchesPerRound = teams/2;
        String [][] rounds = new String [totalRounds][matchesPerRound];
        
        for(int round = 0; round < totalRounds; round++)
        {
            for(int match = 0; match < matchesPerRound; match++)
            {
                int home = (round + match) % (teams - 1);
                
                int away = (teams - 1 - match + round) % (teams - 1);
                
                //Last team stays in the same place, while the others rotate around it
                
                if(match == 0)
                {
                    away = teams - 1;
                }
                
                rounds[round][match] = (home + 1) + " v " + (away + 1);
            }
        }
        
        // Interleave so that the home and away games are fairly evenly dispered.
        String[][] interleaved = new String [totalRounds][matchesPerRound];
        int even = 0;
        int odd = (teams / 2);
        
        for(int i = 0; i <  rounds.length; i++)
        {
            if(i % 2 == 0)
            {
                interleaved[i] = rounds[even++];
            }
            else
            {
                interleaved[i] = rounds[odd++];
            }
        }
        
        rounds = interleaved;
        
        //Last team cant be away for every game so flip them 
        //To home on odd rounds.
        
       for(int round = 0; round < rounds.length; round++)
        {
            if(round % 2 == 1)
            {
                rounds[round][0] = flip(rounds[round][0]);
            }
        }
        
        //Display the fixtures
        for(int i = 0; i < rounds.length; i++)
        {
            System.out.println("Round " + (i + 1));
            
            System.out.println(Arrays.asList(rounds[i]));
            System.out.println();
        }
        
        System.out.println();
        if(ghost)
        {
            System.out.println("Matches against team " + teams + "are byes.");
        }
        
        System.out.println("Use mirror image of these rounds " + "return fixtures.");
            
    }
    
    private static String flip(String match) 
    {
        String[] components = match.split(" v ");
        return components[1] + " v " + components[0];
    }
}
