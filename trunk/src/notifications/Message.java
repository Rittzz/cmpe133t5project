package notifications;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import common.Restorable;

public class Message implements Restorable
{
    private String id; // Id basically means who the notification is for, which user
    private String message; // The actual contents of the message
    
    // Could possible include a timestamp in here, but too late for that!
    
    public Message(String id, String msg)
    {
	this.id = id;
	message = msg;
    }
    
    public void setMessage(String msg)
    {
	message = msg;
    }
    
    public String getId()
    {
	return id;
    }
    
    public String getMessage()
    {
	return message;
    }
    
    public String toString()
    {
	return id+":"+message;
    }

    public boolean equals(Object o)
    {
	if(o.getClass() != this.getClass())
	    return false;
	
	Message m = (Message)o;
	if(id.equals(m.id) && message.equals(m.message))
	    return true;
	return false;
    }
    
    public static Message readFile(Scanner sc)
    {
	String id = sc.nextLine();
	String msg = "";
	for(String s = sc.nextLine(); !s.equals("</sm>"); s = sc.nextLine())
	{
	    msg += s;
	}
	return new Message(id, msg);
    }
    
    public void saveFile(FileWriter wr) throws IOException
    {
	wr.write(id+"\n");
	wr.write(message+"\n");
	wr.write("</sm>\n");
    }
}
