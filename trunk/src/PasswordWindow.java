import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import passwordChecker.PasswordManager;
import accounts.UserType;


public class PasswordWindow
{
    JFrame myFrame;
    
    private JComboBox userType;
    private JTextField userField;
    private JPasswordField passField;
    private JButton login;
    private JButton quit;
    
    public PasswordWindow()
    {
	myFrame = new JFrame("Log-in");
	myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Change Later
	myFrame.setLayout(null);
	
	JLabel userList_label = new JLabel("User Type");
	String[] userList = UserType.getLabels();
	userType = new JComboBox(userList);
	userType.setSelectedIndex(0);
	
	JLabel userField_label = new JLabel("User Name");
	userField = new JTextField();
	
	JLabel passField_label = new JLabel("Password");
	passField = new JPasswordField();
	
	login = new JButton("Log in");
	login.addActionListener(new ActionListener() {

	    public void actionPerformed(ActionEvent e)
	    {
		if(checkPassword())
		{
		    // Do something here related to stuff
		}
	    }    
	});
	
	quit = new JButton("Quit");
	quit.addActionListener(new ActionListener() {

	    public void actionPerformed(ActionEvent arg0)
	    {
		System.exit(0);	
	    }    
	});
	
	userList_label.setBounds(20,20,80,20);
	userType.setBounds(100,20,120,20);
	
	userField_label.setBounds(20,50,80,20);
	userField.setBounds(100,50, 120, 20);
	
	passField_label.setBounds(20, 80, 80,20);
	passField.setBounds(100,80, 120, 20);
	
	quit.setBounds(20,110,80,30);
	login.setBounds(140,110,80,30);
	
	myFrame.add(userList_label);
	myFrame.add(userType);
	myFrame.add(passField_label);
	myFrame.add(passField);
	myFrame.add(userField_label);
	myFrame.add(userField);
	myFrame.add(login);
	myFrame.add(quit);
	
	myFrame.setSize(250,190);
	myFrame.setResizable(false);
	myFrame.setVisible(true);
    }
    
    public boolean checkPassword()
    {
	UserType type = UserType.getUserType((String)userType.getSelectedItem());
	//String type = (String)userType.getSelectedItem();
	String name = userField.getText();
	String password = new String(passField.getPassword());
	String passHash = Integer.toString(password.hashCode());
	
	System.out.println("Type = " + type);
	System.out.println("Name = " + name);
	System.out.println("Password = " + password);
	System.out.println("PassHash = " + passHash);
	
	return PasswordManager.checkPassword(type, name, password);
	
	
	
//	// Now lets find the corresponding pair
//	Scanner in = new Scanner(getPasswordFile(type));
//	while(in.hasNext())
//	{
//	    String line = in.nextLine();
//	    String[] split = line.split(" ");
//	    if(split[0].equalsIgnoreCase(name) && split[1].equals(passHash))
//	    {
//		System.out.println("Verified!");
//		return true;
//	    }
//	}
//	System.out.println("Not verfied!");
//	return false;
    }
    
    public FileInputStream getPasswordFile(UserType type)
    {
	try
	{
	    // {"Spectator", "Player", "Tournament Owner", "Admin"};
	    String fileName = null;
	    switch(type) {
		case SPECTATOR:
		    fileName = "spectatorPass.txt";
		    break;
		case PLAYER:
		    fileName = "playerPass.txt";
		    break;
		case ADMIN:
		    fileName = "adminPass.txt";
		    break;
		case ADVERTISER:
		    fileName = "advertisePass.txt";
		    break;
	    }
	    File passFile = new File(fileName);
	    FileInputStream in = new FileInputStream(passFile);
	    return in;
	}
	catch (FileNotFoundException e)
	{
	    System.err.println("FNFE:File for " + type + " does not exist!");
	    return null;
	}
	catch (Exception e)
	{
	    System.err.println("E:File for " + type + " does not exist!");
	    return null;
	}
    }
    
    public static void main(String[] args)
    {
	PasswordWindow w = new PasswordWindow();
    }
}
