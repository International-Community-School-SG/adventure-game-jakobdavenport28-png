import java.util.Scanner;


public class Quest
{
	/**
    * A Scanner object for dealing with all input from the user
    */
    static Scanner input = new Scanner(System.in);
    
    /**
    * The Player object that will represent our player in the world.
    */
	static private Player player;
	
    /**
    * The World object that will store all of our rooms and items.
    */
	static private World world;
	
    /**
    * The Commands object that allows the player to interact with the world.
    */
	static private Commands commands;
	
	/**
     * The setup of the game, initialize all variables and start the game loop.
     */
    public static void main(String[] args)
    {
        // This initializes the world, the player, and the commands, then starts the game running.
        
        world = new World();
        
        player = new Player("Fred");
        player.room = world.start;
    	commands = new Commands();
    	gameLoop();
    }
    
    /**
     * The main loop of the game.  
     * 
     * Loop repeats until the player object is no longer playing.
    */
    public static void gameLoop() {
    	
    	String prompt = "What would you like to do next? If you do not know the commands type help.";
    	while(player.isPlaying()){
    		String[] order = getInput(prompt);
    		prompt = "What would you like to do next?";
    		boolean success = commands.runCommand(order, player, world);
    		if(!success) {
    			// This may double up errors (for example with the take command
    			System.out.println("Sorry, I don't understand you.");
    		}
    		
    	}
    }
    
    /**
     * A helper function to get input from the player.                           
     *
     * Displays a prompt for the player to respond to, then parses the answer into individual words and returns an array.
     * 
     * @param  prompt   String that will be displayed to get input from the user.          
     * @return          An array of Strings that stores all the words in the response.
     */
    private static String[] getInput(String prompt) {
		
    	System.out.println(prompt);
    	String sentence = input.nextLine();
    	sentence = sentence.toUpperCase();
    	String[] order = getWords(sentence);
    	
		return order;
	}

	/**
     * A helper function to split an input into individual words.                           
     *
     * Splits a string into words, currently only uses spaces as delimiters and leaves all punctuation.
     * 
     * @param  sentence   String to be parsed.          
     * @return          An array of Strings that stores all the words in the sentence.
     */
    public static String[] getWords(String sentence){
    	String[] words = sentence.split(" ");
    	return words;
    }
    


}