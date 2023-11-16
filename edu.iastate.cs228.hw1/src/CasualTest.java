import edu.iastate.cs228.hw1.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CasualTest {
    @Test
    void test() {
        Town a = new Town(2,2);
        a.grid[0][0] = new Casual(a,0,0);
        a.grid[0][1] = new Streamer(a,0,1);
        a.grid[1][0] = new Casual(a,1,0);
        a.grid[1][1] = new Casual(a,1,1);
        assertEquals(State.RESELLER, a.grid[0][0].next(a).who());
    }

    @Test
    void test1() {
        Town t = new Town(2,2);
        t.grid[0][0] = new Casual(t,0,0);
        t.grid[0][1] = new Casual(t,0,1);
        t.grid[1][0] = new Casual(t,1,0);
        t.grid[1][1] = new Casual(t,1,1);
        assertEquals(State.RESELLER, t.grid[0][0].next(t).who());

        Town t2 = new Town(2,2);

        for(int i = 0; i < t2.getLength();i++) {
            for(int j = 0; j < t2.getWidth(); j++) {
                t2.grid[i][j]= new Casual(t2, i, j);
            }
        }
        t2.toString();
        assertEquals(4, ISPBusiness.getProfit(t2));

    }
}