
import java.util.Arrays;

public class Commands{
	/**
	 * Methods written for each command (pass world and player to each method)
	 *   Each method should return boolean to show if they succeeded or not
	 */
	
	
	/**
	 * An array of all commands that will be shown to the player when they ask for the command list
	 * 
	 * Adding a command here does NOT actually make the command work yet:
	 *  - must also be added in the runCommand
	 *  - must also write the method to implement the command
	 */
	String[] validCommands = {"GO","TAKE","DROP","LOOK", "QUIT","INVENTORY"};
	
	/**
	 * Nothing to initialize
	 */
	public Commands() {
		
	}
	 
	/**
	 * Called by the gameLoop to attempt to run a command
	 * 
	 * @param words - a list of all words in the command
	 * @param player - the player object (gives access to current room and inventory)
	 * @param world - the world object (probably unnecessary for most commands, but included just in case)
	 * 
	 * @return true if the command is successful, otherwise false
	 */
	public boolean runCommand(String[] words, Player player, World world) {
		for(String word: words) {
			
			/**
			 * A switch command is a fancy if/else chain
			 * 
			 * In this case, if word matches one of the cases, that case is run, if not, nothing is run
			 */
			switch (word) {
    			/**
    			 * This is where commands are actually run, any new command needs to be added here
    			 *  - This can also include extra hidden commands that are not on the main list of commands
    			 *  - Use multiple case statements to allow synonyms or shortcuts
    			 * 
    			 *  - If you don't use the return statement in a given case, the code will continue into the next case
    			 */
    
    			case "GO":  
    				return go(words,player,world);
    			case "TAKE": case "GET":   //This runs for the command "TAKE" or "GET"
    				return take(words,player,world);
    			case "LOOK":
    				return look(words,player,world);
    			case "DROP":
    				return drop(words,player,world);
    			case "INVENTORY":
    				return inventory(words,player,world);
    			case "HELP":
    				return help(words,player,world);
    		
    			/**
    			 * Simple commands can just be executed here without a separate method
    			 *  Though this might make it harder to debug!
    			 */
    			case "QUIT": case "Q":
    				player.setPlaying(false);
    				System.out.println("Good-bye!");
    				return true;
			
			}
		}
		return false;
	}

	/**
	 * Here begins the actual commands players can use.  Each command should:
	 *   return a boolean to tell if it has been run properly or not (this may or may not be used in your code)
	 *   print out any results for the player
	 *   For the command to actually run, it needs to have been added to the switch statement above
	 * 
	 * @param words - everything written in the most recent command
	 * @param player - the Player object (with access to his current room)
	 * @param world - the World object (this should not be needed in most cases as the Player has access to current room already)
	 * 
	 * @return - Did the command succeed?
	 */
	
	
	private boolean drop(String[] words, Player player, World world) {
		// Left for the programmer to implement this
		
		return false;
	}

    /**
     * Prints out what the player can see
     * 
     * Currently simply prints the room, whatever that has been defined to be
     * 
     * Needs to be updated
     */
	private boolean look(String[] words, Player player, World world) {
		System.out.println(player.room);
		return true;
	}

	/**
	 * Tries to take an item from the room
	 */
	private boolean take(String[] words, Player player, World world) {
		Room current = player.room;
		Item item = current.getItem(words);     //looks through the words to find an item in the room
		if(item==null) {
			System.out.println("What should I take?");  //Didn't find a valid item to pick up
			return false;
		}
		player.addItem(item);  // Add it to the player's inventory
		current.removeItem(item);  // Remove it from the room
		
		System.out.println("You took the " + item.getName() + ".");
		
		return true;
	}

    /**
     * Attempts to move into another room
     */
	private boolean go(String[] words, Player player, World world) {
		char direction = getDirection(words);  // Get a direction from the list of words
		if(direction == 0) {
			System.out.println("What direction should I go?");  // No valid direction found
			return false;
		}
		Room target = player.room.go.get(direction);  // Is there a room in the given direction?
		
		if (target == null) {
			System.out.println("There doesn't seem to be an exit in that direction.");
			return false;
		}
		
		player.room = target;  // Set the player's room
		return look(words,player,world);  // Display what can be seen
		
	}
	
	/**
	 * Looks through the words for a valid direction
	 * 
	 * Currently the only valid directions are NORTH and SOUTH
	 * 
	 * Also uses abbreviations of N and S
	 * 
	 * Other directions need to be added here.
	 * 
	 * @param words - the list of words to search
	 * 
	 * @return the direction found as a char 
	 */
	private char getDirection(String[] words) {
		String[] directions = {"N","NORTH","S","SOUTH"};
		for (String direction : directions) {
			if (Arrays.asList(words).contains(direction)) {
				return direction.charAt(0);
			}
				
		}	
		return 0;
	}

    /**
     * Help the player out by listing the valid commands
     */
	private boolean help(String[] words, Player player, World world) {
		System.out.println("Here is a list of commands:");
		for(String comm: validCommands)
		{
			System.out.println(comm);
		}
		return true;
	}

    /**
     * List the items the player is holding currently
     */
	private boolean inventory(String[] words, Player player, World world) {
		// TODO Auto-generated method stub
		return false;
	}
}