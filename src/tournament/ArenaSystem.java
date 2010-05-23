package tournament;

import accounts.Account;
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
    
    /**
     * Saves all the data into their respective files.  See the FILE_NAME attribute to where they are actually saved
     */
    public void save()
    {
	PlayerList.getInstance().save();
	TournamentList.getInstance().save();
    }
    
    /**
     * Loads all the files from their respective files
     */
    public void load()
    {
	PlayerList.getInstance().load();
	TournamentList.getInstance().load();
    }
}