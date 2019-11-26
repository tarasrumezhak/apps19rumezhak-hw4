
package ua.edu.ucu.autocomplete;

import static org.hamcrest.Matchers.containsInAnyOrder;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import ua.edu.ucu.tries.RWayTrie;

/**
 *
 * @author Andrii_Rodionov
 */
public class PrefixMatchesITTest {

    private PrefixMatches pm;

    @Before
    public void init() {
        pm = new PrefixMatches(new RWayTrie());
        pm.load("abc", "abce", "abcd", "abcde", "abcdef");
    }

    @Test
    public void testWordsWithPrefix_String() {
        String pref = "ab";

        Iterable<String> result = pm.wordsWithPrefix(pref);

        String[] expResult = {"abc", "abce", "abcd", "abcde", "abcdef"};

        assertThat(result, containsInAnyOrder(expResult));
    }

    @Test
    public void testWordsWithPrefix_String_and_K() {
        String pref = "abc";
        int k = 3;

        Iterable<String> result = pm.wordsWithPrefix(pref, k);

        String[] expResult = {"abc", "abce", "abcd", "abcde"};

        assertThat(result, containsInAnyOrder(expResult));
    }

    @Test
    public void testDeleteWord() {
        pm.delete("abce");
        String[] expResult = {"abc", "abcd", "abcde", "abcdef"};
        assertThat(pm.wordsWithPrefix("abc"), containsInAnyOrder(expResult));
    }

    @Test
    public void testLoad() {
        pm.load("abchehy abcyyhhy");
        String[] expResult = {"abc", "abcd", "abcde", "abce","abcdef", "abchehy", "abcyyhhy"};
        Iterable<String> result = pm.wordsWithPrefix("abc");
        assertThat(result, containsInAnyOrder(expResult));
    }

    @Test
    public void testSize() {
        pm.load("abchehy abcyyhhy");
        String[] expResult = {"abc", "abcd", "abcde", "abce","abcdef", "abchehy", "abcyyhhy"};
        assertEquals(pm.size(), 7);
    }
}
