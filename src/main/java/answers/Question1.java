package answers;

import java.util.*;

public class Question1 {

	public static class Node {
		Node nodeFalse;
		Node nodeTrue;
		Node getFalse() {
			if(nodeFalse == null) nodeFalse = new Node();
			return nodeFalse;
		}
		Node getTrue() {
			if(nodeTrue == null) nodeTrue = new Node();
			return nodeTrue;
		}
	}

	public static int bestMergedPortfolio(int[] portfolios) {
		return brute(portfolios);
	}

	public static int brute(int[] portfolios) {
		int result = 0;
		Set<Integer> set = new HashSet<>();
		for(int i = 0; i < portfolios.length; i++) {
			set.add(portfolios[i]);
		}
		int[] arr = new int[set.size()];
		int where = 0;
		for(int x : set) {
			arr[where] = x;
			where++;
		}
		for(int i = 0; i < arr.length; i++) {
			for(int j = i+1; j < arr.length; j++) {
				result = Math.max(result, (arr[i]^arr[j]));
			}
		}
		return result;
	}

	public static int optimal(int[] portfolios) {
		int[] coefficient = new int[16];
		coefficient[0] = 1;
		for(int i = 1; i < 16; i++) coefficient[i] = coefficient[i-1] * 2;
		return findResult(portfolios, coefficient);
	}

	private static int findResult(int[] portfolios, int[] coefficient) {
		int mini = (1 << 17);
		int maxi = -1;
		for(int i = 0; i < portfolios.length; i++) {
			maxi = Math.max(maxi, portfolios[i]);
			mini = Math.min(mini, portfolios[i]);
		}
		if(maxi < 2) {
			return (maxi == mini) ? 0 : 1;
		}

		int minPower = 0;
		int maxPower = 0;
		while(minPower < 15 && coefficient[minPower+1] <= mini) minPower++;
		while(maxPower < 15 && coefficient[maxPower+1] <= maxi) maxPower++;
		if(maxPower == minPower) {
			for(int i = 0; i < portfolios.length; i++) {
				portfolios[i] &= ~(coefficient[maxPower]);
			}
			return findResult(portfolios, coefficient);
		}
		int result = 0;
		List<Integer> maxList = new ArrayList<>();
		List<Integer> others = new ArrayList<>();
		for(int i = 0; i < portfolios.length; i++) {
			if(portfolios[i] >= coefficient[maxPower]) {
				maxList.add(portfolios[i]);
			} else others.add(portfolios[i]);
		}
		for(int i = maxList.size()-1; i >= 0; i--) {
			for(int j = others.size()-1; j >= 0; j--) {
				result = Math.max(result, (maxList.get(i) ^ others.get(j)));
			}
		}
		return result;
	}

	/*public static int trie(int[] portfolios) {
		int maxElem = 0;
		for(int x = 0; x < portfolios.length; x++) {
			maxElem = Math.max(portfolios[x], maxElem);
		}
		int digits = 0;
		while(maxElem > 0) {
			digits++;
			maxElem >>= 1;
		}
		if(digits == 0) return 0;
		int[] coefficient = new int[digits];
		coefficient[0] = 1;
		for(int i = 1; i < digits; i++) coefficient[i] = coefficient[i-1]*2;
		int result = 0;
		int tmp = 0;
		boolean[] dist = new boolean[digits];
		Node root = new Node();
		Node node = root;
		Node insertNode = root;
		for(int x = 0; x < portfolios.length; x++) {
			tmp = portfolios[x];
			for(int j = 0; j < digits; j++) {
				dist[j] = (tmp%2 == 1) ? true : false;
				tmp >>= 1;
			}
			node = root;
			insertNode = root;
			tmp = 0;
			for(int i = digits-1; i >= 0; i--) {
				if(dist[i]) {
					if(node.nodeFalse != null) {
						tmp |= coefficient[i];
						node = node.nodeFalse;
					} else node = node.getTrue();
				} else {
					if(node.nodeTrue != null) {
						tmp |= coefficient[i];
						node = node.nodeTrue;
					} else node = node.getFalse();
				}
				insertNode = (dist[i]) ? insertNode.getTrue() : insertNode.getFalse();
			}
			result = Math.max(result, tmp);
		}
		return result;
	}*/

}
