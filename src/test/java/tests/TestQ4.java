package tests;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import answers.*;

public class TestQ4 extends TestCase {
    public TestQ4( String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( TestQ4.class );
    }

    public static void testFirst() {
        String[][] inputArr = {
            {"12", "12", "3", "X", "3"},
            {"23", "X", "X", "X", "3"},
            {"33", "21", "X", "X", "X"},
            {"9", "12", "3", "X", "X"},
            {"X", "X", "X", "4", "5"}
        };
        int k = 3;
        assertEquals(24, Question4.selectionFailedTradedesks(inputArr, k));
    }

    public static void testSecond() {
        String[][] inputArr = {
            {"X", "X", "2"},
            {"2", "3", "X"},
            {"X", "3", "2"}
        };
        int k = 3;
        assertEquals(0, Question4.selectionFailedTradedesks(inputArr, k));
    }

    public static void testThird() {
        String[][] inputArr = {
            {"2", "3", "X", "2"},
            {"4", "X", "X", "4"},
            {"3", "2", "X", "X"},
            {"X", "X", "X", "5"}
        };
        int k = 2;
        assertEquals(5, Question4.selectionFailedTradedesks(inputArr, k));
    }
}
