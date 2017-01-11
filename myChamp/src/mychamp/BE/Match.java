package mychamp.BE;

public class Match
{

    private int roundID;
    private int matchID;
    private int groupID;

    public int getMatchID()
    {
        return matchID;
    }

    public void setMatchID(int matchID)
    {
        this.matchID = matchID;
    }
    private int homeTeamID;
    private int awayTeamID;

    public Match(int roundID, int matchID, int homeTeamID, int awayTeamID)
    {
        this.roundID = roundID;
        this.matchID = matchID;
        //this.groupID = groupID;
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

    public int getGroupID()
    {
        return groupID;
    }

    public void setGroupID(int groupID)
    {
        this.groupID = groupID;
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
