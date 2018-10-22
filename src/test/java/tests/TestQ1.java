package tests;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import answers.*;
import java.util.*;

public class TestQ1 extends TestCase {
    public TestQ1( String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( TestQ1.class );
    }

    public static void testOne() {
        int[] arr = new int[1];
        arr[0] = 1;
        assertEquals(0, Question1.bestMergedPortfolio(arr));
    }

    public static void testFirst() {
        int[] arr = {15, 8, 6, 7};
        assertEquals(15, Question1.bestMergedPortfolio(arr));
    }

    public static void testSecond() {
        int[] arr = {9, 7, 12, 2};
        assertEquals(14, Question1.bestMergedPortfolio(arr));
    }

    public static void testBig() {
        int[] arr = new int[100];
        for(int i = 0; i < 100; i++) arr[i] = i+1;
        assertEquals(127, Question1.bestMergedPortfolio(arr));
    }

    public static void testTrie() {
        int sz = 100;
        int[] arr = new int[sz];
        Random random = new Random();
        for(int i = 1; i <= 1000; i++) {
            System.out.println("Running test #" + i);
            for(int j = 0; j < sz; j++) {
                arr[j] = random.nextInt(65536);
            }
            if(Question1.brute(arr) != Question1.trie(arr)) {
                for(int j = 0; j < sz; j++) {
                    System.out.print(arr[j] + " ");
                }
                System.out.println("Brute: " + Question1.brute(arr));
                System.out.println("Trie: " + Question1.trie(arr));
                assert false;
            }
        }
    }
}
