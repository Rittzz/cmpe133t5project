package passwordChecker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import accounts.UserType;

/**
 * Does the real work in determining whether or not a password is valid, all data is stored in text.
 * For passwords random salting IS used, so screw you if you don't know what I'm talking about
 * @author Ian Graves
 *
 */
public class PasswordManager
{  
    private PasswordManager()
    {
	// LALALALAA UTILITY CLASS
    }

    /**
     * Returns the password file name for the give usertype
     * @param u
     * @return
     */
    private static String getPasswordFileName(UserType u)
    {
	final String EXTRA_FILE_EXT = "";
	String FILE_NAME = EXTRA_FILE_EXT;
	switch(u){
	    case ADMIN:
		FILE_NAME += ControllerPassword.FILE_NAME;
		break;
	    case SPECTATOR:
		FILE_NAME += SpectatorPassword.FILE_NAME;
		break;
	    case PLAYER:
		FILE_NAME += PlayerPassword.FILE_NAME;
		break;
	    case ADVERTISER:
		FILE_NAME += AdvertiserPassword.FILE_NAME;
		break;
	}
	if(!FILE_NAME.equals(EXTRA_FILE_EXT))
	    return FILE_NAME;
	else
	    throw new IllegalArgumentException("The Usertype " + u + " does not have a password file");
    }

    /**
     * Checks to see if the password is valid for the userName and userType
     * @param u
     * @param userName
     * @param password
     * @return true if the password and username match up!
     */
    public static boolean checkPassword(UserType u, String userName, String password)
    {
	String FILE_NAME = getPasswordFileName(u);
	try
	{
	    File passFile = new File(FILE_NAME);
	    FileInputStream fileIn = new FileInputStream(passFile);

	    Scanner in = new Scanner(fileIn);
	    while(in.hasNext())
	    {
		// Little type safty here
		String line = in.nextLine();
		if(line.trim().equals(""))
		    continue; // STUPID AND DUMB AND STUPID
		String[] split = line.split(" ");
		String newHash = Integer.toString((password+split[2]).hashCode());
		String passHash = split[1];
		if(split[0].equalsIgnoreCase(userName) && passHash.equals(newHash)) // HASING IS FUN
		{
		    System.out.println("Verified!"); // DEBUG
		    return true; // HAXOR
		}
	    }
	    System.out.println("Not verfied!");
	    return false;
	}
	catch (FileNotFoundException e)
	{
	    System.err.println("FNFE:File for " + u + " does not exist!");
	    return false;
	}
	catch (Exception e)
	{
	    System.err.println("E:Error parsing file for " + u);
	    return false;
	}
    }
    
    /**
     * Returns true if the userName exists
     * @param u usertype
     * @param userName the name of the user
     * @return
     */
    public static boolean accountExists(UserType u, String userName)
    {
	String FILE_NAME = getPasswordFileName(u);
	try
	{
	    File passFile = new File(FILE_NAME);
	    // First make sure the userName doesn't already exist!
	    FileInputStream fileIn = new FileInputStream(passFile);

	    Scanner in = new Scanner(fileIn);
	    while(in.hasNext())
	    {
		String line = in.nextLine();
		String[] split = line.split(" ");
		if(split[0].equalsIgnoreCase(userName))
		{
		    fileIn.close();
		    return true; // Found the userName we good
		}
	    }
	    fileIn.close();
	}
	catch (IOException e)
	{
	    System.err.println("Error account not created!");
	}
	return false;
    }

    /**
     * Creates an account at the end of the password file
     * @param u
     * @param userName
     * @param password NOT HASHED!
     * @return if an account was created returns true, false otherwise
     */
    public static boolean createAccount(UserType u, String userName, String password)
    {
	String FILE_NAME = getPasswordFileName(u);
	try
	{
	    File passFile = new File(FILE_NAME);
	    // First make sure the userName doesn't already exist!
	    FileInputStream fileIn = new FileInputStream(passFile);

	    Scanner in = new Scanner(fileIn);
	    while(in.hasNext())
	    {
		String line = in.nextLine();
		String[] split = line.split(" ");
		if(split[0].equalsIgnoreCase(userName))
		{
		    return false; // Repeat userName, didn't create
		}
	    }
	    // Now that we know it doesn't exist, write the new account
	    FileWriter wr = new FileWriter(passFile, true);
	    int salt = (int) (Math.random()*50000);
	    String combined = password + salt;
	    wr.write("\n"+userName + " " + combined.hashCode() + " " + salt);
	    wr.close();
	    return true;
	}
	catch (IOException e)
	{
	    System.err.println("Error account not created!");
	}
	return false;
    }

    /**
     * Deletes an account given the username and type
     * @param u
     * @param userName
     * @return
     */
    public static boolean deleteAccount(UserType u, String userName)
    {
	// General Methodolgy here is to rewrite the entire file without the new entry
	boolean flag = false;
	String FILE_NAME = getPasswordFileName(u);
	try
	{
	    File passFile = new File(FILE_NAME);
	    // First make sure the userName doesn't already exist!
	    FileInputStream fileIn = new FileInputStream(passFile);
	    ArrayList<String> data = new ArrayList<String>();
	    Scanner in = new Scanner(fileIn);
	    while(in.hasNext())
	    {
		String line = in.nextLine();
		String[] split = line.split(" ");
		if(split[0].equalsIgnoreCase(userName))
		{
		    flag = true;
		}
		else
		{
		    if(split.length > 0)
			data.add(line);
		}
	    }
	    if(!flag)
	    {
		System.out.println("Stop Early"); // OMG LINEAR ATTACK
		return flag;
	    }
	    System.out.println(data);
	    FileWriter wr = new FileWriter(passFile,false); // Now re-write the entire file, so bad so bad
	    for(String s : data)
	    {
		wr.write(s+"\n");
	    }
	    wr.close();
	}
	catch (IOException e)
	{
	    System.err.println("Error account not deleted!");  // MAybe?
	}
	return flag;
    }
    
    /**
     * Changes the password for the given user given their type and old password to the new password
     * @param u the usertype of the user
     * @param userName the name of the user
     * @param oldPass the old password
     * @param newPass the new password
     * @return if the password change was successful, returns true
     */
    public static boolean changePassword(UserType u, String userName, String oldPass, String newPass)
    {
	if(checkPassword(u, userName, oldPass))
	{
	    // Then we know we are good to change
	    deleteAccount(u, userName);
	    createAccount(u, userName, newPass);
	    return true;
	}
	return false;
    }
}
