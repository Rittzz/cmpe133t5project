package accounts;

import passwordChecker.PasswordManager;
import payment.PaymentMethod;

/**
 * The Basic account super class, since all accounts have similiar attributes such as username, I just put it in here
 * @author Ian Graves
 *
 */
public abstract class Account
{
    private UserType myType; // The Type of Account -- USED FOR IMPORTANT STUFF
    private String userName; // Name of the Account
    private PaymentMethod payMethod;
    
    /**
     * Creates an account that already exist for the current session by pulling the data from the database.  If the password is incorrect 
     * or the given username doesn't exist an exception will be thrown.  Notice that the exception is a checked exception, so go go try catch
     * @param type
     * @param name
     * @param pass
     * @throws IncorrectPasswordException
     */
    public Account(UserType type, String name, String pass)
    {
	myType = type;
	userName = name;
	payMethod = null;
    }
    
    // ACCESSORS
    
    public UserType getType()
    {
	return myType;
    }
    
    public String getName()
    {
	return userName;
    }
    
    /**
     * Creates an account for the user in the password file for the usertype
     * @param type
     * @param name
     * @param pass
     */
    public static void createAccount(UserType type, String name, String pass)
    {
	PasswordManager.createAccount(type, name, pass);
    }
    
    /**
     * Changes the password for the account
     * @param newPass
     */
    public void changePassword(String newPass)
    {
	PasswordManager.deleteAccount(getType(), userName);
	PasswordManager.createAccount(getType(), userName, newPass);
    }
}
