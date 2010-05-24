package accounts;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import tournament.Tournament;
import tournament.TournamentList;

import common.Observer;


/**
 * LeagueOwner account should contain a list of tournaments that is own by the league owner.
 * @author Ian Graves
 *
 */
public class LeagueOwnerAccount extends Account implements TournamentListHolder, Observer<Tournament>
{
    private ArrayList<Tournament> myTournaments;
    public LeagueOwnerAccount(String name, String pass)
    {
	super(UserType.LEAGUE_OWNER, name, pass);
	myTournaments = new ArrayList<Tournament>();
    }
    
    /**
     * Convenience method for adding a tournament to the list
     * @param t
     */
    public void addTournament(Tournament t)
    {
	myTournaments.add(t);
	t.addObserver(this);
    }
    
    private LeagueOwnerAccount()
    {
	super(UserType.LEAGUE_OWNER);
	myTournaments = new ArrayList<Tournament>();
    }
    
    /**
     * Returns the underlying list for the tournaments this guy owns.  Manipulating this list will manipulate the underlying data.  Note: 
     * if you wish to ADD a tournament to the list use the addTournament method in this class.  It handles setting up observers for it's 
     * tournaments.
     * @return
     */
    public ArrayList<Tournament> getList()
    {
	return myTournaments;
    }

    public static LeagueOwnerAccount readFile(Scanner r)
    {
	LeagueOwnerAccount act = new LeagueOwnerAccount();
	act.fillFromFile(r); // Gets basic Attributes
	// Specific stuff here
	int size = Integer.parseInt(r.nextLine());
	for(int i = 0; i < size; i++)
	{
	    Tournament t = TournamentList.getInstance().get(r.nextLine());
	    act.myTournaments.add(t);
	}
	return act;
    }
    
    public void saveFile(FileWriter wr) throws IOException
    {
	super.saveFile(wr);
	wr.write(myTournaments.size()+"\n");
	for(Tournament t : myTournaments)
	{
	    wr.write(t.getName()+"\n");
	}
    }

    public void alert(Tournament obj)
    {
	// Notify the league owner that something has happened
    }

}
