package answers;
import java.util.*;

public class Question6 {

	public static class Pair implements Comparable<Pair> {
		int where;
		int dist;
		Pair(int w, int d) {
			where = w;
			dist = d;
		}

		public int compareTo(Pair p) {
			if(dist == p.dist) {
				if(where == p.where) return 0;
				if(where < p.where) return -1;
				if(where > p.where) return 1;
			}
			return (dist < p.dist) ? -1 : 1;
		}
	}

	public static int shortestServerRoute(int numServers, int targetServer, int[][] times) {
		return bruteforce(numServers, targetServer, times);
	}

	public static int bruteforce(int numServers, int targetServer, int[][] times) {
		int[] minDistance = new int[numServers];
		boolean[] visited = new boolean[numServers];
		for(int i = 0; i < numServers; i++) {
			minDistance[i] = 2147483647;
		}

		PriorityQueue<Pair> S = new PriorityQueue<>();
		S.add(new Pair(0, 0));
		while(S.size() > 0 && !visited[targetServer]) {
			Pair p = S.poll();
			if(visited[p.where]) continue;
			visited[p.where] = true;
			minDistance[p.where] = p.dist;
			if(p.where == targetServer) break;
			for(int i = 0; i < numServers; i++) {
				if(!visited[i] && p.dist + times[p.where][i] < minDistance[i]) {
					minDistance[i] = p.dist + times[p.where][i];
					S.add(new Pair(i, minDistance[i]));
				}
			}
		}
		return minDistance[targetServer];
	}

	public static int optimal(int numServers, int targetServer, int[][] times) {
		int[] minDistance = new int[numServers];
		boolean[] visited = new boolean[numServers];
		int start = 0;
		int end = numServers-1;
		int[] next = new int[numServers];
		int[] prev = new int[numServers];
		for(int i = 0; i < numServers; i++) {
			minDistance[i] = 2147483647;
			next[i] = i+1;
			prev[i] = i-1;
		}

		PriorityQueue<Pair> S = new PriorityQueue<>();
		S.add(new Pair(0, 0));
		while(true) {
			Pair p = S.poll();
			if(visited[p.where]) continue;
			visited[p.where] = true;
			minDistance[p.where] = p.dist;
			if(p.where == targetServer) break;

			if(p.where == start) start = next[p.where];
			else if(p.where == end) end = prev[p.where];
			else {
				next[prev[p.where]] = next[p.where];
				prev[next[p.where]] = prev[p.where];
			}
			for(int i = start;;) {
				if(p.dist + times[p.where][i] < minDistance[i]) {
					minDistance[i] = p.dist + times[p.where][i];
					S.add(new Pair(i, minDistance[i]));
				}
				if(i == end) break;
				i = next[i];
			}
		}
		return minDistance[targetServer];
	}

}
