import java.util.Calendar;
import java.util.GregorianCalendar;

import notifications.Message;
import notifications.NotificationManager;
import tournament.ArenaSystem;
import tournament.DateUtility;
import tournament.PlayerList;
import tournament.SinglePlayer;
import tournament.Sport;
import tournament.Team;
import tournament.Tournament;
import tournament.TournamentList;
import tournament.TournamentType;


public class TournamentTester
{
    public static void main(String[] args)
    {
//	// Test Account Creation and Deletion
//	String[] list = {
//		"Tag","casa",
//		"Bob","casa",
//		"Jim","casa"
//		};
//	String userName = "Tag";
//	String password = "casa";
//	
//	for(int i = 0; i < list.length; i += 2)
//	{
//	    System.out.println("Writing...");
//	    PasswordManager.createAccount(UserType.ADMIN, list[i], list[i+1]);
//	    System.out.println("Done Writing now checking...");
//	    boolean result = PasswordManager.checkPassword(UserType.ADMIN, list[i], list[i+1]);
//	    System.out.println("Success = " + result);
//	}
//	
//	System.out.println("Trying to Delete an Entry now..");
//	boolean result = PasswordManager.deleteAccount(UserType.ADMIN, "Tag");
//	System.out.println("Success = " + result);
	
	// Try out basic tournament functions
	populatePlayerList();
	ArenaSystem.getInstance().load();
	//populateNotifications(50);
	System.out.println(PlayerList.getInstance());
	
	Tournament testTournament = new Tournament("Test", DateUtility.parseNormalDate("5/26/1988"), Sport.BASEBALL, TournamentType.SINGLE, 8);
	
	PlayerList pList = PlayerList.getInstance();
	
	for(int i = 0; i < 8; i++)
	{
	    testTournament.addPlayer(pList.get(i));
	}
	
	for(Message m : NotificationManager.getInstance().getAll())
	{
	    System.out.println(m);
	}
	
	TournamentList tList = TournamentList.getInstance();
	testTournament.setupTournament();
	tList.addTournament(testTournament);
	
	System.out.println(tList);
	System.out.println(testTournament);
	
	testTournament.automaticTournament();
	System.out.println("---FINSIHED---");
	System.out.println(testTournament);
	System.out.println(tList);
	
	Calendar date = GregorianCalendar.getInstance();
	System.out.println("Date = " + DateUtility.toFormalDate(date));
	ArenaSystem.getInstance().save();
    }
    
    /**
     * Populates the player database with players to be used in the tournament
     */
    public static void populatePlayerList()
    {
	PlayerList pList = PlayerList.getInstance();
	String[] names = {"Bob","Jim","Jen","Timmy","Cartman","Kyle","Kenny","KenKen"};
	for(int i = 0; i < names.length; i++)
	{
	    pList.addPlayer(new SinglePlayer(names[i]));
	}
	// Create Test Team
	Team myTeam = new Team("The Globe");
	myTeam.addPlayer(pList.get(0));
	myTeam.addPlayer(pList.get(1));
	myTeam.addPlayer(pList.get(2));
	pList.addPlayer(myTeam);
	//ArenaSystem.getInstance().save();
    }
    
    public static void populateNotifications(int amt)
    {
	NotificationManager mn = NotificationManager.getInstance();
	
	for(int i = 0; i < amt; i++)
	{
	    Message m = new Message("Bob","Test Message " + i);
	    mn.addNotification(m);
	}
    }
    
    public static void populateTournamentList(int amt)
    {
	TournamentList tList = TournamentList.getInstance();
	for(int i = 0; i < amt; i++)
	{
	    Tournament testTournament = new Tournament("Test " + i, DateUtility.getDate(0, 11, 2011), Sport.BASEBALL,TournamentType.SINGLE, 8);
	    PlayerList pList = PlayerList.getInstance();
	    for(int j = 0; j < 8; j++)
	    {
		testTournament.addPlayer(pList.get(j));
	    }
	    testTournament.setupTournament();
	    tList.addTournament(testTournament);
	}
	//ArenaSystem.getInstance().save();
    }
}
