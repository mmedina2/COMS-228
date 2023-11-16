import edu.iastate.cs228.hw1.Casual;
import edu.iastate.cs228.hw1.Reseller;
import edu.iastate.cs228.hw1.State;
import edu.iastate.cs228.hw1.Town;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResellerTest {

    @Test
    void testnew() {
        Town t = new Town(2,2);
        t.grid[0][0] = new Reseller(t,0,0);
        t.grid[0][1] = new Casual(t,0,1);
        t.grid[1][0] = new Casual(t,1,0);
        t.grid[1][1] = new Casual(t,1,1);
        assertEquals(State.EMPTY, t.grid[0][0].next(t).who());
    }
}