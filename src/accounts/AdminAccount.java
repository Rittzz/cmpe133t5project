package accounts;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import tournament.SinglePlayer;


/**
 * This class will eventually contain something...
 * @author Ian Graves
 *
 */
public class AdminAccount extends Account
{
    // Admin Account doesn't really have any attributes
    // Let me know if you can think of some
    
    public AdminAccount(String name, String pass)
    {
	super(UserType.ADMIN, name, pass);
    }
    
    private AdminAccount()
    {
	super(UserType.ADMIN);
    }

    public static AdminAccount readFile(Scanner r)
    {
	AdminAccount act = new AdminAccount();
	act.fillFromFile(r); // Gets basic Attributes
	// Nothing else here since Admin doesn't have any attributes
	return act;
    }
    
    public void saveFile(FileWriter wr) throws IOException
    {
	super.saveFile(wr);
	// Nothing else to save here for now
    }

}
