package accounts;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import payment.PaymentDatabase;
import tournament.Tournament;

public class AccountList
{
    private AccountList() {}
    
    private static final String FILE_NAME = "AccountDatabase.txt";
    public static final AccountList INSTANCE = new AccountList();
    
    public static AccountList getInstance()
    {
	return INSTANCE;
    }
    
    private ArrayList<Account> list = new ArrayList<Account>();
    
    /**
     * Use this methods to get accounts from the database.  Only create accounts if it's on the account creation screen
     * @param name
     * @return
     */
    public Account get(String name)
    {
	for(Account act : list)
	{
	    if(name.equals(act.getName()))
		return act;
	}
	return null;
    }
    
    public void add(Account act)
    {
	list.add(act);
    }
    
    public void debugPrint()
    {
	System.out.println("AccountList");
	for(Account a : list)
	{
	    System.out.println(a);
	}
    }
    
    public void save()
    {
	try
	{
	    File db = new File(FILE_NAME);
	    FileWriter wr = new FileWriter(db);
	    for (Account t : this.list)
	    {
		t.saveFile(wr);
	    }
	    wr.close();
	}
	catch(IOException e)
	{
	    System.err.println("ERROR SAVING ACCOUNT DATABASE");
	    e.printStackTrace();
	}
    }

    public void load()
    {
	try
	{
	    list.clear();
	    File db = new File(FILE_NAME);
	    Scanner re = new Scanner(db);
	    while(re.hasNext())
	    {
		String className = re.nextLine();
		if(className.equals(AdminAccount.class.toString()))
		    list.add(AdminAccount.readFile(re));
		else if(className.equals(LeagueOwnerAccount.class.toString()))
		    list.add(LeagueOwnerAccount.readFile(re));
		else if(className.equals(AdvertiserAccount.class.toString()))
		    list.add(AdvertiserAccount.readFile(re));
		else if(className.equals(PlayerAccount.class.toString()))
		    list.add(PlayerAccount.readFile(re));
		else if(className.equals(SpectatorAccount.class.toString()))
		    list.add(SpectatorAccount.readFile(re));
	    }
	}
	catch(FileNotFoundException e)
	{
	    System.err.println("File account database not found");
	}
	catch(IOException e)
	{
	    System.err.println("Error Parsing Player database File");
	}
	
    }
}
