package accounts;

import tournament.Player;
import tournament.Tournament;

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
    }

    public void setTournament(Tournament t)
    {
	myTournament = t;
    }
    
    public Tournament getTournament()
    {
	return myTournament;
    }
}
