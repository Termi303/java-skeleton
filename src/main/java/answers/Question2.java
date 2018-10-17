package answers;
import java.util.*;

public class Question2 {

	public static int equallyBalancedCashFlow(int[] cashflowIn, int[] cashflowOut) {
		boolean[][] arr = new boolean[100007][2];
		//Arrays.sort(allowedAllocations);
		arr[0][0] = arr[0][1] = true;
		int sumIn = 0;
		for(int j : cashflowIn) sumIn += j;
		for(int val : cashflowIn) {
			for(int i = sumIn-val; i >= 0; i--) {
				if(arr[i][0]) {
					arr[i+val][0] = true;
				}
			}
		}
		int sumOut = 0;
		for(int j : cashflowOut) sumOut += j;
		for(int val : cashflowOut) {
			for(int i = sumOut-val; i >= 0; i--) {
				if(arr[i][1]) {
					arr[i+val][1] = true;
				}
			}
		}
		int bigger = Math.max(sumIn, sumOut);
		List<Integer> listIn = new ArrayList<>();
		List<Integer> listOut = new ArrayList<>();
		for(int i = 1; i <= bigger; i++) {
			if(arr[i][0] == true && arr[i][1] == true) return 0;
			if(arr[i][0] == true) {
				listIn.add(i);
			} else if(arr[i][1] == true) {
				listOut.add(i);
			}
		}
		int result = 2147483647;
		int iterIn = 0;
		int iterOut = 0;
		while(iterIn < listIn.size() && iterOut < listOut.size()) {
			int alfa = listIn.get(iterIn);
			int beta = listOut.get(iterOut);
			if(Math.abs(alfa-beta) < result) result = Math.abs(alfa-beta);
			if(alfa > beta) iterOut++;
			else iterIn++;
		}
		return result;
	}

}
