
/**
 * Write a description of class Item here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Item
{
    // instance variables - replace the example below with your own
    private int weight;
    private String description;
    private String name;

    /**
     * Constructor for objects of class Item
     * @param item name, weight, and its description
     */
    public Item(String name, int weight, String description)
    {
        // initialise instance variables
        this.name = name;
        this.weight = weight;
        this.description = description;
    }

    /**
     * Weight of item
     * @return item weight
     */
    public int getWeight()
    {
        return weight;
    }
    
    /**
     * @return description of the item
     */
    public String getDescription()
    {
        // put your code here
        return description;
    }
    
    /**
     * @return name of the item
     */
    public String getName()
    {
        return name;
    }
}
