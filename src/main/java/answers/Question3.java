package answers;

import helpers.Edge;

public class Question3 {

	public static int lowestExposureToExchanges(int numNodes, Edge[] edgeList) {
		return bruteforce(numNodes, edgeList);
	}

	private static int bruteforce(int numNodes, Edge[] edgeList) {
		boolean[][] connected = new boolean[numNodes][numNodes];
		for(Edge edge : edgeList) {
			edge.setEdgeA(edge.getEdgeA()-1);
			edge.setEdgeB(edge.getEdgeB()-1);
			connected[edge.getEdgeA()][edge.getEdgeB()] = true;
			connected[edge.getEdgeB()][edge.getEdgeA()] = true;
		}
		int base = 1;
		int result = 0;
		base <<= numNodes;
		int tmp = 0;
		int x = 0;
		int y = 0;
		boolean[] dist = new boolean[numNodes];

		boolean good = true;
		for(int i = 1; i <= base; i++) {
			tmp = i;
			x = y = 0;
			good = true;
			for(int j = 0; j < numNodes; j++) {
				if(tmp%2 == 1) {
					dist[j] = true;
					for(int k = 0; k < j; k++) {
						if(dist[k] && connected[j][k]) {
							good = false;
							break;
						}
					}
					x++;
				} else {
					dist[j] = false;
				}
				if(!good) break;
				tmp >>= 1;
			}
			if(!good) continue;
			for(int j = 0; j < numNodes; j++) {
				if(!dist[j]) {
					for(int k = 0; k < numNodes; k++) {
						if(dist[k] && connected[j][k]) {
							y++;
							break;
						}
					}
				}
			}
			if(x-y > result) result = x-y;
		}
		return result;
	}

}
