import passwordChecker.PasswordManager;
import tournament.ArenaSystem;
import accounts.Account;
import accounts.AccountList;
import accounts.AdminAccount;
import accounts.LeagueOwnerAccount;
import accounts.PlayerAccount;
import accounts.SpectatorAccount;


public class AccountTester
{
    public static void main(String[] args)
    {
	PasswordManager.purge();
	populateAccounts(50);
	ArenaSystem.getInstance().save();
	//AccountList.getInstance().debugPrint();
	ArenaSystem.getInstance().load();
	AccountList.getInstance().debugPrint();
    }
    
    public static void populateAccounts(int max)
    {
	AccountList inst = AccountList.getInstance();
	String name = "Joe";
	String pass = "casa";
	for(int i = 0; i < max; i++)
	{
	    Account act;    
	    // Do some random stuff here
	    double rand = Math.random();
	    if(rand < 0.25)
		act = new AdminAccount(name+i,pass);
	    else if(rand < 0.5)
		act = new SpectatorAccount(name+i,pass);
	    else if(rand < 0.75)
		act = new LeagueOwnerAccount(name+i,pass);
	    else
		act = new PlayerAccount(name+i,pass);
	    
	    inst.add(act);
	}
    }
}
