package accounts;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import tournament.Tournament;
import tournament.TournamentList;


/**
 * Contains all attribute related to advertisers such as all the tournaments you are advertising in
 * @author Ian Graves
 *
 */
public class AdvertiserAccount extends Account
{
    private List<Tournament> advList = new ArrayList<Tournament>();
    public AdvertiserAccount(String name, String pass)
    {
	super(UserType.ADVERTISER, name, pass);
    }
    
    public void addTournament(Tournament t)
    {
	advList.add(t);
    }
    
    private AdvertiserAccount()
    {
	super(UserType.ADVERTISER);
    }

    public static AdvertiserAccount readFile(Scanner r)
    {
	AdvertiserAccount act = new AdvertiserAccount();
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
	wr.write(advList.size()+"\n");
	for(Tournament t : advList)
	{
	    wr.write(t.getName()+"\n");
	}
    }

}
