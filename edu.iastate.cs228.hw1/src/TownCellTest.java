import edu.iastate.cs228.hw1.State;
import edu.iastate.cs228.hw1.Town;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class TownCellTest {

    Town t;

    @Before
    public void initialize() throws FileNotFoundException
    {
        t = new Town("ISP4x4.txt");
    }

    @Test
    public void testCensus()
    {
        String str = t.grid[1][1].next(t).who().toString();
        assertEquals("", State.EMPTY.toString(), str);
    }
}