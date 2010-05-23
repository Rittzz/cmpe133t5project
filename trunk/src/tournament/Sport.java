package tournament;

import java.util.NoSuchElementException;

public enum Sport
{
    SOCCER("Soccer"),FOOTBALL("Football"),
    BASEBALL("Baseball"),BASKETBALL("Basketball");
    
    private String myLabel;
    private Sport(String l)
    {
	myLabel = l;
    }
    public static Sport parse(String val)
    {
	Sport[] list = Sport.values();
	for(Sport s : list)
	{
	    if(s.toString().equals(val))
		return s;
	}
	throw new NoSuchElementException();
    }
    public String toString()
    {
	return myLabel;
    }
}
