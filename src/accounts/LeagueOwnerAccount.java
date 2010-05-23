package accounts;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import tournament.Tournament;
import tournament.TournamentList;


/**
 * LeagueOwner account should contain a list of tournaments that is own by the league owner.
 * @author Ian Graves
 *
 */
public class LeagueOwnerAccount extends Account
{
    private ArrayList<Tournament> myTournaments;
    public LeagueOwnerAccount(String name, String pass)
    {
	super(UserType.LEAGUE_OWNER, name, pass);
	myTournaments = new ArrayList<Tournament>();
    }
    
    public void addTournament(Tournament t)
    {
	myTournaments.add(t);
    }
    
    private LeagueOwnerAccount()
    {
	super(UserType.LEAGUE_OWNER);
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
	    act.addTournament(t);
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

}
