/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychamp.BE;

/**
 *
 * @author Fjord82
 */
public class Match
{
    private int roundID;
    private int group;
    private int homeTeamID;
    private int awayTeamID;
    
    

public Match(int roundID, int group, int homeTeamID, int awayTeamID)
{
   this.roundID = roundID;
    this.group = group;
    this.homeTeamID = homeTeamID;
    this.awayTeamID = awayTeamID;
    
}      
             

public int getRoundID()
{
    return roundID;
}

public void setRoundID(int roundID)
{
    this.roundID = roundID;
}

    public int getGroup()
    {
        return group;
    }

    public void setGroup(int group)
    {
        this.group = group;
    }

    public int getHomeTeamID()
    {
        return homeTeamID;
    }

    public void setHomeTeamID(int homeTeamID)
    {
        this.homeTeamID = homeTeamID;
    }

    public int getAwayTeamID()
    {
        return awayTeamID;
    }

    public void setAwayTeamID(int awayTeamID)
    {
        this.awayTeamID = awayTeamID;
    }



}