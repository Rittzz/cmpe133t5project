package payment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import tournament.Player;
import tournament.PlayerList;

/**
 * A database that contains all the user's payment information.  Only stores actual information, no blank info here.  Also 
 * 
 * @author Ian Graves
 *
 */
public class PaymentDatabase
{
    private PaymentDatabase() {}
    
    private static final String FILE_NAME = "PaymentDatabase.txt";
    public static final PaymentDatabase INSTANCE = new PaymentDatabase();
    
    public static PaymentDatabase getInstance()
    {
	return INSTANCE;
    }
    
    public static PaymentMethod readFile(String className, Scanner r)
    {
	if(className.equals(Credit.class))
	{
	    
	}
	return null;
    }
    
    public void save()
    {
	try
	{
	    File db = new File(FILE_NAME);
//	    ArrayList<Account> pList = PlayerList.getInstance().getAll();
	    FileWriter wr = new FileWriter(db);
//	    for (Player p : plist)
//	    {
//		t.saveFile(wr);
//	    }
	    wr.close();
	}
	catch(IOException e)
	{
	    System.err.println("ERROR SAVING TOURNAMENT DATABASE");
	    e.printStackTrace();
	}
    }
}
