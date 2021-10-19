import java.util.HashMap;
import java.util.Set; 

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class Room 
{
    public String description;
    private HashMap<String, Room> exits;
    private HashMap<String, Item> items;
    private Helper helper;
    private boolean locked;
    //private HashMap<String, ArrayList<Room>> exits;


    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description, boolean locked) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        items = new HashMap<String, Item>();
        helper = null;
        this.locked = locked;
    }

    /**
     * @param direction to go in and a room exit
     */
    public void setExit(String direction, Room exit)
    {
        exits.put(direction, exit);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @param direction to go in
     * @return a room 
     */
    public Room getExit(String direction)
    {
        return exits.get(direction);
    }

    /**
     * @return a string for the exit name
     */
    public String getExitString()
    {

        Set<String> strExits = exits.keySet();
        String exitString = " Exits: ";
        for(String str : exits.keySet())
        {
            exitString +=str;
            exitString += " ";
        }

        return exitString;
    }

    /**
     * @return a string of tha long description of the room
     */
    public String getLongDescription()
    {
        String longDescription = "";

        longDescription = "You are " + description;

        longDescription += getExitString();
        

        if (items.size() != 0) {
            for(String str : items.keySet())
            {
                Item item = items.get(str);
                longDescription += "\n The room contains " + item.getDescription();
 
            }
        }
        if (helper != null) {
            longDescription += "\n The room contains a helper: " + helper.getName();
        }
        return longDescription; 
    }
    
    /**
     * @param item to add to the room
     */
    public void addItem(Item item)
    {
        items.put(item.getName(), item);
    }
    
    /**
     * @param the item name as a string
     * @return the Item
     */
    public Item getItem(String item)
    {
        return items.get(item);
    }
    
    /**
     * This initializes the null helper to a filled helper
     * @param help as a Helper
     */
    public void addHelper(Helper help)
    {
        helper = help;
    }
    
    /**
     * Lock exit
     */
    public void unlockExit()
    {
        locked = false;
    }
    
    /**
     * @return boolean to see if exit is locked
     */
    public boolean getLockExit()
    {
        return locked;
    }
    
    /**
     * @return helper, a Helper
     */
    public Helper getHelper()
    {
        return helper;
    }
}
