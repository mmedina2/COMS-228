import edu.iastate.cs228.hw1.Town;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class TownTest {

    Town t;
    @Before
    public void initialize() throws FileNotFoundException
    {
        t = new Town(2, 2);
        t.randomInit(5);
    }


    @Test
    void testL()
    {
        assertEquals(2, t.getLength());
    }

    @Test
    void testW()
    {
        assertEquals(2, t.getWidth());
    }

}