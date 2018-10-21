package answers;

import helpers.Edge;
import java.util.ArrayList;
import java.util.List;

public class Question3 {

	public static int lowestExposureToExchanges(int numNodes, Edge[] edgeList) {
		return optimal(numNodes, edgeList);
	}

	public static int bruteforce(int numNodes, Edge[] edgeList) {
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

	public static int optimal(int numNodes, Edge[] edgeList) {
		int[] coefficient = new int[numNodes];
		coefficient[0] = 1;
		for(int i = 1; i < numNodes; i++) coefficient[i] = (1 << i);

		int[] connected = new int[numNodes];
		for(Edge edge : edgeList) {
			edge.setEdgeA(edge.getEdgeA()-1);
			edge.setEdgeB(edge.getEdgeB()-1);
			connected[edge.getEdgeA()] |= coefficient[edge.getEdgeB()];
			connected[edge.getEdgeB()] |= coefficient[edge.getEdgeA()];
		}
		int base = 1;
		int result = 0;
		base <<= numNodes;
		int tmp = 0;
		int x, y;
		int ones;
		List<Integer> zeros = new ArrayList<>(32);

		boolean good = true;
		for(int i = 1; i <= base; i++) {
			tmp = i;
			x = y = 0;
			good = true;
			ones = 0;
			zeros.clear();
			for(int j = 0; j < numNodes; j++) {
				if(tmp%2 == 1) {
					if((ones & connected[j]) != 0) {
						good = false;
						break;
					}
					x++;
					ones += coefficient[j];
				} else {
					zeros.add(j);
				}
				tmp >>= 1;
			}
			if(!good) continue;
			for(int j = 0; j < zeros.size(); j++) {
				if((ones & connected[zeros.get(j)]) != 0) {
					y++;
				}
			}
			if(x-y > result) result = x-y;
		}
		return result;
	}

}
