package ua.edu.ucu.tries;

import java.util.ArrayList;
import ua.edu.ucu.collections.Queue;

/*
This class was implemented based on Algorithms-4th-Edition-Robert-Sedgewick book.
 */

public class RWayTrie implements Trie {
    private static int R = 256; // radix
    private Node root; // root of trie
//    private int size = 0;

    private static class Node {
        private Object val;
        private Node[] next = new Node[R];
    }

    @Override
    public void add(Tuple t) {
        root = put(root, t.term, t.weight, 0);
//        size++;
    }

    private Node put(Node x, String key, int val, int d) { // Change value associated with key if in subtrie rooted at x.
        if (x == null) {
            x = new Node();
        }
        if (d == key.length()) {
            x.val = val;
            return x;
        }
        char c = key.charAt(d); // Use dth key char to identify subtrie.
        x.next[c] = put(x.next[c], key, val, d+1);
        return x;
    }

    @Override
    public boolean contains(String word) {
        try {
            return get(word) != -1;
        }
        catch (NullPointerException e) {
            return false;
        }
    }

    private int get(String key) {
        Node x = get(root, key, 0);
        if (x == null) return -1;
        return (Integer) x.val;
    }

    private Node get(Node x, String key, int d) { // Return value associated with key in the subtrie rooted at x.
        if (x == null) return null;
        if (d == key.length()) return x;
        char c = key.charAt(d); // Use dth key char to identify subtrie.
        return get(x.next[c], key, d+1);
    }

    @Override
    public boolean delete(String word) {
        root = delete(root, word, 0);
//        size--;
        return root != null;
    }

    private Node delete(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length())
            x.val = null;
        else {
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d+1);
        }
        if (x.val != null) return x;
        for (char c = 0; c < R; c++)
            if (x.next[c] != null) return x;


        return null;
    }

    @Override
    public Iterable<String> words() {
        return wordsWithPrefix("");
    }

    @Override
    public Iterable<String> wordsWithPrefix(String s) {
        Queue q = new Queue();
        ArrayList<String> lst = new ArrayList<>();

        collect(get(root, s, 0), s, q);

        int size = q.size();
        for (int i = 0; i < size; i++) {
            Object el = q.dequeue();
            lst.add((String) el);
        }

        return lst;
    }

    private void collect(Node x, String pre, Queue q) {
        if (x == null) return;
        if (x.val != null) q.enqueue(pre);
        for (char c = 0; c < R; c++)
            collect(x.next[c], pre + c, q);
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node x)
    {
        if (x == null) return 0;
        int cnt = 0;
        if (x.val != null) cnt++;
        for (char c = 0; c < R; c++)
            cnt += size(x.next[c]);
        return cnt;
    }
}
