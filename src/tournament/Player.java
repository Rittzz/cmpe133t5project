package tournament;

/**
 * Composite Pattern Interface
 * @author Ian Graves
 *
 */
public interface Player extends Restorable
{
    public int getWinCount();
    
    public int getLossCount();
    
    public void addWin();
    
    public void addLoss();
    
    public String getName();
    
    public String getStats();
}
