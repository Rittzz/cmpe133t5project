package accounts;

import java.util.NoSuchElementException;

/**
 * All the different types of users!
 * @author Ian Graves
 *
 */
public enum UserType
{
    SPECTATOR("Spectator"),PLAYER("Player"),
    ADMIN("Admin"),ADVERTISER("Advertiser"),
    LEAGUE_OWNER("League Owner");
    
    private String label;
    private UserType(String l)
    {
	label = l;
    }
    
    public String getLabel()
    {
	return label;
    }
    
    public String toString()
    {
	return label;
    }
    
    public static UserType getUserType(String s)
    {
	UserType[] list = UserType.values();
	
	for(UserType u : list)
	{
	    if(u.getLabel().equals(s))
		return u;
	}
	
	throw new NoSuchElementException();
    }
    
    public static String[] getLabels()
    {
	UserType[] list = UserType.values();
	String[] values = new String[list.length];
	for(int i = 0; i < values.length; i++)
	{
	    values[i] = list[i].getLabel();
	}
	return values;
    }
}
