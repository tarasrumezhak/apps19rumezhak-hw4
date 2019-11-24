package ua.edu.ucu.tries;

public final class ImmutableLinkedList{
    public ImmutableLinkedList() {
        head = null;
        size = 0;
    }

    ImmutableLinkedList(Object[] c) {
        if (c.length != 0) {
            head = new Node(c[0]);
            Node temp = head;
            for (int i = 1; i < c.length; i++) {
                temp.next = new Node(c[i]);
                temp = temp.next;
            }
        }
        size = c.length;
    }

    private ImmutableLinkedList(Node n) {
        head = n;
    }

    static class Node {
        Object data;
        Node next;

        Node(Object o) {
            data = o;
            next = null;
        }
    }

    private Node head;
    private int size = 0;

    private void checkIndex(int index) {
        if (index < 0 || index > size + 1) {
            throw new IndexOutOfBoundsException();
        }
    }

    private ImmutableLinkedList copyList(ImmutableLinkedList original) {
        if (original.head == null) {
            return new ImmutableLinkedList();
        }
        ImmutableLinkedList newList = new ImmutableLinkedList();
        newList.size = size;
        newList.head = new Node(original.head.data);
        Node temp_original = original.head.next;
        Node temp_new = newList.head;
        while (temp_original != null) {
            temp_new.next = new Node(temp_original.data);
            temp_new = temp_new.next;
            temp_original = temp_original.next;
        }
        return newList;
    }

    public ImmutableLinkedList add(Object e) {
        return add(size, e);
    }

    public ImmutableLinkedList add(int index, Object e) {
        checkIndex(index);
        int counter = 0;
        ImmutableLinkedList newList = copyList(this);
        Node tempNode = newList.head;
        Node newNode = new Node(e);
        if (tempNode == null) {
            newList.head = new Node(e);
        } else if (index == 0) {
            newNode.next = tempNode;
            ImmutableLinkedList lst = new ImmutableLinkedList(newNode);
            lst.size = size + 1;
            return lst;
        }
        else {
            while (tempNode != null) {
                if (counter  == index - 1) {
                    Node temp = tempNode.next;
                    tempNode.next = newNode;
                    tempNode.next.next = temp;
                }
                tempNode = tempNode.next;
                counter++;
            }
        }
        newList.size++;
        return newList;
    }

    public ImmutableLinkedList addAll(Object[] c) {
        return addAll(size, c);
    }

    public ImmutableLinkedList addAll(int index, Object[] c) {
        ImmutableLinkedList newList = copyList(this);
        for (int i = 0; i < c.length; i++) {
            newList = newList.add(index + i, c[i]);
        }
        return newList;
    }

    public Object get(int index) {
        checkIndex(index);
        ImmutableLinkedList newList = copyList(this);
        Node temp = newList.head;
        while (index-- != 0) {
            temp = temp.next;
        }
        return temp.data;
    }


    public ImmutableLinkedList remove(int index) {
        checkIndex(index);
        ImmutableLinkedList newList = copyList(this);
        Node temp = newList.head;
        int counter = 0;
        if (index == 0) {
            ImmutableLinkedList lst = new ImmutableLinkedList(temp.next);
            lst.size = this.size - 1;
            return lst;
        }
        while (counter != index-1) {
            temp = temp.next;
            counter++;
        }
        Node prev = temp;
        Node next = temp.next.next;
        prev.next = next;
        newList.size--;
        return newList;
    }

    public ImmutableLinkedList set(int index, Object e) {
        checkIndex(index);
        ImmutableLinkedList newList = copyList(this);
        Node temp = newList.head;
        while (index-- != 0) {
            temp = temp.next;
        }
        temp.data = e;
        return newList;
    }

    public int indexOf(Object e) {
        ImmutableLinkedList newList = copyList(this);
        Node temp = newList.head;
        int counter = 0;
        while (temp != null && !temp.data.equals(e)) {
            temp = temp.next;
            counter++;
        }
        if (counter == newList.size && newList.getLast() != e) {
            return -1;
        }
        return counter;
    }

    public int size() {
        return size;
    }

    public ImmutableLinkedList clear() {
        return new ImmutableLinkedList();
    }

    public boolean isEmpty() {
        return size <= 0;
    }

    public Object[] toArray() {
        ImmutableLinkedList newList = copyList(this);
        Object[] array = new Object[size];
        Node temp = newList.head;
        int i = 0;
        while (temp != null) {
            array[i] = temp.data;
            temp = temp.next;
            i++;
        }
        return array;
    }

    public ImmutableLinkedList addFirst(Object e) {
        return add(0, e);
    }

    public ImmutableLinkedList addLast(Object e) {
        return add(e);
    }

    public Object getFirst() {
        return get(0);
    }

    public Object getLast() {
        return get(size-1);
    }

    public ImmutableLinkedList removeFirst() {
        return remove(0);
    }

    public ImmutableLinkedList removeLast() {
        return remove(size-1);
    }


    @Override
    public String toString() {
        ImmutableLinkedList newList = copyList(this);
        Node temp = newList.head;
        if (temp == null) {
            return "";
        }
        StringBuilder linkedText = new StringBuilder();
        do {
            linkedText.append(temp.data).append(", ");;
            temp = temp.next;
        } while (temp != null);
        return linkedText.toString().substring(0, linkedText.length() - 2);
    }
}
