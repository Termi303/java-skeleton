package answers;
import java.util.*;

public class Question5 {

	public static int shareExchange(int[] allowedAllocations, int totalValue) {
		int[] result = new int[totalValue+1];
		//Arrays.sort(allowedAllocations);
		for(int i = 1; i <= totalValue; i++) {
			result[i] = 1000000007;
		}
		for(int val : allowedAllocations) {
			for(int i = 0; i+val <= totalValue; i++) {
				if(result[i]+1 < result[i+val]) {
					result[i+val] = result[i]+1;
				}
			}
		}
		return result[totalValue];
	}

}
