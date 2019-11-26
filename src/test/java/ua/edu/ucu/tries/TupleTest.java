package ua.edu.ucu.tries;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TupleTest {
    private Tuple tup;

    @Before
    public void init() {
        String word = "Hello";
        tup = new Tuple(word, word.length());
    }

    @Test
    public void testTerm() {
        assertEquals(tup.term, "Hello");
    }

    @Test
    public void testWeight() {
        assertEquals(tup.weight, 5);
    }
}
