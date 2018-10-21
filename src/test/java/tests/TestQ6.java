package tests;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import answers.*;
import java.util.*;

public class TestQ6 extends TestCase {
    public TestQ6( String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( TestQ6.class );
    }

    public static void testFirst() {
        int numServers = 3;
        int targetServer = 1;
        int[][] times = {
            {0, 7, 4},
            {7, 0, 2},
            {4, 2, 0}
        };
        assertEquals(6, Question6.shortestServerRoute(numServers, targetServer, times));
    }

    public static void testOptimal() {
        int tests = 100;
        int numServers = 1000;
        int targetServer = 0;
        int distanceLimit = 1000;

        int[][] times = new int[numServers][numServers];

        Random random = new Random();
        for(int i = 1; i <= tests; i++) {
            System.out.println("Running test #" + i);
            targetServer = random.nextInt(numServers);
            for(int j = 0; j < numServers; j++) {
                for(int k = 0; k < numServers; k++) {
                    if(j == k) times[j][k] = 0;
                    else times[j][k] = random.nextInt(distanceLimit) + 1;
                }
            }
            int optim = 0;
            int brute = 0;
            try {
                optim = Question6.optimal(numServers, targetServer, times);
                brute = Question6.bruteforce(numServers, targetServer, times);
            } catch(Exception e) {
                System.out.println(e);
                System.out.println("numServers == " + numServers);
                System.out.println("target == " + targetServer);
                for(int j = 0; j < numServers; j++) {
                    for(int k = 0; k < numServers; k++) {
                        System.out.print(times[j][k] + " ");
                    }
                    System.out.print("\n");
                }
                assert false;
            }

            if(brute != optim) {
                System.out.println("numServers == " + numServers);
                System.out.println("target == " + targetServer);
                for(int j = 0; j < numServers; j++) {
                    for(int k = 0; k < numServers; k++) {
                        System.out.print(times[j][k] + " ");
                    }
                    System.out.print("\n");
                }
                System.out.println("bruteforce: " + brute);
                System.out.println("optimal: " + optim);
                assert false;
            }
        }
    }
}
