import java.util.Set;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game 
{
    private Parser parser;
    private Player player;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createGame();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createGame()
    {
        //Room outside, theater, pub, lab, office, cellar;
        Room outside, beach, ocean, oceancave, restaurant, house, gym, dungeon; 
        Item treasure, helmet, sword, sandals, protein, cake, key;
        Helper rooney, frye;

        // create the rooms
        outside = new Room("outside in the plaza", false);
        beach = new Room("at the beach", false);
        ocean = new Room("in the ocean, don't get too close to the sharks", false);
        oceancave = new Room("in the dark, scary ocean cave", false);
        restaurant = new Room("in the best restaurant in town", false);
        house = new Room("in the oldest, abandoned house in town", false);
        gym = new Room("at the gym to get gains", false);
        dungeon = new Room("secret dungeon in the restaurant", true);
        // initialise room exits
        //setExits(Room north, Room east, Room south, Room west) 
        outside.setExit("north", beach);
        outside.setExit("east", house);
        outside.setExit("south", gym);
        outside.setExit("west", restaurant);

        house.setExit("west", outside);

        restaurant.setExit("east", outside);
        restaurant.setExit("down", dungeon);

        gym.setExit("north", outside);

        dungeon.setExit("up", restaurant);

        ocean.setExit("east", beach);
        ocean.setExit("down", oceancave);

        beach.setExit("west", ocean);
        beach.setExit("south", outside);

        oceancave.setExit("up", ocean);

        // Add items to rooms
        treasure = new Item("treasure", 50, "treasure, mysterious, but valuable");
        oceancave.addItem(treasure);

        helmet = new Item("helmet", 50, "a helmet, which will protect your head");
        house.addItem(helmet);

        cake = new Item("cake", 25, "cake, which you should not eat because it is very old, but you can give it to others");
        house.addItem(cake);

        sandals = new Item("sandals", 25, "sandals will help you walk across the hot sand");
        beach.addItem(sandals);

        key = new Item("key", 10, "a key, which unlocks doors");
        ocean.addItem(key);

        sword = new Item("sword", 200, "a sword, to defeat your enemy");
        dungeon.addItem(sword);

        protein = new Item("protein", 0, "protein, which helps you lift more weight");
        gym.addItem(protein);

        rooney = new Helper("Mr. Rooney", "If you'd like a hint, I will need some cake", false, "You're looking scrawny, you should start lifting");
        outside.addHelper(rooney);
        frye = new Helper("Mr. Frye", "If you'd like a hint, I will need some sandals, the restaurant floor is cold", false, "I need this to unlock my car");
        restaurant.addHelper(frye);

        player = new Player(outside, "Ferris Bueller", 100);  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly exciting adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println("You are " + player.getCurrentRoom().getDescription());

        //Call newley split off rutine 
        printLocationInfo();
    }

    /**
     * Print out the current location and the exits to that room
     */
    private void printLocationInfo()
    {
        System.out.println(player.getCurrentRoom().getExitString());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("look")) {
            look();
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("back")) {
            goBack();
        }
        else if (commandWord.equals("take")) {
            take(command);
        }
        else if (commandWord.equals("drop")) {
            drop(command);
        }
        else if (commandWord.equals("items")) {
            items();
        }
        else if (commandWord.equals("eat")) {
            eat(command);
        }
        else if (commandWord.equals("hint")) {
            hint();
        }
        else if (commandWord.equals("give")) {
            give(command);
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(parser.showCommands());
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     * @param command The command to be processed.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();
        // Try to leave current room.
        Room nextRoom = null;
        nextRoom = player.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            if (nextRoom.getLockExit() == true) {
                if(player.findItem("key")) {
                    nextRoom.unlockExit();
                    player.removeItemFromInventory("key");
                    System.out.println(player.updateRoom(nextRoom).getLongDescription());
                }
                else {
                    System.out.println("This room is locked!");
                }
            }
            else {
                System.out.println(player.updateRoom(nextRoom).getLongDescription());
            }
        }
    }

    /**
     * Prints the description and contents of a room
     */
    private void look()
    {
        System.out.println(player.getCurrentRoom().getLongDescription()); 
    }

    /**
     * This takes the player to the previous room
     */
    private void goBack()
    {
        Room newRoom = player.back();
        if (newRoom == null) {
            System.out.println("Error! There is no previous room!");
        }
        else {
            System.out.println(newRoom.getLongDescription());
        }
    }

    /**
     * This method picks up an available item
     */
    private void take(Command command)
    {
        String itemWord = command.getSecondWord();
        if (player.getCurrentRoom().getItem(itemWord) != null) {
            //Add condition if the item weighs to much, print this is too heavy
            player.addItemToInventory(player.getCurrentRoom().getItem(itemWord));
        }
        else {
            System.out.println("No such item in this room!");
        }
    }

    /**
     * This method drops an item the player is carrying
     */
    private void drop(Command command)
    {
        String itemWord = command.getSecondWord();
        player.removeItemFromInventory(itemWord);
    }

    /**
     * Prints the items a player has
     */
    private void items()
    {
        player.getItems();
    }

    /**
     * Allows for a player to eat items
     */
    private void eat(Command command)
    {
        String eatWord = command.getSecondWord();
        player.increaseWeight(eatWord);
    }

    /**
     * This method prints a hint that a helper gives 
     */
    private void hint()
    {
        Helper helperPresent = player.getCurrentRoom().getHelper();
        if (helperPresent != null) {
            boolean hasItem = player.getCurrentRoom().getHelper().getKey();
            if(hasItem == false) {
                System.out.println(helperPresent.getName() + ": " + helperPresent.getCurrency());
            }
            else {
                System.out.println(helperPresent.getName() + ": " + helperPresent.getHint());
            }
        }
        else {
            System.out.println("There is no helper in this room!");
        }
    }

    /** 
     * This method allows you to give the helper a item in return for their help
     * @param command which is the item to give
     */
    private void give(Command command)
    {
        String itemWord = command.getSecondWord();
        String name = null;
        if (player.getCurrentRoom().getHelper() != null) {
            name = player.getCurrentRoom().getHelper().getName();
        }
        else {
            System.out.println("Helper not present");
        }
        boolean found = player.findItem(itemWord);
        if (found == true) {
            player.removeItemFromInventory(itemWord);
            player.getCurrentRoom().getHelper().updateKey();
            System.out.println("Ask " + name + " for a hint");
        }
        else {
            System.out.println("Item not available to give to helper");
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

}
