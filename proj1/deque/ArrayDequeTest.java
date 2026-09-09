package deque;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Comparator;

public class ArrayDequeTest {
    @Test
    public void maintest() {
        ArrayDeque<Integer> adi = new ArrayDeque<>();
        for(int i = 0; i < 32; i++) {
            adi.addLast(i);
            assertEquals((int) adi.get(i), i);
        }
        adi.printDeque();

        for(int i = 0; i < 24; i++) {
            assertEquals((int) adi.removeLast(), 31 - i);
        }

    }

    @Test
    public void testIterator() {
        ArrayDeque<Integer> adi = new ArrayDeque<>();
        for(int i = 0; i < 32; i++) {
            adi.addLast(i);
        }
        int i = 0;
        for (int n : adi) {
            assertEquals(n, i);
            i++;
        }
    }

    private class IntComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }

    @Test
    public void testMax() {
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>(new IntComparator());
        mad.addLast(5);
        mad.addLast(10);
        mad.addLast(15);
        assertEquals((int)mad.max(), 15);

        mad.addLast(5);
        mad.addLast(10);
        assertEquals((int)mad.max(), 15);
    }
}
