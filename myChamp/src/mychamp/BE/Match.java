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
    private String homeTeam = "";
    private String awayTeam = "";
    
    

public Match(int roundID, String homeTeam, String awayTeam)
{
    this.roundID = roundID;
    this.homeTeam = homeTeam;
    this.awayTeam = awayTeam;
    
}      
             

public int getRoundID()
{
    return roundID;
}

public void setRoundID(int roundID)
{
    this.roundID = roundID;
}

public String getHomeTeam()
{
    return homeTeam;
}

public void setHomeTeam(String homeTeam)
{
    this.homeTeam = homeTeam;
}

public String getAwayTeam()
{
    return awayTeam;
}

public void setAwayTeam(String awayTeam)
{
    this.awayTeam = awayTeam;
}

}