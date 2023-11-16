package edu.iastate.cs228.hw1;



/**
 * 
 * @author Marcos Medina
 *	TownCell Creates grids based on a file input, or through a random input with a seed
 *
 */
public abstract class TownCell {

	protected Town plain;
	protected int row;
	protected int col;

	protected int colStart;
	protected int colEnd;
	protected int rowStart;
	protected int rowEnd;
	
	
	// constants to be used as indices.
	protected static final int RESELLER = 0;
	protected static final int EMPTY = 1;
	protected static final int CASUAL = 2;
	protected static final int OUTAGE = 3;
	protected static final int STREAMER = 4;
	
	public static final int NUM_CELL_TYPE = 5;
	
	//Use this static array to take census.
	public static final int[] nCensus = new int[NUM_CELL_TYPE];

	public TownCell(Town p, int r, int c) {
		plain = p;
		row = r;
		col = c;
	}
	
	/**
	 * Checks all neigborhood cell types in the neighborhood.
	 * Refer to homework pdf for neighbor definitions (all adjacent
	 * neighbors excluding the center cell).
	 * Use who() method to get who is present in the neighborhood
	 *  
	 * @paramcounts of all customers
	 */
	public void census(int nCensus[]) {
		// zero the counts of all customers
		nCensus[RESELLER] = 0; 
		nCensus[EMPTY] = 0; 
		nCensus[CASUAL] = 0; 
		nCensus[OUTAGE] = 0; 
		nCensus[STREAMER] = 0;

		colStart = NegativeCK( col - 1);
		colEnd = NegativeCK(col + 1);
		rowStart = NegativeCK(row - 1);
		rowEnd = NegativeCK(row + 1);

		for (int i = colStart; i <= colEnd; i++) {
		    for (int j = rowStart; j <= rowEnd; j++) {
		        try{
					if ((j == row) && (i == col)){
						continue;
					}

						if (plain.grid[j][i].who() == State.OUTAGE){
							nCensus[OUTAGE]++;
						}
						else if (plain.grid[j][i].who() == State.EMPTY){
							nCensus[EMPTY]++;
						}
						else if (plain.grid[j][i].who() == State.RESELLER) {
							nCensus[RESELLER]++;
						}
						else if (plain.grid[j][i].who() == State.CASUAL) {
							nCensus[CASUAL]++;
						}
						else if (plain.grid[j][i].who() == State.STREAMER){
							nCensus[STREAMER]++;
						}

				}
				catch (ArrayIndexOutOfBoundsException e){

				}
		    }
		}


	}

	private int NegativeCK(int i){
		if (i < 0){
			return 0;
		}
		else {
			return i;
		}
	}

	public abstract State who();

	/**
	 * Determines the cell type in the next cycle.
	 * 
	 * @param tNew: town of the next cycle
	 * @return TownCell
	 */
	public abstract TownCell next(Town tNew);

}
