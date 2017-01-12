package mychamp.BLL;

/**
 * Currently unused.
 *
 */
public class ResultManager
{

    private static ResultManager instance;

    public static ResultManager getInstance()
    {
        if (instance == null)
        {
            instance = new ResultManager();
        }

        return instance;
    }

    private ResultManager()
    {

    }

}
