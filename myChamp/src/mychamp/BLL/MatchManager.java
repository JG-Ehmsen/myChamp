/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychamp.BLL;

import java.util.ArrayList;
import java.util.List;
import mychamp.BE.Match;
import mychamp.DAL.FileManager;



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
    
    FileManager fileManager = FileManager.getInstance();
    
    List<Match> round1 = new ArrayList();
    List<Match> round2 = new ArrayList();
    List<Match> round3 = new ArrayList();
    List<Match> round4 = new ArrayList();
    List<Match> round5 = new ArrayList();
    List<Match> round6 = new ArrayList();
}
