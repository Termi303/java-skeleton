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
}
