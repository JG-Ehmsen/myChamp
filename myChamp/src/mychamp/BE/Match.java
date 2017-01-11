package mychamp.BE;

public class Match
{

    private int roundID;
    private int matchID;
    private int homeTeamID;
    private int awayTeamID;
    private String homeTeamName = "";

    private String awayTeamName = "";

    public Match(int roundID, int matchID, int homeTeamID, int awayTeamID, String homeTeamName, String awayTeamName)
    {
        this.roundID = roundID;
        this.matchID = matchID;
        this.homeTeamID = homeTeamID;
        this.awayTeamID = awayTeamID;
        this.homeTeamName=homeTeamName;
        this.awayTeamName=awayTeamName;
    }

    public String getHomeTeamName()
    {
        return homeTeamName;
    }

    public String getAwayTeamName()
    {
        return awayTeamName;
    }

    public int getMatchID()
    {
        return matchID;
    }

    public int getRoundID()
    {
        return roundID;
    }

    public int getHomeTeamID()
    {
        return homeTeamID;
    }

    public int getAwayTeamID()
    {
        return awayTeamID;
    }

}
