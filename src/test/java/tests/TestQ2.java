package tests;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import answers.*;

public class TestQ2 extends TestCase {
    public TestQ2( String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( TestQ2.class );
    }

    public static void testFirst() {
        int[] cashIn = {66, 293, 215, 188, 147, 326, 449, 162, 46, 350};
        int[] cashOut = {170, 153, 305, 290, 187};
        assertEquals(0, Question2.equallyBalancedCashFlow(cashIn, cashOut));
    }

    public static void testSecond() {
        int[] cashIn = {189, 28};
        int[] cashOut = {43, 267, 112, 166};
        assertEquals(8, Question2.equallyBalancedCashFlow(cashIn, cashOut));
    }

    public static void testThird() {
        int[] cashIn = {72, 24, 73, 4, 28, 56, 1, 43};
        int[] cashOut = {27};
        assertEquals(1, Question2.equallyBalancedCashFlow(cashIn, cashOut));
    }
}
