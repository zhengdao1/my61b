package deque;

public class ArrayDeque<T> {
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

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void resize(int n) {
        T[] a = (T[]) new Object[n];
        if(first == 0) {
            System.arraycopy(items, 0, a, 0, items.length);
        }
        else {
            System.arraycopy(items, first, a, 0, items.length - first);
            System.arraycopy(items, 0, a, items.length - first, lastNull);
        }
        items = a;
        first = 0;
        lastNull = size;
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        first = (first - 1 + items.length) % items.length;
        items[first] = item;
        size++;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[lastNull] = item;
        size++;
        lastNull = (lastNull + 1) % items.length;
    }

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

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        return items[(index + first) % items.length];
    }
}
