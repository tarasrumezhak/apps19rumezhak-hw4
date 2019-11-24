package ua.edu.ucu.tries;

import ua.edu.ucu.tries.ImmutableLinkedList;

public class Queue {
    private ImmutableLinkedList linkedList;

    public Queue() {
        linkedList = new ImmutableLinkedList();
    }

    Object peek() {
        return linkedList.getFirst();
    }

    Object dequeue() {
        Object obj = this.peek();
        linkedList = linkedList.removeFirst();
        return obj;
    }

    void enqueue(Object e) {
        linkedList = linkedList.addLast(e);
    }

    int size() {
        return linkedList.size();
    }

}
