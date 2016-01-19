import com.Martinez.BinaryHeap.Heap;
import com.Martinez.BinaryHeap.MinBinaryHeap;
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
public class MinHeapCharTest {
	Heap heap;

	@Before
	public void setup() {
		heap = new MinBinaryHeap<>();
	}

	private static final Object[] CharPeekParams() {
		return new Object[] {
			new Object[]{new char[]{'a'}, 'a'},
			new Object[]{new char[]{'b','c','d'}, 'b'},
			new Object[]{new char[] {'d','c','c','b'}, 'b'},
			new Object[]{new char[] {'w','m','f','w','j'}, 'f'}
		};
	}

	private static final Object[] CharRMParams() {
		return new Object[] {
			new Object[]{new char[]{'a','a'}, 'a', 'a'},
			new Object[]{new char[]{'b','c','d',}, 'b', 'c'},
			new Object[]{new char[] {'d','c','c','b'}, 'b','c'},
			new Object[]{new char[] {'w','m','f','w','j'}, 'f', 'j'}
		};
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

}
