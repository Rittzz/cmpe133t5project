package tournament;

public enum TournamentType
{
    SINGLE("Single"),TEAM("Team");
    
    private String label;
    private TournamentType(String s)
    {
	label = s;
    }
    
    public String toLabel()
    {
	return label;
    }
}
