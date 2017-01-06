/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychamp.BLL;

/**
 *
 * @author jonas
 */
public class ResultManager
{
    private static ResultManager instance;
    
    public static ResultManager getInstance()
    {
        if(instance == null)
        {
            instance = new ResultManager();
        }
        
        return instance;
    }
    
}
