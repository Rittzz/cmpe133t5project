package accounts;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import tournament.Tournament;
import tournament.TournamentList;


/**
 * Contains all attribute related to advertisers such as all the tournaments you are advertising in
 * @author Ian Graves
 *
 */
public class AdvertiserAccount extends Account implements TournamentListHolder
{
    private String banner; // Our banner will simply be a text line
    private ArrayList<Tournament> advList = new ArrayList<Tournament>();
    
    public AdvertiserAccount(String name, String banner, String pass)
    {
	super(UserType.ADVERTISER, name, pass);
	this.banner = banner;
    }
    
    private AdvertiserAccount()
    {
	super(UserType.ADVERTISER);
    }
    
    public void setBanner(String b)
    {
	banner = b;
    }
    
    public String getBanner()
    {
	return banner;
    }
    
    /**
     * Returns the underlying list for the tournaments this guy owns.  Manipulating this list will manipulate the underlying data.
     * @return
     */
    public ArrayList<Tournament> getList()
    {
	return advList;
    }
    
    public void addTournament(Tournament t)
    {
	advList.add(t);
    }

    public static AdvertiserAccount readFile(Scanner r)
    {
	AdvertiserAccount act = new AdvertiserAccount();
	act.fillFromFile(r); // Gets basic Attributes
	act.banner = r.nextLine();
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
	wr.write(banner+"\n");
	wr.write(advList.size()+"\n");
	for(Tournament t : advList)
	{
	    wr.write(t.getName()+"\n");
	}
    }

}
