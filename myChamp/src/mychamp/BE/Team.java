package mychamp.BE;

public class Team
{

    private int teamID; // The ID of the team.
    private String teamName = ""; // The name of the team.
    private int teamGoalsScored = 0; // The amount of goals the team has scored.
    private int teamGoalsScoredAgainst = 0; // The amount of goals scored against the team.
    private int teamGamesPlayed = 0; // The total amount of games played by the team.
    private int teamGamesWon = 0; // The amount of games won by the team.
    private int teamGamesLost = 0; // The amount of games lost by the team.
    private int teamGamesDraw = 0; // The amount of games played by the team that ended in a draw.
    private int teamPoints = 0; // The amount of points the team has earned.

    /**
     * Creates a new 'Team' instance with the provided parameters.
     *
     * @param teamID, the ID of the team.
     * @param teamName, the name of the team.
     * @param gamesPlayed, the total amount of games played.
     * @param gamesWon, the amount of games that have been won.
     * @param gamesDraw, the amount of games that have resulted in a draw.
     * @param gamesLost, the amount of games that have been lost.
     * @param goalsScored, the amount of goals the team has scored.
     * @param goalsAgainst, the amount of goals that have been scored against
     * the team.
     */
    public Team(int teamID, String teamName, int gamesPlayed, int gamesWon, int gamesDraw, int gamesLost, int goalsScored, int goalsAgainst)

    {
        this.teamID = teamID;
        this.teamName = teamName;
        this.teamGamesPlayed = gamesPlayed;
        this.teamGamesWon = gamesWon;
        this.teamGamesDraw = gamesDraw;
        this.teamGamesLost = gamesLost;
        this.teamGoalsScored = goalsScored;
        this.teamGoalsScoredAgainst = goalsAgainst;
    }

    /**
     * Returns the ID of the team.
     *
     * @return
     */
    public int getTeamID()
    {
        return teamID;
    }

    /**
     * Sets the ID of the team.
     *
     * @param teamID
     */
    public void setTeamID(int teamID)
    {
        this.teamID = teamID;
    }

    /**
     * Returns the name of the team.
     *
     * @return
     */
    public String getTeamName()
    {
        return teamName;
    }

    /**
     * Sets the name of the team.
     *
     * @param teamName
     */
    public void setTeamName(String teamName)
    {
        this.teamName = teamName;
    }

    /**
     * Returns the amount of goals scored by the team.
     *
     * @return
     */
    public int getTeamGoalsScored()
    {
        return teamGoalsScored;
    }

    /**
     * Sets the amount of goals scored by the team.
     *
     * @param teamGoalsScored
     */
    public void setTeamGoalsScored(int teamGoalsScored)
    {
        this.teamGoalsScored = teamGoalsScored;
    }

    /**
     * Returns the amount of goals scored against the team.
     *
     * @return
     */
    public int getTeamGoalsScoredAgainst()
    {
        return teamGoalsScoredAgainst;
    }

    /**
     * Sets the amount of goals scored against the team.
     *
     * @param teamGoalsScoredAgainst
     */
    public void setTeamGoalsScoredAgainst(int teamGoalsScoredAgainst)
    {
        this.teamGoalsScoredAgainst = teamGoalsScoredAgainst;
    }

    /**
     * Returns the amount of games played by the team.
     *
     * @return
     */
    public int getTeamGamesPlayed()
    {
        return teamGamesPlayed;
    }

    /**
     * Sets the amount of games played by the team.
     *
     * @param teamGamesPlayed
     */
    public void setTeamGamesPlayed(int teamGamesPlayed)
    {
        this.teamGamesPlayed = teamGamesPlayed;
    }

    /**
     * Returns the amount of games won by the team.
     *
     * @return
     */
    public int getTeamGamesWon()
    {
        return teamGamesWon;
    }

    /**
     * Sets the amount of games won by the team.
     *
     * @param teamGamesWon
     */
    public void setTeamGamesWon(int teamGamesWon)
    {
        this.teamGamesWon = teamGamesWon;
    }

    /**
     * Returns the amount of games lost by the team.
     *
     * @return
     */
    public int getTeamGamesLost()
    {
        return teamGamesLost;
    }

    /**
     * Sets the amount of games lost by the team.
     *
     * @param teamGamesLost
     */
    public void setTeamGamesLost(int teamGamesLost)
    {
        this.teamGamesLost = teamGamesLost;
    }

    /**
     * Returns the amount of games played by the team that resulted in a draw.
     *
     * @return
     */
    public int getTeamGamesDraw()
    {
        return teamGamesDraw;
    }

    /**
     * Sets the amount of games played by the team that resulted in a draw.
     *
     * @param teamGamesDraw
     */
    public void setTeamGamesDraw(int teamGamesDraw)
    {
        this.teamGamesDraw = teamGamesDraw;
    }

    /**
     * Returns the amount of points the team has earned.
     *
     * @return
     */
    public int getTeamPoints()
    {
        return teamPoints;
    }

    /**
     * Sets the amount of points the team has earned.
     *
     * @param teamPoints
     */
    public void setTeamPoints(int teamPoints)
    {
        this.teamPoints = teamPoints;
    }

}
