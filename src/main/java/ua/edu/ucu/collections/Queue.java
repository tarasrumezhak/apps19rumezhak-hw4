package ua.edu.ucu.collections;

import ua.edu.ucu.collections.ImmutableLinkedList;

public class Queue {
    private ImmutableLinkedList linkedList;

    public Queue() {
        linkedList = new ImmutableLinkedList();
    }

    public Object peek() {
        return linkedList.getFirst();
    }

    public Object dequeue() {
        Object obj = this.peek();
        linkedList = linkedList.removeFirst();
        return obj;
    }

    public void enqueue(Object e) {
        linkedList = linkedList.addLast(e);
    }

    public int size() {
        return linkedList.size();
    }

}
