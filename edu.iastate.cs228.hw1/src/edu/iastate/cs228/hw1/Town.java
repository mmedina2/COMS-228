package edu.iastate.cs228.hw1;

import java.awt.geom.RectangularShape;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;




/**
 *  @author <<Marcos Medina>>
 *
 */
public class Town {

	private static int length;
	private static int width;  //Row and col (first and second indices)
	public  TownCell[][] grid;


	/**
	 * Constructor to be used when user wants to generate grid randomly, with the given seed.
	 * This constructor does not populate each cell of the grid (but should assign a 2D array to it).
	 *
	 * @param length
	 * @param width
	 */
	public Town(int length, int width) {
		this.length = length;
		this.width = width;
		grid = new TownCell[length][width];

	}

	/**
	 * Constructor to be used when user wants to populate grid based on a file.
	 * Please see that it simply throws FileNotFoundException exception instead of catching it.
	 * Ensure that you close any resources (like file or scanner) which is opened in this function.
	 *
	 * @param inputFileName
	 * @throws FileNotFoundException
	 */
	public Town(String inputFileName) throws FileNotFoundException {
		File file = new File(inputFileName);
		Scanner scnr = new Scanner(file);
		this.length = scnr.nextInt();
		this.width = scnr.nextInt();
		grid = new TownCell[length][width];
		TownCell cell = null;
		while (scnr.hasNextLine()){
			for (int a = 0; a < length; a++) {
				for (int b = 0; b < width; b++) {
					String now = scnr.next();
					if (now.equals("O")) {
						cell = new Outage(this, a, b);
					} else if (now.equals("S")) {
						cell = new Streamer(this, a, b);
					} else if (now.equals("C")) {
						cell = new Casual(this, a, b);
					} else if (now.equals("R")) {
						cell = new Reseller(this, a, b);
					} else if (now.equals("E")) {
						cell = new Empty(this, a, b);
					}
					grid[a][b] = cell;
				}
			}
		}
		scnr.close();
		}


	/**
	 * Returns width of the grid.
	 *
	 * @return
	 */
	public int getWidth() {

		return width;
	}

	/**
	 * Returns length of the grid.
	 *
	 * @return
	 */
	public int getLength() {

		return length;
	}

	/**
	 * Initialize the grid by randomly assigning cell with one of the following class object:
	 * Casual, Empty, Outage, Reseller OR Streamer
	 */
	public void randomInit(int seed) {
		Random rand = new Random(seed);
		TownCell Cellreal = null;
		for (int i = 0; i < getLength(); i++) {
			for (int j = 0; j < getWidth(); j++) {
				int newRandomValue = rand.nextInt(5);
				if (newRandomValue == 0) {
					Cellreal = new Reseller(this, i, j);
				} else if (newRandomValue == 1) {
					Cellreal = new Empty(this, i, j);
				} else if (newRandomValue == 2) {
					Cellreal = new Casual(this, i, j);
				} else if (newRandomValue == 3) {
					Cellreal = new Outage(this, i, j);
				} else if (newRandomValue == 4) {
					Cellreal = new Streamer(this, i, j);
				}
				grid[i][j] = Cellreal;

			}
		}

	}

	/**
	 * Output the town grid. For each square, output the first letter of the cell type.
	 * Each letter should be separated either by a single space or a tab.
	 * And each row should be in a new line. There should not be any extra line between
	 * the rows.
	 * <p>
	 * NOTES:
	 */
	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < getLength(); i++){
			for (int j = 0; j< getWidth(); j++) {
					if(grid[i][j].who() == State.CASUAL){
						s += " C";
					}
					else if(grid[i][j].who() == State.STREAMER){
						s += " S";
					}
					else if(grid[i][j].who() == State.OUTAGE){
						s += " O";
					}
					else if(grid[i][j].who() == State.EMPTY){
						s += " E";
					}
					else if(grid[i][j].who() == State.RESELLER){
						s += " R";
					}
			}
		s += "\n";
		}
		return s;
	}


}



