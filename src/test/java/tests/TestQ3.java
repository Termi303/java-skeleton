package tests;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import answers.*;
import helpers.*;

public class TestQ3 extends TestCase {
    public TestQ3( String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( TestQ3.class );
    }

    private static Edge createEdge(int a, int b) {
        int[] arr = {a, b};
        return new Edge(arr);
    }

    public static void testFirstSample() {
        int numNodes = 3;
        Edge[] edges = new Edge[2];
        edges[0] = createEdge(1, 2);
        edges[1] = createEdge(2, 3);

        assertEquals(1, Question3.lowestExposureToExchanges(numNodes, edges));
    }

    public static void testSecondSample() {
        int numNodes = 5;
        Edge[] edges = new Edge[6];
        edges[0] = createEdge(1, 3);
        edges[1] = createEdge(1, 4);
        edges[2] = createEdge(1, 5);
        edges[3] = createEdge(2, 3);
        edges[4] = createEdge(2, 4);
        edges[5] = createEdge(2, 5);
        assertEquals(1, Question3.lowestExposureToExchanges(numNodes, edges));
    }
}
