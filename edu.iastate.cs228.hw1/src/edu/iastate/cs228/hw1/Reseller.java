package edu.iastate.cs228.hw1;

import java.awt.geom.RectangularShape;

/**
 * @author Marcos Medina
 */
public class Reseller extends TownCell{
    public Reseller(Town p, int r, int c) {
        super(p, r, c);
    }

    @Override
    public State who() {
        return State.RESELLER;
    }

    @Override
    public TownCell next(Town tNew) {
        int nCensus[] = new int[NUM_CELL_TYPE];
        census(nCensus);

        if (nCensus[CASUAL] <= 3 || nCensus[EMPTY] >= 3)
        {
            return new Empty(tNew, row, col);
        }

        else
        {
            return new Reseller(tNew, row, col);
        }
    }
}
