package accounts;


/**
 * Contains all attribute related to advertisers such as all the tournaments you are advertising in
 * @author Ian Graves
 *
 */
public class AdvertiserAccount extends Account
{
    
    public AdvertiserAccount(String name, String pass)
    {
	super(UserType.ADVERTISER, name, pass);
    }

}
