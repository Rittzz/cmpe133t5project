Cmpe133 Arena System README Version 1.0

Important Components

The layout of the project is divided into projects to keep things organized

The most important components are
	-ArenaSystem
		The ArenaSystem controls all saving and loading operations from file.  Use those methods instead of the individual save and load methods 
		from each database or manager class.  Load should be the first thing you call when you open up the application and save should be the last 
		thing you call as you close it.
	-TournamentList
		Contains a list of all the tournaments, can also be displayed in a JTable.  All tournaments in the system are stored here.
	-PlayerList
		Contains a list of all the players in the tournaments.
	-AccountList
		Contains a database of all the accounts, of all types
	-NotificationManager
		Contains a database of all the notifications for the user.
	-PasswordManager
		Used for logging in purposes and for checking the password and such.

Here is a simple step-by-step instructions on some scenarios

LOG-IN WITH AN EXISTING ACCOUNT

1.	Take the fields of text from userName and password fields and run a checkPassword check using PasswordManager
2.	If everything checks out, retrieve the account from accountList like so

		Account act = AccountList.get(userName); 
		// This retrieves the account with all the details, since we know the userType of each account
		// we can correctly cast it to the right account no problem.
		// Here is an example
		if(act.getType == UserType.ADMIN)
			AdminAccount adAct = (AdminAccount)act; // Should have no problems here
			
3.	Once we have the account we can pull various information from it depending on the account

HOW TO CREATE A NEW ACCOUNT

1.	Once you have all your data from the forum, you will know which type of user the user wants to be and it's stats such as 
	password and userName.
2.	Create a brand new account using the corresponding class constructor, this will do two things
		First it will add the account you just created to the AccountList database.
		Second of all it will create an account with the default attributes in place.
3.	Once you create a new account, you are pretty much good to go and can manipulate it as you please.

	EXAMPLE:
			AdminAccount act = new AdminAccount("Bob","passwordhere"); // Automatically added into the AccountDatabase

	NOTE: 	Creating a PlayerAccount automatically creates a player to go with it and adds it the Player Database.  Don't do this 
			unless you know what you are doing.  If you REALLY want to do this, you must also delete the player from the player 
			database as well.  Also if said play is playing in a tournament, you cannot get rid of the existing player unless you 
			dig in deep.

DELETING AN EXISTING ACCOUNT

Use the AccountList.removeAccount() method to do that.  It will do two things, first remove from the account database and second 
will remove it from the password database.

CREATING A TOURNAMENT

1.	Once you have all the necessary information from the user, creating a Tournament is as easy as just creating a new instance of it.  
	See the constructor for Tournament for the parameters involved in that.  Note for the Calendar parameter, DateUtility has some 
	methods you might find useful.
2.	Now this is important, just because you CREATE a Tournament object DOES NOT mean it's in the TournamentList database.  You must add 
	it there manually.
	
See the various attributes of Tournament to what it holds exactly.

	NOTE:	You cannot play a Tournament until it is full!  Once it is full YOU MUST CALL setupTournament() if you wish to seed the first 
			round automatically or manually when you give it a round.

PLAYING A TOURNAMENT

Each tournament consist of one round, once there are only two teams left, the round played will crown the winner.  Each tournament round 
consist of a list of games.  The seed method in the Tournament class will only work if all the games in the round have been played.  If 
this is not the case an exception will be thrown.  You can check to see if a tournament is finished by the isSeedable() method.

	NOTE:	Tournament cannot be deleted unless you purge the system of EVERYTHING.  Too bad

RETRIEVING NOTIFICATIONS FOR A USER

Use the NotificationManager instance to do so.  This is actually pretty easy.  To get all notifications for a account x just do...

	// Retrieves an ArrayList of Messages for the user
	NotificationManager.getInstance().getMessagesFor(x.getName());
	
	// Once you have retrieved this data and would like to remove it all just do...
	NotificationManager.getInstance().remove(theList) // theList being the ArrayList returned earlier
	
Remember the user name is like the primary key for everything.

FINAL WORDS

If I forgot to put a getter and setter for something, call/text me first to see if you can do it.  Some things shouldn't be changed once created 
(like the names of all Tournaments, Accounts, and Ids for Notifications), other can be (like the Date for example).

Good Luck,
Ian Graves
