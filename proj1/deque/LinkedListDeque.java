package deque;

public class LinkedListDeque<T> {
    private class Node {
        public T item;
        public Node next;
        public Node prev;

        public Node(T item) {
            this.item = item;
            this.next = null;
            this.prev = null;
        }
    }

    /* Invariant: sentinel.next points to the first item in the list
     *            sentinel.prev points to the last item in the list
     */
    private int size;
    private Node sentinel;

    public LinkedListDeque() {
        sentinel = new Node(null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(T item) {
        Node n = new Node(item);
        n.next = sentinel.next;
        n.prev = sentinel;
        sentinel.next = n;
        n.next.prev = n;
        size += 1;
    }

    public void addLast(T item) {
        Node n = new Node(item);
        n.prev = sentinel.prev;
        n.next = sentinel;
        sentinel.prev.next = n;
        sentinel.prev = n;
        size += 1;
    }

    public T removeFirst() {
        Node n = sentinel.next;
        sentinel.next = n.next;
        n.next.prev = sentinel;
        if (!isEmpty()) {
            size -= 1;
        }
        return n.item;
    }

    public T removeLast() {
        Node n = sentinel.prev;
        sentinel.prev = n.prev;
        n.prev.next = sentinel;
        if (!isEmpty()) {
            size -= 1;
        }
        return n.item;
    }

    public int size() {
        return size;
    }

    public T get(int index) {
        // Using iteration
        Node curr = sentinel.next;
        for(int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr.item;
    }

    public T getRecursive(int index) {
        return getRecursiveHelper(index, sentinel.next);
    }

    private T getRecursiveHelper(int index, Node curr) {
        if(index == 0) {
            return curr.item;
        }
        else {
            return getRecursiveHelper(index - 1, curr.next);
        }
    }
}
