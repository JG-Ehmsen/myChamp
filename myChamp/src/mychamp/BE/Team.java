package mychamp.BE;

public class Team
{

    private int teamID; // The ID of the team.
    private String teamName; // The name of the team.
    private String teamSchoolName; // The school the team comes from.
    private int teamGoalsScored; // The amount of goals the team has scored.
    private int teamPoints; // The amount of points the team has earned.

    /**
     * Creates a new 'Team' instance with the provided parameters.
     *
     * @param teamID, the ID of the team.
     * @param teamName, the name of the team.
     * @param teamSchoolName, the name of the team's school.
     */
    public Team(int teamID, String teamName, String teamSchoolName)
    {
        this.teamID = teamID;
        this.teamName = teamName;
        this.teamSchoolName = teamSchoolName;
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
     * Returns the name of the team's school.
     *
     * @return
     */
    public String getTeamSchoolName()
    {
        return teamSchoolName;
    }

    /**
     * Sets the name of the team's school.
     *
     * @param teamSchoolName
     */
    public void setTeamSchoolName(String teamSchoolName)
    {
        this.teamSchoolName = teamSchoolName;
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
