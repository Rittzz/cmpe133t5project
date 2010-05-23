package accounts;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import tournament.Player;
import tournament.PlayerList;
import tournament.SinglePlayer;
import tournament.Tournament;
import tournament.TournamentList;

/**
 * Generic player account, for purpose of seperating duties, a player account and an actual player are two different things.
 * Using composition however we can make this work.  As of now, and in the future, a player can only participate in one tournament at a time
 * @author Ian Graves
 *
 */
public class PlayerAccount extends Account
{
    Player myPlayer;
    Tournament myTournament;
    
    public PlayerAccount(String name, String pass)
    {
	super(UserType.PLAYER, name, pass);
	// PlayerAccount means a new player!
	myPlayer = new SinglePlayer(name);
	PlayerList.getInstance().addPlayer(myPlayer);
    }

    public void setTournament(Tournament t)
    {
	myTournament = t;
    }
    
    public Tournament getTournament()
    {
	return myTournament;
    }
    
    private PlayerAccount()
    {
	super(UserType.PLAYER);
    }

    public static PlayerAccount readFile(Scanner r)
    {
	PlayerAccount act = new PlayerAccount();
	act.fillFromFile(r); // Gets basic Attributes
	// Specific stuff here
	String pName = r.nextLine();
	String tName = r.nextLine();
	Player p = !pName.equals("NULL") ? PlayerList.getInstance().get(pName) : null;
	Tournament t = !tName.equals("NULL") ? TournamentList.getInstance().get(tName) : null;
	act.myTournament = t;
	act.myPlayer = p;
	return act;
    }

    public void saveFile(FileWriter wr) throws IOException
    {
	super.saveFile(wr);
	// I'm fancy
	wr.write(myPlayer!=null?myPlayer.getName()+"\n":"NULL\n");
	wr.write(myTournament!=null?myTournament.getName()+"\n":"NULL\n");
    }
}
