import edu.iastate.cs228.hw1.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StreamerTest {

    @Test
    void testA() {
        Town t = new Town(2,2);
        t.grid[0][0] = new Streamer(t,0,0);
        t.grid[0][1] = new Streamer(t,0,1);
        t.grid[1][0] = new Reseller(t,1,0);
        t.grid[1][1] = new Casual(t,1,1);
        assertEquals(State.OUTAGE, t.grid[0][0].next(t).who());

    }
}