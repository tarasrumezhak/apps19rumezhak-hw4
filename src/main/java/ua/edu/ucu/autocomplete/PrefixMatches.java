package ua.edu.ucu.autocomplete;

import ua.edu.ucu.tries.Trie;
import ua.edu.ucu.tries.Tuple;

import java.util.*;

/**
 *
 * @author andrii
 */
public class PrefixMatches {

    private Trie trie;

    public PrefixMatches(Trie trie) {
        this.trie = trie;
    }

    public int load(String... strings) {
        int counter = 0;
        for (String str: strings) {
            String[] splited = str.split("\\s+");
            for (String st: splited) {
                if (str.length() >= 2) {
                    trie.add(new Tuple(str, str.length()));
                    counter++;
                }
            }
        }
        return counter;
    }

    public boolean contains(String word) {
        return trie.contains(word);
    }

    public boolean delete(String word) {
        return trie.delete(word);
    }

    public Iterable<String> wordsWithPrefix(String pref) {
        if (pref.length() >= 2) {
            return trie.wordsWithPrefix(pref);
        }
        return null;
    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {
        if (pref.length() >= 2) {
            Iterable<String> arr = trie.wordsWithPrefix(pref);
            ArrayList<String> list = new ArrayList<>();
            for (String st: arr) {
                list.add(st);
            }
            list.sort(Comparator.comparingInt(String::length));
            int counter = 0;
            int prev_length = 0;
            ArrayList<String> result = new ArrayList<>();
            for (String st: list) {
                if (counter == k) {
                    break;
                }
                result.add(st);
                if (st.length() > prev_length) {
                    prev_length = st.length();
                    counter++;
                }
            }
            return result;
        }
        return null;
    }

    public int size() {
        return trie.size();
    }
}
