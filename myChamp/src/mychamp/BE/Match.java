package mychamp.BE;

public class Match
{

    private int roundID; // The ID of the round within which this match takes place.
    private int matchID; // The ID of the match within which this takes place.
    private int homeTeamID; // The ID of the home team in the match.
    private int awayTeamID; // The ID of the away team in the match.
    private String homeTeamName = ""; // The name of the home team in this match.
    private String awayTeamName = ""; // The name of the away team in this match.

    /**
     * Creates a new match with the given parameters.
     *
     * @param roundID
     * @param matchID
     * @param homeTeamID
     * @param awayTeamID
     * @param homeTeamName
     * @param awayTeamName
     */
    public Match(int roundID, int matchID, int homeTeamID, int awayTeamID, String homeTeamName, String awayTeamName)
    {
        this.roundID = roundID;
        this.matchID = matchID;
        this.homeTeamID = homeTeamID;
        this.awayTeamID = awayTeamID;
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
    }

    /**
     * Returns the home team name.
     *
     * @return
     */
    public String getHomeTeamName()
    {
        return homeTeamName;
    }

    /**
     * Returns the away team name.
     *
     * @return
     */
    public String getAwayTeamName()
    {
        return awayTeamName;
    }

    /**
     * returns the ID of the match.
     *
     * @return
     */
    public int getMatchID()
    {
        return matchID;
    }

    /**
     * Returns the ID of the round.
     *
     * @return
     */
    public int getRoundID()
    {
        return roundID;
    }

    /**
     * Returns the ID of the home team for this match.
     *
     * @return
     */
    public int getHomeTeamID()
    {
        return homeTeamID;
    }

    /**
     * Returns the ID of the away team for this match.
     *
     * @return
     */
    public int getAwayTeamID()
    {
        return awayTeamID;
    }

}
