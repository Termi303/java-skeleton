package tests;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import answers.*;

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
}
