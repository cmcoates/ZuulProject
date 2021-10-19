
/**
 * Write a description of class Helper here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Helper
{
    // instance variables - replace the example below with your own
    private Room currentRoom;
    private String name;
    private String currency;
    private boolean key;
    private String hint;

    /**
     * Constructor for objects of class Helper
     * @param the helper name, currency which is what the helper needs to give a hint, a key, and a hint
     */
    public Helper(String name, String currency, boolean key, String hint)
    {
        // initialise instance variables
        this.name = name;
        this.currency = currency;
        key = false;
        this.hint = hint;
    }
    
    /**
     * @return helper name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * The helper currency is what the helper needs in return of giving you a hint
     * @return helper currency
     */
    public String getCurrency()
    {
        return currency;
    }
    
    /**
     * This returns true or false if the helper has received the currency
     * @return key 
     */
    public boolean getKey()
    {
        return key;
    }
    
    /**
     * This changes the value of the key
     */
    public void updateKey()
    {
        key = true;
    }
    
    /**
     * @return hint which gives you a hint on how to proceed
     */
    public String getHint()
    {
        return hint;
    }
}
