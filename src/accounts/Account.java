package accounts;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import passwordChecker.PasswordManager;
import payment.PaymentDatabase;
import payment.PaymentMethod;

import common.Restorable;

/**
 * The Basic account super class, since all accounts have similiar attributes such as username, I just put it in here
 * @author Ian Graves
 *
 */
public abstract class Account implements Restorable
{
    private UserType myType; // The Type of Account -- USED FOR IMPORTANT STUFF
    private String userName; // Name of the Account
    private PaymentMethod payMethod;
    
    public class AccountExistException extends RuntimeException
    {}
    
    /**
     * Used for create a completely new account, updates the password file too.  DO NOT USE TO JUST CREATE ONE JUST CAUSE!!!!!!
     * For getting a pre-existing account, use AccountList.  This will automatically add the new account to the account list
     * @param type
     * @param name
     * @param pass
     * @throws AccountExistException if an account of that name already exist, throws an error.  For now it's unchecked, but may be checked later...
     */
    public Account(UserType type, String name, String pass)
    {
	myType = type;
	userName = name;
	payMethod = null;
	if(!PasswordManager.createAccount(type, name, pass))
	    throw new AccountExistException();
	else
	    AccountList.getInstance().add(this);
    }
    
    /**
     * Used when loading accounts from file, DON'T USE THIS FOR CREATING ACCOUNTS FROM THE ACCOUNT CREATION SCREEN
     * @param type
     * @param name
     */
    protected Account(UserType t)
    {
	myType = t;
	userName = null;
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
    
    /**
     * Set the payment method here
     * @param p
     */
    public void setPaymentMethod(PaymentMethod p)
    {
	payMethod = p;
    }
    
    /**
     * Makes the account pay the system and returns true if it actually happened and false otherwise.  If the 
     * account doesn't have a payment method then returns false.
     * @param amt
     * @return
     */
    public boolean paySystem(int amt)
    {
	if(payMethod != null)
	    return payMethod.pay(amt);
	return false;
    }
    
    protected void fillFromFile(Scanner r)
    {
	userName = r.nextLine();
	myType = UserType.getUserType(r.nextLine()); // Redundant but whatever
	String value = r.nextLine();
	if(!value.equals("NULL"))
	{
	    // We know value is the classname then
	    payMethod = PaymentDatabase.readFile(value, r);
	}
    }
    
    /**
     * Always override this method to ensure saving and loading works but be sure to call super to it unless you know what you 
     * are doing...
     * @param wr
     * @throws IOException
     */
    public void saveFile(FileWriter wr) throws IOException
    {
	wr.write(getClass()+"\n");
	wr.write(userName+"\n");
	wr.write(myType+"\n");
	if(payMethod != null)
	{
	    payMethod.saveFile(wr);
	}
	else
	{
	    wr.write("NULL\n");
	}
    }
    
    public String toString()
    {
	String s = "";
	s += userName + "\n" + myType;
	return s;
    }
}
