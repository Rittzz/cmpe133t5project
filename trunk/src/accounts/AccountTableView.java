package accounts;

import javax.swing.table.AbstractTableModel;

import tournament.DateUtility;
import tournament.Tournament;

/**
 * Use this class to display a list of tournaments the account has.  This provide a wrapper class for the TournamentListHolder that allows easy access to 
 * a JTable display
 * @author Ian Graves
 *
 */
public class AccountTableView extends AbstractTableModel
{
    private TournamentListHolder model;
    
    public AccountTableView(TournamentListHolder h)
    {
	super();
	model = h;
    }
    
    // Table Methods for displaying this in a JTable
    
    /**
     * There are 3 columns for a tournament, the name, the sport, and the start date
     */
    public int getColumnCount()
    {
	return 3;
    }

    public int getRowCount()
    {
	return model.getList().size();
    }

    public String getColumnName(int col)
    {
	switch(col){
	    case 0:
		return "Name";
	    case 1:
		return "Sport";
	    case 2:
		return "Start Date";
	}
	return "ERROR";
    }

    public Object getValueAt(int row, int col)
    {
	Tournament current = model.getList().get(row);
	
	switch(col){
	    case 0:
		return current.getName();
	    case 1:
		return current.getMySport();
	    case 2:
		return DateUtility.toNormalDate(current.getStartDate());
	}
	
	return "NO VALUE FOUND!";
    }
}
