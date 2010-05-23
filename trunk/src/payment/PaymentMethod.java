package payment;

import java.util.Scanner;

import common.Restorable;


public interface PaymentMethod extends Restorable
{
    /**
     * The account in question pays the amount given in the parameter, how that is done is up to the implementing class.  
     * If the payment goes through true will be returned, false otherwise
     * @param amt
     * @return
     */
    public boolean pay(int amt);
}
