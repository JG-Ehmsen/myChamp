/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychamp.BLL;



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

    private MatchManager()
    {

    }
    
}
