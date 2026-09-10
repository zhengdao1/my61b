package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T>, Deque<T> {
    private T[] items;
    private int size;

    private int first;
    private int lastNull;

    // Invariant: the first item is at items[first], the last item is at items[lastNull - 1]

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        first = 0;
        lastNull = 0;
    }

    @Override
    public int size() {
        return size;
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int index;
        public ArrayDequeIterator() {
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            T returnItem = items[(index + first) % items.length];
            index++;
            return returnItem;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ArrayDeque<T> other = (ArrayDeque<T>) o;
        if (this.size != other.size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!this.items[(i + first) % items.length].equals(other.items[(i + other.first) % other.items.length])) {
                return false;
            }
        }
        return true;
    }

    private void resize(int n) {
        T[] a = (T[]) new Object[n];
        if(first == 0) {
            if(items.length <= n) {
                System.arraycopy(items, 0, a, 0, items.length);
            }
            else {
                System.arraycopy(items, 0, a, 0, n);
            }
        }
        else {
            if(lastNull <= first) {
                System.arraycopy(items, first, a, 0, items.length - first);
                System.arraycopy(items, 0, a, items.length - first, lastNull);
            }
            else {
                System.arraycopy(items, first, a, 0, lastNull - first);
            }
        }
        items = a;
        first = 0;
        lastNull = size;
    }

    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        first = (first - 1 + items.length) % items.length;
        items[first] = item;
        size++;
    }

    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[lastNull] = item;
        size++;
        lastNull = (lastNull + 1) % items.length;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            System.out.println("ArrayDeque is empty");
            return null;
        }

        T item = items[first];
        items[first] = null;
        size--;
        first = (first + 1) % items.length;
        if (items.length >= 16 && size <= items.length / 4) {
            resize(items.length / 2);
        }
        return item;
    }

    @Override
    public T removeLast() {
        // check empty
        if (size == 0) {
            System.out.println("ArrayDeque is empty");
            return null;
        }

        // find last item
        int index = (lastNull - 1 + items.length) % items.length;
        T item = items[index];
        items[index] = null;
        size--;
        lastNull = index;

        // shrink
        if (items.length >= 16 && size <= items.length / 4) {
            resize(items.length / 2);
        }
        return item;
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        return items[(index + first) % items.length];
    }

    @Override
    public void printDeque() {
        for (T item : this) {
            System.out.print(item + " ");
        }
        System.out.println();
    }
}
