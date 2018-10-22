package answers;

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
		return trie(portfolios);
	}

	public static int brute(int[] portfolios) {
		int result = 0;
		for(int i = 0; i < portfolios.length; i++) {
			for(int j = i+1; j < portfolios.length; j++) {
				result = Math.max(result, (portfolios[i]^portfolios[j]));
			}
		}
		return result;
	}

	public static int trie(int[] portfolios) {
		int maxElem = 0;
		for(int x : portfolios) {
			maxElem = Math.max(x, maxElem);
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
		for(int x : portfolios) {
			tmp = x;
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
						tmp += coefficient[i];
						node = node.nodeFalse;
					} else node = node.getTrue();
				} else {
					if(node.nodeTrue != null) {
						tmp += coefficient[i];
						node = node.nodeTrue;
					} else node = node.getFalse();
				}
				insertNode = (dist[i]) ? insertNode.getTrue() : insertNode.getFalse();
			}
			result = Math.max(result, tmp);
		}
		return result;
	}

}
