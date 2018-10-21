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
		return (portfolios.length >= 101) ? trie(portfolios) : brute(portfolios);
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
		int[] coefficient = new int[16];
		coefficient[0] = 1;
		for(int i = 1; i < 16; i++) coefficient[i] = coefficient[i-1]*2;
		int result = 0;
		int tmp = 0;
		boolean[] dist = new boolean[16];
		Node root = new Node();
		Node node = root;
		Node insertNode = root;
		for(int x : portfolios) {
			tmp = x;
			for(int j = 0; j < 16; j++) {
				dist[j] = (tmp%2 == 1) ? true : false;
				tmp >>= 1;
			}
			node = root;
			insertNode = root;
			tmp = 0;
			for(int i = 15; i >= 0; i--) {
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
