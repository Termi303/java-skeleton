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
		int[] minDistance = new int[numServers];
		boolean[] visited = new boolean[numServers];
		List<Integer> pos = new LinkedList<>();
		for(int i = 0; i < numServers; i++) {
			minDistance[i] = 2147483647;
			pos.add(i);
		}

		PriorityQueue<Pair> S = new PriorityQueue<>();
		S.add(new Pair(0, 0));
		while(S.size() > 0 && !visited[targetServer]) {
			Pair p = S.poll();
			if(visited[p.where]) continue;
			visited[p.where] = true;
			minDistance[p.where] = p.dist;
			if(p.where == targetServer) break;
			pos.remove(p.where);
			for(int i : pos) {
				if(p.dist + times[p.where][i] < minDistance[i]) {
					minDistance[i] = p.dist + times[p.where][i];
					S.add(new Pair(i, minDistance[i]));
				}
			}
		}
		return minDistance[targetServer];
	}

}
