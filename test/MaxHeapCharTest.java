import com.Martinez.BinaryHeap.Heap;
import com.Martinez.BinaryHeap.MaxBinaryHeap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;



/**
 * Created by alx on 1/17/16.
 */

@RunWith(JUnitParamsRunner.class)
public class MaxHeapCharTest {
    Heap heap;

    @Before
    public void setup() {
        heap = new MaxBinaryHeap<>();
    }
    private static final Object[] CharParams() {
        return new Object[] {
            'a','b','c','d','z','d','b'};
    }

    private static final Object[] CharSizeParams() {
        return new Object[] {
            new Object[]{new char[]{'a'}, 1},
            new Object[]{new char[]{'b','c','d'}, 3},
            new Object[]{new char[] {'d','c','c','c'}, 4}
        };
    }

    private static final Object[] CharPeekParams() {
        return new Object[] {
            new Object[]{new char[]{'a'}, 'a'},
            new Object[]{new char[]{'b','c','d'}, 'd'},
            new Object[]{new char[] {'d','c','c','b'}, 'd'},
            new Object[]{new char[] {'w','m','f','w','j'}, 'w'}
        };
    }

    private static final Object[] CharRMParams() {
        return new Object[] {
            new Object[]{new char[]{'a','a'}, 'a', 'a'},
            new Object[]{new char[]{'b','c','d',}, 'd', 'c'},
            new Object[]{new char[] {'d','c','c','b'}, 'd','c'},
            new Object[]{new char[] {'w','m','f','w','j'}, 'w', 'w'}
        };
    }

    @Test
    @Parameters(method = "CharParams")
    public void insertChars(char val) {
        heap.insert(val);
    }

    @Test
    @Parameters(method = "CharSizeParams")
    public void testSize(char[] vals, int size) {
        for(char val: vals) {
            heap.insert(val);
        }

        assertEquals(size, heap.size());
    }

    //test peak
    @Test
    @Parameters(method = "CharPeekParams")
    public void testPeek(char[] vals, char root) {
        for(char val: vals) {
            heap.insert(val);
        }

        assertEquals(root, heap.peek());
    }

    //test remove
    @Test
    @Parameters(method = "CharRMParams")
    public void testRemove(char[] vals, char deleted, char rootAfterRm) {
        for(char val : vals) {
            heap.insert(val);
        }

        assertEquals(deleted, heap.remove());
        assertEquals(rootAfterRm, heap.peek());
    }

    //test illegal params

}
