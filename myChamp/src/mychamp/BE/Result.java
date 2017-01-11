package mychamp.BE;

public class Result
{
    private int matchID;
    private int homeTeamResult;
    private int awayTeamResult;
    
    
    public Result(int matchID, int homeTeamResult, int awayTeamResult)
    {
        this.matchID = matchID;
        this.homeTeamResult = homeTeamResult;
        this.awayTeamResult = awayTeamResult;
    }

    public int getMatchID()
    {
        return matchID;
    }

    public void setMatchID(int matchID)
    {
        this.matchID = matchID;
    }

    public int getHomeTeamResult()
    {
        return homeTeamResult;
    }

    public void setHomeTeamResult(int homeTeamResult)
    {
        this.homeTeamResult = homeTeamResult;
    }

    public int getAwayTeamResult()
    {
        return awayTeamResult;
    }

    public void setAwayTeamResult(int awayTeamResult)
    {
        this.awayTeamResult = awayTeamResult;
    }
    
    
}
