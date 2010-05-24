package tournament;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.table.AbstractTableModel;

import common.Observer;

/**
 * Contains a list a tournaments, implements the singleton design pattern.  Easy to insert into a JTable.
 * @author Ian Graves
 *
 */
public class TournamentList extends AbstractTableModel
{
    private static final String FILE_NAME = "TournamentDatabase.txt";
    private static final TournamentList INSTANCE = new TournamentList();

    private ArrayList<Tournament> list;
    
    private TournamentList()
    {
	list = new ArrayList<Tournament>();
	// Load the file here
	
    }
    
    public static TournamentList getInstance()
    {
	return INSTANCE;
    }
    
    public void addTournament(Tournament t)
    {
	if(list.contains(t))
	{
	    throw new Error("Tournament " + t.getName() + " already exist in the Database");
	}
	list.add(t);
    }
    
    public Tournament get(int index)
    {
	return list.get(index);
    }
    
    public int size()
    {
	return getRowCount();
    }
    
    /**
     * Returns a copy of a list of all the players in the player database,
     * @return
     */
    public ArrayList<Tournament> getAll()
    {
	return new ArrayList<Tournament>(list);
    }
    
    public Tournament get(String name)
    {
	for(Tournament p : list)
	{
	    if(p.getName().equals(name))
		return p;
	}
	System.err.println("WARNING!  Null Tournament returned!");
	throw null;
    }
    
    // Table Methods for displaying this in a JTable
    
    /**
     * There are 3 columns for a tournament, the name, the sport, and the start date
     */
    public int getColumnCount()
    {
	return 3;
    }

    public int getRowCount()
    {
	return list.size();
    }

    public String getColumnName(int col)
    {
	switch(col){
	    case 0:
		return "Name";
	    case 1:
		return "Sport";
	    case 2:
		return "Start Date";
	    case 3:
		return "Player Type";
	    case 4:
		return "Spots Available";
	}
	return "ERROR";
    }

    public Object getValueAt(int row, int col)
    {
	Tournament current = list.get(row);
	
	switch(col){
	    case 0:
		return current.getName();
	    case 1:
		return current.getMySport();
	    case 2:
		return DateUtility.toNormalDate(current.getStartDate());
	    case 3:
		return current.getType().toLabel();
	    case 4:
		return current.getPlayerCount()-current.getCurrentPlayerCount();
	}
	
	return "NO VALUE FOUND!";
    }
    
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
    
    // Writing and Loading methods
    
    protected void save()
    {
	try
	{
	    File db = new File(FILE_NAME);
	    FileWriter wr = new FileWriter(db);
	    for (Tournament t : this.list)
	    {
		t.saveFile(wr);
	    }
	    wr.close();
	}
	catch(IOException e)
	{
	    System.err.println("ERROR SAVING TOURNAMENT DATABASE");
	    e.printStackTrace();
	}
    }

    protected void load()
    {
	try
	{
	    list.clear();
	    File db = new File(FILE_NAME);
	    Scanner re = new Scanner(db);
	    while(re.hasNext())
	    {
		Tournament t = Tournament.readFile(re);
		addTournament(t);
		//cl = re.nextLine();
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
    
    public void addObserverToTournament(Observer<Tournament> obs, String[] listNames)
    {
	for(String tName : listNames)
	{
	    boolean found = false;
	    for(Tournament t : list)
	    {
		if(t.getName().equals(tName))
		{
		    found = true;
		    t.addObserver(obs);
		}
	    }
	    if(!found)
	    {
		System.err.println("Could not find a Tournament named " + tName + " to add to " + obs);
	    }
	}
    }
}
