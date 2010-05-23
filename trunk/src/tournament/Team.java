package tournament;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A collection of players
 * @author Ian Graves
 *
 */
public class Team implements Player
{
    private ArrayList<String> playerNames;
    private ArrayList<Player> members;
    private int winCount;
    private int lossCount;
    private String name;
    
    public Team(String n)
    {
	name = n;
	members = new ArrayList<Player>();
	winCount = 0;
	lossCount = 0;
	playerNames = new ArrayList<String>();
    }
    
    public String getName()
    {
	return name;
    }
    
    public void addPlayer(Player p)
    {
	if(p==null)
	    throw new NullPointerException();
	members.add(p);
	playerNames.add(p.getName());
    }
    
    public void loadPlayers()
    {
	members.clear();
	for(String name: playerNames)
	{
	    members.add(PlayerList.getInstance().get(name));
	}
    }

    @Override
    public String getStats()
    {
	String s = "";
	for(Player p : members)
	{
	    s += p.getStats();
	}
	return s;
    }

    public int getLossCount()
    {
	return lossCount;
    }

    @Override
    public int getWinCount()
    {
	return winCount;
    }
    
    public void addWin()
    {
	winCount++;
	for(Player p : members)
	{
	    p.addWin();
	}
    }
    
    public void addLoss()
    {
	lossCount++;
	for(Player p : members)
	{
	    p.addLoss();
	}
    }
    
    public boolean equals(Object o)
    {
	if(o.getClass() != getClass())
	    return false;
	
	Team p = (Team)o;
	
	return p.name.equals(name);
    }

    public static Team readFile(Scanner r)
    {
	// The class name has already been read...
	String name = r.nextLine();
	int wins = Integer.parseInt(r.nextLine());
	int loses = Integer.parseInt(r.nextLine());
	Team t = new Team(name);
	t.winCount = wins;
	t.lossCount = loses;
	for(String s = r.nextLine(); !s.equals(name); s = r.nextLine())
	{
	    t.playerNames.add(s);
	}
	return t;
    }

    @Override
    public void saveFile(FileWriter wr) throws IOException
    {
	wr.write(getClass().getName()+"\n");
	wr.write(name+"\n");
	wr.write(winCount+"\n");
	wr.write(lossCount+"\n");
	// This stuff gets complicated fast...
	for(Player p: members)
	{
	    wr.write(p.getName()+"\n");
	}
	wr.write(name+"\n");
    }
}
