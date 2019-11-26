package ua.edu.ucu.tries;

import org.junit.Before;
import org.junit.Test;
import ua.edu.ucu.tries.RWayTrie;
import ua.edu.ucu.tries.Tuple;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.*;

public class RwayTrieTest {

    private RWayTrie trie;

    @Before
    public void init() {
        trie = new RWayTrie();
        String[] words = {"hello", "taras", "task", "abc", "abce", "abcd", "abcde", "abcdef"};
        for (String word : words) {
            trie.add(new Tuple(word, word.length()));
        }
    }

    @Test
    public void testAdd() {
        String word = "new";
        trie.add(new Tuple(word, word.length()));
        String[] expected = {"hello", "taras", "task", "abc", "abce", "abcd", "abcde", "abcdef", "new"};
        assertThat(trie.words(), containsInAnyOrder(expected));
    }

    @Test
    public void testDelete() {
        trie.delete("task");
        String[] expected = {"hello", "taras", "abc", "abce", "abcd", "abcde", "abcdef"};
        assertThat(trie.words(), containsInAnyOrder(expected));
    }

    @Test
    public void testContains() {
        assertTrue(trie.contains("abc"));
    }

    @Test
    public void testNotContains() {
        assertFalse(trie.contains("UCU"));
    }

    @Test
    public void testWords() {
        String[] expected = {"hello", "taras", "task", "abc", "abce", "abcd", "abcde", "abcdef"};
        assertThat(trie.words(), containsInAnyOrder(expected));
    }

    @Test
    public void testWordsWithPrefix() {
        String[] expected = {"abcd", "abcde", "abcdef"};
        assertThat(trie.wordsWithPrefix("abcd"), containsInAnyOrder(expected));
    }

    @Test
    public void testSize() {
        assertEquals(trie.size(), 8);
    }

    @Test
    public void testSize2() {
        trie.add(new Tuple("new", 3));
        trie.add(new Tuple("cola", 4));
        trie.delete("abc");
        assertEquals(trie.size(), 9);
    }
}
