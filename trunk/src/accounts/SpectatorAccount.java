package accounts;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


/**
 * Contains Spectator Relevent stuff
 * @author Ian Graves
 *
 */
public class SpectatorAccount extends Account
{
    // GIVE ME IDEAS
    public SpectatorAccount(String name, String pass)
    {
	super(UserType.SPECTATOR, name, pass);
    }

    private SpectatorAccount()
    {
	super(UserType.SPECTATOR);
    }

    public static SpectatorAccount readFile(Scanner r)
    {
	SpectatorAccount act = new SpectatorAccount();
	act.fillFromFile(r); // Gets basic Attributes
	// Nothing else here since spectator doesn't have any attributes
	return act;
    }
    
    public void saveFile(FileWriter wr) throws IOException
    {
	super.saveFile(wr);
    }

}
