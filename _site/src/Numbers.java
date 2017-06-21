/**
 * Numbers generates 6 random numbers, of any combination of up to four large {25,50,75,100} 
 * and remaining small [1,10] numbers, and a goal number within the range [100,999]. 
 * User input is used to determine how many large numbers are desired.
 * This generator is designed to follow the game in the game show 'Letters and Numbers'.
 * 
 * Author: Danni Ovens
 * Date: 19/06/17
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Numbers {
	private static ArrayList<Integer> bigNumbers = new ArrayList<Integer>();
	private static ArrayList<Integer> numbers = new ArrayList<Integer>();
	private static int numLarge;
	private static int numSmall;
	private static int goal;
	
	public Numbers(){
	}
	
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("\nHow many large numbers?");
		numLarge = Integer.parseInt(scanner.nextLine());
		if(!verifyNumber()) numLarge = Integer.parseInt(scanner.next());

		scanner.close();
		
		generateList(numLarge);
		printNumbers();
		
		test(); // Run a test on 0-4 large numbers.
	}
	
	public static void generateList(int numLarge) {		
		bigNumbers.addAll(Arrays.asList(25,50,75,100)); // Add all of the large numbers.

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
	 * Randomly chose one of the bigNumbers. Since the game does not allow for repeats, the number
	 * is removed from the list upon being chosen.
	 * @return the random chosen big number.
	 */
	public static int bigRand() {
		// Choose which of the numbers in the 'bigNumbers' list is to be chosen.
		int index = ThreadLocalRandom.current().nextInt(0, bigNumbers.size());
		return bigNumbers.remove(index); // Return and remove this number.	
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
		if(numLarge < 0 || numLarge > 4) {
			System.out.println("Sorry, number out of scope. How many (between 0 and 4) big numbers?");
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
	
	public static void test(){
		numbers.clear();
		bigNumbers.clear();
		
		// Test with no large elements.
		generateList(0);
		assert(numbers.size() == 6);
		assert(largeDuplicates(0));
		numbers.clear();
		bigNumbers.clear();
		
		// Test with one large element.
		generateList(1);
		assert(numbers.size() == 6);
		assert(largeDuplicates(1));
		numbers.clear();
		bigNumbers.clear();
		
		// Test with two large elements.
		generateList(2);
		assert(numbers.size() == 6);
		assert(largeDuplicates(2));
		numbers.clear();
		bigNumbers.clear();
		
		// Test with three large elements.
		generateList(3);
		assert(numbers.size() == 6);
		assert(largeDuplicates(3));
		numbers.clear();
		bigNumbers.clear();
		
		// Test with four large elements.
		generateList(4);
		assert(numbers.size() == 6);
		assert(largeDuplicates(4));
		numbers.clear();
	}
	
	/**
	 * largeDuplicates counts how many large numbers are in the arraylist. If a duplicate does occur
	 * then count will not be the same as num. If there aren't enough large numbers, it will also 
	 * return false.
	 * @param num The amount of large numbers supposedly in the list.
	 * @return True if num is actually the amount of large numbers without duplicates. False otherwise.
	 */
	public static Boolean largeDuplicates(int num) {
		int count = 0;
		if(numbers.contains(25)) count++;
		if(numbers.contains(50)) count++;
		if(numbers.contains(75)) count++;
		if(numbers.contains(100)) count++;
		
		return(count == num);
	}
}