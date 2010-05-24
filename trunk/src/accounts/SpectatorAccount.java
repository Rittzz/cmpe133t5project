package accounts;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import tournament.Tournament;
import tournament.TournamentList;


/**
 * Contains Spectator Relevent stuff
 * @author Ian Graves
 *
 */
public class SpectatorAccount extends Account implements TournamentListHolder
{
    private ArrayList<Tournament> favList;
    public SpectatorAccount(String name, String pass)
    {
	super(UserType.SPECTATOR, name, pass);
	favList = new ArrayList<Tournament>();
    }

    private SpectatorAccount()
    {
	super(UserType.SPECTATOR);
    }
    
    public void addTournament(Tournament t)
    {
	favList.add(t);
    }
    
    public ArrayList<Tournament> getList()
    {
	return favList;
    }

    public static SpectatorAccount readFile(Scanner r)
    {
	SpectatorAccount act = new SpectatorAccount();
	act.fillFromFile(r); // Gets basic Attributes
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
	wr.write(favList.size()+"\n");
	for(Tournament t : favList)
	{
	    wr.write(t.getName()+"\n");
	}
    }

}
