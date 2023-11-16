package edu.iastate.cs228.hw1;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Marcos Medina
 *
 * The ISPBusiness class performs simulation over a grid 
 * plain with cells occupied by different TownCell types.
 *
 */
public class ISPBusiness{

	private static int permaCounter = 0;
	private static int Iteration = 0;

	/**
	 * Returns a new Town object with updated grid value for next billing cycle.
	 * @param tOld: old/current Town object.
	 * @return: New town object.
	 */
	public static Town updatePlain(Town tOld) {
		Town tNew = new Town(tOld.getLength(), tOld.getWidth());
		for (int i = 0; i < tNew.getLength(); i++) {
			for (int j = 0; j < tNew.getWidth(); j++) {
				tNew.grid[i][j] = tOld.grid[i][j].next(tNew);// Creates new plain
			}
		}
		return tNew;
	}
	
	/**
	 * Returns the profit for the current state in the town grid.
	 * @param town
	 * @return
	 */
	public static int getProfit(Town town) {
		int counter = 0;
		for (int i = 0; i < town.getLength(); i++) {
			for (int j = 0; j < town.getWidth(); j++) {
				if (town.grid[i][j].who() == State.CASUAL){
					counter++;
					permaCounter++;
				}
			}
		}
		return counter;
	}
	

	/**
	 *  Main method. Interact with the user and ask if user wants to specify elements of grid
	 *  via an input file (option: 1) or wants to generate it randomly (option: 2).
	 *  
	 *  Depending on the user choice, create the Town object using respective constructor and
	 *  if user choice is to populate it randomly, then populate the grid here.
	 *  
	 *  Finally: For 12 billing cycle calculate the profit and update town object (for each cycle).
	 *  Print the final profit in terms of %. You should print the profit percentage
	 *  with two digits after the decimal point:  Example if profit is 35.5600004, your output
	 *  should be:
	 *
	 *	35.56%
	 *  
	 * Note that this method does not throw any exception, so you need to handle all the exceptions
	 * in it.
	 * 
	 * @param args
	 * 
	 */
	public static void main(String []args){
		Scanner scnr = new Scanner(System.in);
		System.out.println("“How to populate grid (type 1 or 2): 1: from a file. 2: randomly\n" +
				"with seed”");
		int Choice = scnr.nextInt();
		if(Choice == 1){
			System.out.println("Please enter file path ");
			Town i = null;
			try {
				i = new Town(scnr.next());
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e);
			}
			System.out.println(i);
			System.out.println("Profit: " + getProfit(i));
			Iteration += 1;
			System.out.println("After itr: " + Iteration);
			for (int b = 1; b <= 11; b++){
				i = updatePlain(i);
				System.out.println(i);
				System.out.println("Profit: " + getProfit(i));
				Iteration += 1;
				System.out.println("After itr: " + Iteration);
			}
			int max;
			max = i.getLength() * i.getWidth();
			double Per = ((double)permaCounter/(double)max);
			System.out.println(Per);


		}
		else{
			System.out.println("Provide rows, cols and seed integer separated by\n" +
					"spaces: ");
			int length = scnr.nextInt();
			int width = scnr.nextInt();
			int seed = scnr.nextInt();
			Town i = new Town(width, length);
			i.randomInit(seed);
			System.out.println(i);
			System.out.println("Profit: " + getProfit(i));
			Iteration += 1;
			System.out.println("After itr: " + Iteration);
			for (int b = 1; b < 12; b++){
				i = updatePlain(i);
				System.out.println(i);
				System.out.println("Profit: " + getProfit(i));
				Iteration += 1;
				System.out.println("After itr: " + Iteration);
			}
			int max;
			max = i.getLength() * i.getWidth();
			double Per = ((double)permaCounter/(double)max);
			System.out.println(Per);



		}
	}


}
