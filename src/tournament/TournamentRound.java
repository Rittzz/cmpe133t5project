package tournament;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import common.Restorable;

/**
 * A round is the round of games player for a tournament.  Any tournament with more than 2 teams playing will have more than one round
 * @author Ian Graves
 *
 */
public class TournamentRound implements Restorable
{
    private int roundSize;
    private ArrayList<Game> gameList;
    
    public TournamentRound(int size)
    {
	roundSize = size;
	gameList = new ArrayList<Game>();
    }
    
    public void addGame(Game g)
    {
	gameList.add(g);
    }
    
    public ArrayList<Game> getGameList()
    {
	return gameList;
    }
    
    public int getRoundSize()
    {
	return roundSize;
    }
    
    /**
     * Used to get the winner of round if the round size is only 1.  This will only return a non-null value if the round size is 1 and the game is finished
     * @return
     */
    public Player getWinner()
    {
	if(roundSize == 1 && gameList.get(0).getWinner() != null)
	{
	    return gameList.get(0).getWinner();
	}
	return null;
    }
    
    public boolean isFinished()
    {
	for(Game g : gameList)
	{
	    if(g.getWinner() == null)
		return false;
	}
	return true;
    }
    
    /**
     * Takes the winners of this round and returns a whole new round with the winners playing each other.  Returns null if the round is not finished or if the round contains 
     * only two teams
     * @return
     */
    public TournamentRound nextRound()
    {
	// First lets make sure all games have finished
	if(!isFinished())
	    return null;
	ArrayList<Player> winningTeams = new ArrayList<Player>();
	for(Game g : gameList)
	{
	    winningTeams.add(g.getWinner());
	}
	System.out.println(this);
	// Special Case 1, if we have only on game in this round, then the winner will be the winner of the game, so there can be no next round
	if(roundSize == 2)
	{
	    return null;
	}
	// Now that we know all the games have finished make a new round
	TournamentRound newRound = new TournamentRound(roundSize/2);
	for(int i = 0; i < winningTeams.size(); i += 2)
	{
	    Game newGame = new Game(winningTeams.get(i), winningTeams.get(i+1));
	    newRound.addGame(newGame);
	}
	return newRound;
    }
    
    public String toString()
    {
	String s = "Round Size = " + roundSize + "\n";
	for(Game g : gameList)
	{
	    s += g + "\n";
	}
	return s;
    }

    public static TournamentRound readFile(Scanner r)
    {
	int roundSize = Integer.parseInt(r.nextLine());
	TournamentRound round = new TournamentRound(roundSize);
	for(int i = 0; i < roundSize/2; i++)
	{
	    round.addGame(Game.readFile(r));
	}
	return round;
    }

    @Override
    public void saveFile(FileWriter wr) throws IOException
    {
	wr.write(roundSize+"\n");
	for(Game g : gameList)
	{
	    g.saveFile(wr);
	}
    }
}
