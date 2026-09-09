package deque;

import java.util.Comparator;
import java.util.Iterator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> cmp;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        this.cmp = c;
    }


    public T max() {
        if(this.isEmpty()) {
            return null;
        }
        Iterator<T> iter = this.iterator();
        T max = iter.next();
        while(iter.hasNext()) {
            T item = iter.next();
            if(this.cmp.compare(item, max) > 0) {
                max = item;
            }
        }
        return max;
    }

    public T max(Comparator<T> c) {
        if (this.isEmpty()) {
            return null;
        }
        Iterator<T> iter = this.iterator();
        T max = iter.next();
        while(iter.hasNext()) {
            T item = iter.next();
            if(c.compare(item, max) > 0) {
                max = item;
            }
        }
        return max;
    }
}
