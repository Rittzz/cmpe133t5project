package accounts;

import passwordChecker.PasswordManager;


/**
 * This class will eventually contain something...
 * @author Ian Graves
 *
 */
public class AdminAccount extends Account
{
    
    public AdminAccount(String name, String pass)
    {
	super(UserType.ADMIN, name, pass);
    }

}
