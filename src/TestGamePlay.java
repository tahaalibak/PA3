/**
 * This class serves to test the game play
 * It asks user for difficulty level
 * Creates and initializes level and game
 * Prints level and lets the user play till its end
 */
import java.util.LinkedList;
import java.util.Scanner;

// This is just a short bit of code that runs a test game based on the users preferences
public class TestGamePlay {

	public static void main(String[] args){
		
		Scanner scanner = new Scanner(System.in);
		int difficultyLevel;
		
		//Obtains user's difficulty preferences
		System.out.print("Difficulty Level (easy = 1, medium = 2, hard = 3): ");
		difficultyLevel = scanner.nextInt();
		System.out.println("Initializing Level...");
		
		//Initializes Level
		LinkedList<Object> level = LevelCreator.level(difficultyLevel);
		System.out.println("Level Initialized.");
		GamePlay.printer(level);
		
		//Starts Game
		System.out.println();
		System.out.println("Starting Game:");
		GamePlay.continuePlay(level);
	}	
	
}
