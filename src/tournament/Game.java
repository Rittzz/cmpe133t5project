package tournament;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

import common.Restorable;

/**
 * A game of two players playing, has a date of when it occurs and a list of observers for the game because I said so
 * @author Ian Graves
 *
 */
public class Game implements Restorable
{
    // Observers of the Game
    private ArrayList<Observer<Game>> obsList;
    
    // Teams Playing
    private Player teamA;
    private Player teamB;
    
    // Winning Team
    private Player winningTeam;
    
    // Date
    private Calendar date;
    
    public Game(Player p1, Player p2)
    {
	teamA = p1;
	teamB = p2;
	winningTeam = null;
	obsList = new ArrayList<Observer<Game>>();
	date = new GregorianCalendar(); // TEMP SHOULD BE SET TO SOMETHING
    }
    
    public void setDate(Calendar cal)
    {
	date = cal;
    }
    
    public Player getTeamA()
    {
	return teamA;
    }
    
    public Player getTeamB()
    {
	return teamB;
    }
    
    public void addObserver(Observer<Game> o)
    {
	obsList.add(o);
    }
    
    /**
     * Makes the two teams play the game and returns the winner.  The winner is decided at random
     * @return
     */
    public void play()
    {
	if(Math.random() < 0.5)
	    setWinner(teamA);
	else
	    setWinner(teamB);
    }
    
    public Player getWinner()
    {
	return winningTeam;
    }
    
    /**
     * Sets the winner of the match to the Player p.  P must be part of the game or else an error will be thrown
     * @param p the team that won
     */
    public void setWinner(Player p)
    {
	if((teamA == p || teamB == p))
	{
	    if(winningTeam == null)
	    {
		winningTeam = p;
		if(p == teamA) // This code is a mess clean up later
		{
		    teamA.addWin();
		    teamB.addLoss();
		}
		else
		{
		    teamA.addLoss();
		    teamB.addWin();
		}
        	for(Observer<Game> o: obsList)
        	{
        	    o.update(this);
        	}
	    }
	    else
	    {
		throw new Error("Cannot set a winner to a game when a winner is already set!  Winner = " + winningTeam);
	    }
	}
	else
	{
	    throw new Error("The Player " + p + " does not exist in this game!  This game is between " + teamA + " and " + teamB);
	}
    }
    
    /**
     * Returns the date of the game and the two teams playing and if the game has played already, the winner
     */
    public String toString()
    {
	String s = DateUtility.toNormalDate(date) +"\n";
	s += teamA + " vs " + teamB;
	if(winningTeam != null)
	    s += "\nWinning Team " + winningTeam;
	return s;
    }

    public static Game readFile(Scanner r)
    {
	String aName = r.nextLine();
	String bName = r.nextLine();
	String winName = r.nextLine();
	System.out.println("LAL"+winName);
	Calendar cal = DateUtility.parseNormalDate(r.nextLine());
	Player a = PlayerList.getInstance().get(aName);
	Player b = PlayerList.getInstance().get(bName);
	Game newGame = new Game(a,b);
	newGame.setDate(cal);
	if(!winName.equals("NULL"))
	{
	    newGame.setWinner(PlayerList.getInstance().get(winName));
	}
	return newGame;
    }

    @Override
    public void saveFile(FileWriter wr) throws IOException
    {
	wr.write(teamA.getName()+"\n");
	wr.write(teamB.getName()+"\n");
	if(winningTeam != null)
	    wr.write(winningTeam.getName()+"\n");
	else
	    wr.write("NULL"+"\n");
	wr.write(DateUtility.toNormalDate(date)+"\n");
    }
}
