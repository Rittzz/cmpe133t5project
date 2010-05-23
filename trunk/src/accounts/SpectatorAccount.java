package accounts;


/**
 * Contains Spectator Relevent stuff
 * @author Ian Graves
 *
 */
public class SpectatorAccount extends Account
{
    
    public SpectatorAccount(String name, String pass)
    {
	super(UserType.SPECTATOR, name, pass);
    }

}
