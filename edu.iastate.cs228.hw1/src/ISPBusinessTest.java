import edu.iastate.cs228.hw1.Casual;
import edu.iastate.cs228.hw1.Empty;
import edu.iastate.cs228.hw1.ISPBusiness;
import edu.iastate.cs228.hw1.Town;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ISPBusinessTest {
    Town a;

    @Before

    @Test
    public void testCensus()
    {
        Town a = new Town(2,2);
        a.grid[0][0] = new Empty(a,0,0);
        a.grid[0][1] = new Empty(a,0,1);
        a.grid[1][0] = new Casual(a,1,0);
        a.grid[1][1] = new Casual(a,1,1);

        assertEquals(2, ISPBusiness.getProfit(a));
    }
}