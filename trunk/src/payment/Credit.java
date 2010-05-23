package payment;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Scanner;

import accounts.AdminAccount;

public class Credit implements PaymentMethod
{
    private int number;
    public Credit(int number, Calendar expDate)
    {
	this.number = number;
    }
    
    public boolean pay(int amt)
    {
	// If Credit card is valid and we can charge it pay it
	// Since we really aren't going to make you pay it always returns true
	return true;
    }
    
    public static Credit readFile(Scanner r)
    {
	int num = Integer.parseInt(r.nextLine());
	return new Credit(num, null);
    }
    
    public void saveFile(FileWriter wr) throws IOException
    {
	wr.write(getClass()+"\n");
	wr.write(number+"\n");
    }

}
