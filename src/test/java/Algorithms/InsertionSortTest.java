package Algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InsertionSortTest {

    @Test
    void testEmpty() {
        int[] a = new int[0];
        InsertionSort.sort(a);
        assertEquals(0, a.length);
    }

    @Test
    void testSingle() {
        int[] a = new int[]{7};
        InsertionSort.sort(a);
        assertArrayEquals(new int[]{7}, a);
    }

    @Test
    void testDuplicates() {
        int[] a = new int[]{3,1,2,3,3,1};
        InsertionSort.sort(a);
        assertArrayEquals(new int[]{1,1,2,3,3,3}, a);
    }

    @Test
    void testSorted() {
        int[] a = new int[]{1,2,3,4,5};
        InsertionSort.sort(a);
        assertArrayEquals(new int[]{1,2,3,4,5}, a);
    }

    @Test
    void testReverse() {
        int[] a = new int[]{5,4,3,2,1};
        InsertionSort.sort(a);
        assertArrayEquals(new int[]{1,2,3,4,5}, a);
    }
}
