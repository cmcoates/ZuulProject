import java.util.ArrayList;
import java.util.Iterator;

/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player
{
    // instance variables - replace the example below with your own
    private Room currentRoom;
    private Room previousRoom;
    private String name;
    private int weight;
    private ArrayList<Item> playerItems;
    private int timesEaten;

    /**
     * Constructor for objects of class Player
     * @param current room, name of the player, and weight they can carry
     */
    public Player(Room currentRoom, String name, int weight)
    {
        // initialise instance variables
        this.currentRoom = currentRoom;
        this.previousRoom = null;
        this.name = name;
        this.weight = weight;
        //this.playerItems = null; This didn't work, try next line of code, this worked. Arraylist has elements preset with null
        playerItems = new ArrayList<Item>();
        timesEaten = 0;
    }

    /** 
     * Accessor for the current room
     */
    public Room getCurrentRoom()
    {
        return currentRoom;
    }

    /**
     * Updates the room
     * @param newRoom which is the new room the player will go to
     * @return the room which they are now in
     */
    public Room updateRoom(Room newRoom)
    {
        previousRoom = currentRoom;
        currentRoom = newRoom;
        return currentRoom;
    }

    /**
     * Updates the room by going to the previous room
     * @return the previousRoom, or null if there was no previous room
     */
    public Room back()
    {
        if (previousRoom == null) {
            return null;
        }
        else {
            return updateRoom(previousRoom);
        }
    }

    /**
     * Adds items to inventory
     * @param item to add
     */
    public void addItemToInventory(Item item)
    {
        int totalWeight = 0;
        if (playerItems != null) {
            for(Item playerItem : playerItems) {
                totalWeight += playerItem.getWeight();
            }
        }
        if (item.getName().equals("protein")) {
            System.out.println("This is food, you must eat it!");
        }
        else {
            if(totalWeight + item.getWeight() <= weight) {
                playerItems.add(item);

            }
            else {
                System.out.println("Cannot pick up item! You can only carry " + weight + " pounds");
            }
        }
    }

    /**
     * Removes items from inventory
     * @param neededItem, a string of the name of the item
     */
    public void removeItemFromInventory(String neededItem)
    {
        //iterate through the arraylist and then delete item.
        Iterator<Item> it = playerItems.iterator();
        boolean found = false;
        while(it.hasNext())
        {
            Item playerItem = it.next();
            if(playerItem.getName().equals(neededItem)) {

                it.remove();
                System.out.println("Item removed from inventory");
                found = true;
            }
            //else {
            //    System.out.println("Item not in inventory");
            //}
        }
        if(found == false) {
            System.out.println("Item not in inventory");
        }
    }

    /**
     * increases the weight a player can carry
     * @param the neededItem in order to increase weight
     */
    public void increaseWeight(String neededItem)
    {
        if (timesEaten == 0 && neededItem.equals("protein")) {
            weight = weight + 200;
            timesEaten++;
            System.out.println("You can now lift " + weight + " pounds");
        }
        else {
            System.out.println("cannot be eaten!");
        }
    }

    /**
     * Finds if the item is available in your inventory to give to the helper
     * @param itemNeeded to give to the helper
     * @return true or false based on if it is found in your inventory
     */
    public boolean findItem(String itemNeeded)
    {
        boolean found = false;
        if (playerItems != null) {
            for (Item playerItem : playerItems) {
                if (playerItem.getName().equals(itemNeeded)) {
                    return true;
                }
            }
        }
        return found;
    }
    
    /**
     * Gets items and total weight in your inventory
     */
    public void getItems()
    {
        int totalWeight = 0;
        if (playerItems != null) {
            for (Item playerItem : playerItems) {
                System.out.println(playerItem.getName());
                totalWeight += playerItem.getWeight();
            }
            System.out.println("Total item weight in inventory is: " + totalWeight);
        }
        else {
            System.out.println("No items in inventory");
        }
    }
}
