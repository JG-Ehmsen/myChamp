/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychamp.DAL;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fjord82
 */
public class FileManager
{
    private static final int INT_SIZE = Integer.BYTES;
 
    private static final int TEAM_NAME_SIZE = 50;
    
    private static final int RECORD_SIZE_TEAMS = TEAM_NAME_SIZE + (INT_SIZE*7); 
    
    
    private static FileManager instance;
    
    public static FileManager getInstance()
    {
        if(instance == null)
        {
            instance = new FileManager();
        }
        return instance;    
    }
    
    public void saveTeam(String teamName)
    {
        int nextId;
        //To be implemented : when the ManagerGUIView has been developed!
        int gamesPlayed = 0;
        int gamesWon = 0;
        int gamesDraw = 0;
        int gamesLost = 0;
        int goalsScored = 0;
        int goalsAgainst = 0;
        
        
        try (RandomAccessFile raf = new RandomAccessFile(new File("teams.txt"), "rw"))
        {
            if(raf.length() == 0)
            {
                raf.writeInt(1);
                raf.seek(0);
            }
            nextId = raf.readInt();
            raf.seek(0);
            raf.writeInt(nextId + 1);
            
            raf.seek(getFirstAvailPointer());
            
            raf.writeInt(nextId);
            raf.writeBytes(String.format("%-" + TEAM_NAME_SIZE + "s", teamName).substring(0,TEAM_NAME_SIZE));
            raf.writeInt(gamesPlayed);
            raf.writeInt(gamesWon);
            raf.writeInt(gamesDraw);
            raf.writeInt(gamesLost);
            raf.writeInt(goalsScored);
            raf.writeInt(goalsAgainst);
            
        } 
        
        
        catch (IOException ex)
        {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public long getFirstAvailPointer() throws FileNotFoundException, IOException
    {
        try(RandomAccessFile raf = new RandomAccessFile(new File("teams.txt"), "r"))
        {
            for (long i = 0; i < raf.length(); i+= RECORD_SIZE_TEAMS)
            {
                raf.seek(i);
                int Id = raf.readInt();
                if(Id == -1)
                {
                    return i;
                }
        
            }
            return raf.length();
            
        }
    }
}

