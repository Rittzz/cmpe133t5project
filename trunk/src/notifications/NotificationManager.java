package notifications;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import tournament.Tournament;

public class NotificationManager
{
    private static final String FILE_NAME = "NotificationDatabase.txt";
    private static final NotificationManager INSTANCE = new NotificationManager();
    private ArrayList<Message> list = new ArrayList<Message>();
    
    private NotificationManager()
    {
	// Nothing
    }
    
    public static NotificationManager getInstance()
    {
	return INSTANCE;
    }
    
    // Accessors and Mutators
    
    public void addNotification(Message msg)
    {
	list.add(msg);
    }
    
    public void addNotification(String id, String msg)
    {
	list.add(new Message(id,msg));
    }
    
    /**
     * Returns a list of notifications for the userName given.  If no notification are found returns null
     * @param userName
     * @return
     */
    public ArrayList<Message> getMessagesFor(String userName)
    {
	ArrayList<Message> uList = new ArrayList<Message>();
	for(Message m : list)
	{
	    if(m.getId().equals(userName))
	    {
		uList.add(m);
	    }
	}
	return uList.size() > 0 ? uList : null;
    }
    
    public Message get(int index)
    {
	return list.get(index);
    }
    
    public ArrayList<Message> getAll()
    {
	return new ArrayList<Message>(list);
    }
    
    public void remove(ArrayList<Message> removeList)
    {
	for(Message m : removeList)
	{
	    list.remove(m);
	}
    }
    
    public void remove(int index)
    {
	list.remove(index);
    }
    
    public void save()
    {
	try
	{
	    File db = new File(FILE_NAME);
	    FileWriter wr = new FileWriter(db);
	    for (Message t : this.list)
	    {
		t.saveFile(wr);
	    }
	    wr.close();
	}
	catch(IOException e)
	{
	    System.err.println("ERROR SAVING NOTIFICATION DATABASE");
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
		Message t = Message.readFile(re);
		list.add(t);
	    }
	}
	catch(FileNotFoundException e)
	{
	    System.err.println("File player notification database not found");
	}
	catch(IOException e)
	{
	    System.err.println("Error Parsing notification database File");
	}
	
    }
}
