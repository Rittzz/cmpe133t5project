import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import tournament.ArenaSystem;
import tournament.PlayerList;
import tournament.TournamentList;


public class SampleWindow
{
    private JFrame frame;
    
    public SampleWindow()
    {
	frame = new JFrame("Sample Table");
	frame.setLayout(new FlowLayout());
	
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	JTable table = new JTable(TournamentList.getInstance());
	//table.setPreferredSize(new Dimension(100,100));
	table.setColumnSelectionAllowed(false);
	table.setRowSelectionAllowed(true);
	
	JTable table2 = new JTable(PlayerList.getInstance());
	//table2.setPreferredSize(new Dimension(100,100));
	table2.setColumnSelectionAllowed(false);
	table2.setRowSelectionAllowed(true);
	
	JScrollPane pane = new JScrollPane(table);
	//pane.setPreferredSize(new Dimension(200,200));
	JScrollPane pane2 = new JScrollPane(table2);
	//pane2.setPreferredSize(new Dimension(200,200));
	
	frame.add(pane,BorderLayout.PAGE_START);
	frame.add(pane2,BorderLayout.PAGE_END);
	frame.pack();
	frame.setVisible(true);
    }
    
    public static void main(String[] args)
    {
	//TournamentTester.populatePlayerList();
	ArenaSystem.getInstance().load();
	System.out.println(PlayerList.getInstance());
	TournamentTester.populateTournamentList(5);
	ArenaSystem.getInstance().save();
	try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    SampleWindow window = new SampleWindow();
                }
            });
        } catch (Exception e) {
            System.err.println("createGUI didn't complete successfully");
        }

    }
}
