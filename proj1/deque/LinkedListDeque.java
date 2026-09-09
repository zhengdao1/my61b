package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable<T>,Deque<T> {
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

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private Node curr;

        public LinkedListIterator() {
            curr = sentinel.next;
        }

        @Override
        public boolean hasNext() {
            return curr != sentinel;
        }

        @Override
        public T next() {
            T returnItem = curr.item;
            curr = curr.next;
            return returnItem;
        }
    }


    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(!(o instanceof LinkedListDeque)) {
            return false;
        }

        LinkedListDeque<T> lld = (LinkedListDeque<T>) o;
        if(this.size() != (lld.size())) {
            return false;
        }

        Node curr = sentinel.next;
        Node currOther = lld.sentinel.next;
        for(int i = 0; i < this.size(); i++) {
            if(!curr.item.equals(currOther.item)) {
                return false;
            }
            curr = curr.next;
            currOther = currOther.next;
        }
        return true;
    }

    @Override
    public void addFirst(T item) {
        Node n = new Node(item);
        n.next = sentinel.next;
        n.prev = sentinel;
        sentinel.next = n;
        n.next.prev = n;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        Node n = new Node(item);
        n.prev = sentinel.prev;
        n.next = sentinel;
        sentinel.prev.next = n;
        sentinel.prev = n;
        size += 1;
    }

    @Override
    public T removeFirst() {
        Node n = sentinel.next;
        sentinel.next = n.next;
        n.next.prev = sentinel;
        if (!isEmpty()) {
            size -= 1;
        }
        return n.item;
    }

    @Override
    public T removeLast() {
        Node n = sentinel.prev;
        sentinel.prev = n.prev;
        n.prev.next = sentinel;
        if (!isEmpty()) {
            size -= 1;
        }
        return n.item;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
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

    @Override
    public void printDeque() {
        for(T item : this) {
            System.out.print(item + " ");
        }
        System.out.println();
    }
}
