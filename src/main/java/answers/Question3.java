package answers;

import helpers.Edge;
import java.util.ArrayList;
import java.util.List;

public class Question3 {

	public static int lowestExposureToExchanges(int numNodes, Edge[] edgeList) {
		return optimal(numNodes, edgeList);
	}

	public static int bruteforce(int numNodes, Edge[] edgeList) {
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

		int result = 0;
		int perm, stop, tmp;
		int x, y;
		boolean good;
		for(int i = numNodes; i > result; i--) {
			perm = (1 << i) - 1;
			if(i == numNodes) stop = perm;
			else stop = ((1 << numNodes) - 1) ^ ((1 << (numNodes - i)) - 1);
			while(true) {
				//Check correctness
				tmp = perm;
				good = true;
				x = y = 0;
				for(int j = 0; j < numNodes; j++) {
					if(tmp%2 == 1) {
						if((connected[j] & perm) != 0) {
							good = false;
							break;
						}
						x++;
					} else {
						if((connected[j] & perm) != 0) y++;
					}
					tmp >>= 1;
				}
				if(good && x-y > result) result = x-y;
				//Go to next loop?
				if(perm == stop) break;
				perm = nextPermutation(perm);
			}
		}
		return result;
	}

	private static int nextPermutation(int current) {
		int tmp = (current | (current-1)) + 1;
		return tmp | ((((tmp & -tmp) / (current & -current)) >> 1) - 1);
	}
}
