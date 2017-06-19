/**
 * Numbers generates 6 random numbers, of any combination of large {25,50,75,100} and small [1,10], and a goal
 * number [100,999]. 
 * User input is used to determine how many large numbers are desired.
 * This generator is designed to follow the game in the game show 'Letters and Numbers'.
 * 
 * Author: Danni Ovens
 * Date: 19/06/17
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Numbers {
	private final  static int[] bigNumbers = {25, 50, 75, 100};
	private static ArrayList<Integer> numbers = new ArrayList<Integer>();
	private static int numLarge;
	private static int numSmall;
	private static int goal;
	
	public Numbers(){}
	
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("\nHow many large numbers?");
		numLarge = Integer.parseInt(scanner.nextLine());
		if(!verifyNumber()) numLarge = Integer.parseInt(scanner.next());

		scanner.close();
		
		generateList(numLarge);
		printNumbers();
	}
	
	public static void generateList(int numLarge) {
		numSmall = 6 - numLarge; // Calculate the amount of small numbers needed.

		for(int i = 0; i < numSmall; i++) {
			numbers.add(smallRand()); // Add randomly generated small numbers.
		}
		for(int i = 0; i < numLarge; i++) {
			numbers.add(bigRand()); // Add randomly generated large numbers.
		}
		
		goal = ThreadLocalRandom.current().nextInt(100, 999 + 1); // Determine the goal number.
		
		Collections.sort(numbers); // Sort the list of numbers in ascending order.
	}
	
	/**
	 * Generate a random number between 1 and 10
	 * @return the random number
	 */
	public static int smallRand() {
		return ThreadLocalRandom.current().nextInt(1, 10 + 1);
	}
	
	/**
	 * Generate a random number between 100 and 999
	 * @return the random number
	 */
	public static int bigRand() {
		return bigNumbers[ThreadLocalRandom.current().nextInt(0, 3 + 1)];
	}
	
	public static void printNumbers() {
		System.out.print("\nYour numbers are: " );
		for(int num : numbers) System.out.print(num + " ");
		System.out.println("\nYour goal is: " + goal);
	}
	
	/**
	 * verifyNumber ensures that the number of large numbers desired is within the specified range
	 * of 0 to 6.
	 * @return True if numLarge is within [0,6]. False otherwise.
	 */
	public static Boolean verifyNumber() {
		if(numLarge < 0 || numLarge > 6) {
			System.out.println("Sorry, number out of scope. How many (between 0 and 6) big numbers?");
			return false;
		}
		return true;
	}
	
	public ArrayList<Integer> getNumbers() {
		return numbers;
	}
	
	public int getGoal() {
		return goal;
	}
}