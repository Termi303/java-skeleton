package answers;
import java.util.*;

public class Question5 {

	public static int shareExchange(int[] allowedAllocations, int totalValue) {
		int[] result = new int[totalValue+1];
		int bigNum = 1000000007;
		//Arrays.sort(allowedAllocations);
		for(int i = 1; i <= totalValue; i++) {
			result[i] = bigNum;
		}
		for(int val : allowedAllocations) {
			for(int i = 0; i+val <= totalValue; i++) {
				if(result[i]+1 < result[i+val]) {
					result[i+val] = result[i]+1;
				}
			}
		}
		if(result[totalValue] == bigNum) return 0;
		return result[totalValue];
	}

}
