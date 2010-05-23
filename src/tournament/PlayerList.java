package tournament;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.table.AbstractTableModel;

/**
 * The Main Datebase for all the Players in the system, uses singleton design pattern.  AbstractTableModel allows this to be fit into a 
 * JTable easily
 * @author Ian Graves
 *
 */
public class PlayerList extends AbstractTableModel
{
    private static final String FILE_NAME = "PlayerDatabase.txt";
    private static final PlayerList INSTANCE = new PlayerList();

    private ArrayList<Player> list;
    
    private PlayerList()
    {
	list = new ArrayList<Player>(); // Dumb
    }
    
    public static PlayerList getInstance()
    {
	return INSTANCE;
    }
    
    /**
     * Adds a player to the list, does NOT add duplicates to the database
     * @param p
     */
    public void addPlayer(Player p)
    {
	if(!list.contains(p))
	    list.add(p);
    }

    public Player get(int index)
    {
	return list.get(index);
    }
    
    public Player get(String name)
    {
	for(Player p : list)
	{
	    if(p.getName().equals(name))
		return p;
	}
	throw new NullPointerException("No player found by " + name);
    }
    
    // TABLE METHODS
    
    /**
     * 3 Columns, name, win, and lose counts
     */
    public int getColumnCount()
    {
	return 3;
    }

    public int getRowCount()
    {
	return list.size();
    }

    
    
    @Override
    public String getColumnName(int column)
    {
	switch(column) {
	    case 0:
		return "Name";
	    case 1:
		return "Wins";
	    case 2:
		return "Losses";
	}
	return super.getColumnName(column);
    }

    public Object getValueAt(int row, int col)
    {
	Player current = list.get(row);
	switch(col) {
	    case 0:
		return current.getName();
	    case 1:
		return current.getWinCount();
	    case 2:
		return current.getLossCount();
	}
	return "NO VALUE FOUND!";
    }
    
    /**
     * Prints out all the players in the list
     */
    public String toString()
    {
	String s = "";
	
	for(int j = 0; j < getColumnCount(); j++)
	    {
		s += getColumnName(j);
		if(j < getColumnCount() - 1)
		    s += "\t";
	    }
	s += "\n";
	
	for(int i = 0; i < getRowCount(); i++)
	{
	    for(int j = 0; j < getColumnCount(); j++)
	    {
		s += getValueAt(i,j);
		if(j < getColumnCount() - 1)
		    s += "\t";
	    }
	    if(i < getRowCount() - 1)
		    s += "\n";
	}
	
	return s;
    }
    
    protected void save()
    {
	try
	{
	    File db = new File(FILE_NAME);
	    FileWriter wr = new FileWriter(db);
	    for (Player p : list)
	    {
		p.saveFile(wr);
	    }
	    wr.write("end");
	    wr.close();
	}
	catch(IOException e)
	{
	    System.err.println("ERROR SAVING PLAYER DATABASE");
	    e.printStackTrace();
	}
    }
    
    /**
     * This is going to be slightly complicated due to the Player Interface composite structure we have going on
     */
    protected void load()
    {
	try
	{
	    list.clear();
	    File db = new File(FILE_NAME);
	    Scanner re = new Scanner(db);
	    String cl = re.nextLine();
	    while(!cl.equals("end"))
	    {
		if(cl.equals(SinglePlayer.class.getName()))
		{
		    SinglePlayer p = SinglePlayer.readFile(re);
		    list.add(p);
		}
		else if(cl.equals(Team.class.getName()))
		{
		    Team t = Team.readFile(re);
		    list.add(t);
		}
		else
		{
		    throw new IOException();
		}
		cl = re.nextLine();
	    }
	    // First read complete now lets fix the teams...
	    for(Player p: list)
	    {
		if(p.getClass() == Team.class)
		{
		    Team t = (Team)p;
		    t.loadPlayers();
		}
	    }
	}
	catch(FileNotFoundException e)
	{
	    System.err.println("File player database not found");
	}
	catch(IOException e)
	{
	    System.err.println("Error Parsing Player database File");
	}
	
    }
}
