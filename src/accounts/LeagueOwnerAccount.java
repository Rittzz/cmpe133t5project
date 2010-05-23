package accounts;

import java.util.ArrayList;

import tournament.Tournament;


/**
 * LeagueOwner account should contain a list of tournaments that is own by the league owner.
 * @author Ian Graves
 *
 */
public class LeagueOwnerAccount extends Account
{
    ArrayList<Tournament> myTournaments;
    public LeagueOwnerAccount(String name, String pass)
    {
	super(UserType.PLAYER, name, pass);
	myTournaments = new ArrayList<Tournament>();
    }

}
