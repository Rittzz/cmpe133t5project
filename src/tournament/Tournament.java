package tournament;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import common.Restorable;

/**
 * The big one, contains all information relevant to a tournament
 * @author Ian Graves
 *
 */
public class Tournament implements Restorable
{
    private String name; // The name of the tournament
    private Calendar startDate; // date
    
    private Sport mySport; // the sport the tournament is playing
    private int playerCount; // the total amount of people in the tournament, should implement a check later to see if this is a power of two
    
    private ArrayList<Player> players; // The list of player particpating in the tournament
    private TournamentRound currentRound; // The the current round
    
    private boolean isPlaying; // ARE WE PLAYING YET?
    private boolean finished; // ZOMG WE DONE?
    
    private Player winningTeam; // The winning team
    
    public Tournament(String n, Calendar date, Sport sp, int maxPlayer)
    {
	name = n;
	playerCount = maxPlayer;
	mySport = sp;
	startDate = date;
	players = new ArrayList<Player>();
	isPlaying = false;
	finished = false;
	winningTeam = null;
    }
    
    public String getName()
    {
	return name;
    }
    
    public Calendar getStartDate()
    {
        return startDate;
    }

    public Sport getMySport()
    {
        return mySport;
    }

    public int getPlayerCount()
    {
        return playerCount;
    }
    
    public boolean isFinished()
    {
	return finished;
    }
    
    /**
     * Returns the winner for the tournament, if null it means the tournament hasn't finished yet and there is no winner
     * @return
     */
    public Player getWinner()
    {
	return winningTeam;
    }
    
    public void setPlaying(boolean b)
    {
	isPlaying = b;
    }

    public boolean addPlayer(Player p)
    {
	if(players.size() < playerCount)
	{
	    players.add(p);
	    return true;
	}
	return false;
    }
    
    /**
     * Once all the players have been added to the tournament(exactly the amount given in the playerCount, this will set up the initial tournament round 
     * which will propagate from there
     */
    public void setupTournament()
    {
	if(!isPlaying && !finished)
	{
	    isPlaying = true;
	    // Make Initial Round here
	    currentRound = new TournamentRound(playerCount);
	    for(int i = 0; i < players.size(); i += 2)
	    {
		// Finds the first two and puts them in a game against each other, then the next two etc.
		currentRound.addGame(new Game(players.get(i),players.get(i+1)));
	    }
	}
    }
    
    public ArrayList<Player> getPlayers()
    {
        return players;
    }
    
    /**
     * Seeds the next round of the tournament if the current round finished
     */
    public void seed()
    {
	if(currentRound.isFinished())
	{
	    TournamentRound nextRound = currentRound.nextRound();
	    if(nextRound == null) // means we have winner of the tournament!  Worst implementation ever
	    {
		winningTeam = currentRound.getWinner();
		currentRound = null;
		finished = true;
	    }
	    else
	    {
		currentRound = nextRound;
	    }
	}
	else
	{
	    throw new Error("Cannot seed!  The current round is not finished");
	}
    }
    
    /**
     * Returns the name of the tournament, the sport, and the start date in a string
     * @return
     */
    public String infoString()
    {
	return name + "\t" + mySport + "\t" + DateUtility.toFormalDate(startDate);
    }
    
    /**
     * Returns all information about the Tournament, used for debugging purposes only
     */
    public String toString()
    {
	String s = ""; // Start building the return result...
	s += infoString() + "\n"; // The header and next the players
	
	// Current Players
	s += "Players\n";
	for(Player p : players)
	{
	    s += p + "\n";
	}
	// If we have a winner display it
	if(finished)
	{
	    s += winningTeam;
	}
	else
	{
        	// Games Left in this round
        	ArrayList<Game> gameList = currentRound.getGameList();
        	s += "Games in Round\n";
        	for(Game g : gameList)
        	{
        	    s += g + "\n";
        	}
	}
	return s;
    }
    
    // DEBUG FUNCTION
    
    public void automaticTournament()
    {
	isPlaying = true;
	while(!finished)
	{
	    // First Play all the games
	    ArrayList<Game> gameList = currentRound.getGameList();
	    for(Game g : gameList)
	    {
		g.play();
	    }
	    // Now lets seed up!
	    seed();
	}
    }

    public static Tournament readFile(Scanner r)
    {
	String name = r.nextLine();
	Calendar date = DateUtility.parseNormalDate(r.nextLine());
	Sport mySport = Sport.parse(r.nextLine());
	int pCount = Integer.parseInt(r.nextLine());
	int actualCount = Integer.parseInt(r.nextLine());
	boolean play = Boolean.parseBoolean(r.nextLine());
	boolean finished = Boolean.parseBoolean(r.nextLine());
	Tournament newT = new Tournament(name, date, mySport, pCount);
	newT.isPlaying = play;
	newT.finished = finished;
	for(int i = 0; i < actualCount; i++)
	{
	    String pName = r.nextLine();
	    newT.addPlayer(PlayerList.getInstance().get(pName));
	}
	boolean listCheck = Boolean.parseBoolean(r.nextLine());
	if(listCheck)
	{
	    newT.currentRound = TournamentRound.readFile(r);
	}
	return newT;
    }

    @Override
    public void saveFile(FileWriter wr) throws IOException
    {
	wr.write(name+"\n");
	wr.write(DateUtility.toNormalDate(startDate)+"\n");
	wr.write(mySport+"\n");
	wr.write(playerCount+"\n");
	wr.write(players.size()+"\n");
	wr.write(isPlaying+"\n");
	wr.write(finished+"\n");
	for(Player p : players)
	{
	    wr.write(p.getName()+"\n");
	}
	if(currentRound != null)
	{
	    wr.write("true\n");
	    currentRound.saveFile(wr);
	}
	else
	    wr.write("false\n");
    }
    
    public boolean equals(Object o)
    {
	return this == o; // Override later
    }
}
