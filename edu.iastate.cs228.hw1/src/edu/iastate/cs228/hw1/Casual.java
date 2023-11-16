package edu.iastate.cs228.hw1;

/**
 * @author Marcos Medina
 */
public class Casual extends TownCell{
    public Casual(Town p, int r, int c) {
        super(p, r, c);
    }

    @Override
    public State who() {
        return State.CASUAL;
    }

    @Override
    public TownCell next(Town tNew) {
        int nCensus[] = new int[NUM_CELL_TYPE];
        census(nCensus);
        if (nCensus[EMPTY] + nCensus[OUTAGE] <= 1) {
            return new Reseller(tNew, row, col);
        }
        else if (nCensus[RESELLER] > 0)
        {
            return new Outage(tNew, row, col);
        }

        else if (nCensus[STREAMER] > 0)
        {
            return new Streamer(tNew, row, col);
        }
        else if (nCensus[CASUAL] >= 5) {
            return new Streamer(tNew, row, col);
        }
        else
        {
            return new Casual(tNew, row, col);
        }

    }

}