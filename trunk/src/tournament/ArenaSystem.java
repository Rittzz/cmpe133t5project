package tournament;

import notifications.NotificationManager;
import accounts.Account;
import accounts.AccountList;
import accounts.UserType;

/**
 * The arena system interface.  In order to load and save the databases, use the methods here.  DO NOT change the playerList and tournamentlist methods to public.  
 * They are protected for a reasons
 * @author Ian Graves
 *
 */
public final class ArenaSystem
{
    private static final ArenaSystem INSTANCE = new ArenaSystem();
    
    // Attributes
    
    private Account currentAccount = null;
    
    private ArenaSystem() {}
    
    public static ArenaSystem getInstance()
    {
	return INSTANCE;
    }
    
    public void setUserAccount(Account ac)
    {
	currentAccount = ac;
    }
    
    public Account getAccount()
    {
	return currentAccount;
    }
    
    public UserType returnUserType()
    {
	return currentAccount.getType();
    }
    
    public void purge()
    {
	// Do Nothing for now
    }
    
    /**
     * Saves all the data into their respective files.  See the FILE_NAME attribute in each class to where they are actually saved
     */
    public void save()
    {
	NotificationManager.getInstance().save();
	PlayerList.getInstance().save();
	TournamentList.getInstance().save();
	AccountList.getInstance().save();
    }
    
    /**
     * Loads all the files from their respective files
     */
    public void load()
    {
	NotificationManager.getInstance().load();
	PlayerList.getInstance().load();
	TournamentList.getInstance().load();
	AccountList.getInstance().load();
    }
}
