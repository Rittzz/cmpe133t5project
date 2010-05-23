package tournament;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * A SINGULAR PLAYER
 * @author Ian Graves
 *
 */
public class SinglePlayer implements Player
{
    public String name;
    private int winCount;
    private int lossCount;
    
    public SinglePlayer(String n)
    {
	name = n;
	winCount = 0;
	lossCount = 0;
    }
    
    public String getName()
    {
	return name;
    }
    
    public String getStats()
    {
	return name;
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
    }
    
    public void addLoss()
    {
	lossCount++;
    }
    
    public String toString()
    {
	return name + " " + winCount + "-" + lossCount;
    }
    
    public boolean equals(Object o)
    {
	if(o.getClass() != getClass())
	    return false;
	
	SinglePlayer p = (SinglePlayer)o;
	
	return p.name.equals(name);
    }

    public static SinglePlayer readFile(Scanner r)
    {
	// The class name has already been read...
	String name = r.nextLine();
	int wins = Integer.parseInt(r.nextLine());
	int loses = Integer.parseInt(r.nextLine());
	SinglePlayer p = new SinglePlayer(name);
	p.winCount = wins;
	p.lossCount = loses;
	return p;
    }

    @Override
    public void saveFile(FileWriter wr) throws IOException
    {
	wr.write(getClass().getName()+"\n");
	wr.write(name+"\n");
	wr.write(winCount+"\n");
	wr.write(lossCount+"\n");
    }
}
