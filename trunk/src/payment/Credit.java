package payment;

import java.util.Calendar;

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

}
