package answers;

import helpers.Edge;
import java.util.ArrayList;
import java.util.List;

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
		int y = 0;
		List<Integer> ones = new ArrayList<>(32);
		List<Integer> zeros = new ArrayList<>(32);

		boolean good = true;
		for(int i = 1; i <= base; i++) {
			tmp = i;
			y = 0;
			good = true;
			ones.clear();
			zeros.clear();
			for(int j = 0; j < numNodes; j++) {
				if(tmp%2 == 1) {
					ones.add(j);
					for(int k = ones.size()-2; k >= 0; k--) {
						if(connected[j][ones.get(k)]) {
							good = false;
							break;
						}
					}
				} else {
					zeros.add(j);
				}
				if(!good) break;
				tmp >>= 1;
			}
			if(!good) continue;
			for(int j = 0; j < zeros.size(); j++) {
				for(int k = 0; k < ones.size(); k++) {
					if(connected[zeros.get(j)][ones.get(k)]) {
						y++;
						break;
					}
				}
			}
			if(ones.size()-y > result) result = ones.size()-y;
		}
		return result;
	}

}
