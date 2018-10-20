package answers;
import java.util.*;

public class Question2 {

	private static void processCashflow(int[] cashflow, List<Integer> list) {
		for(int x : cashflow) {
			List<Integer> toAdd = new ArrayList<>();
			if(!list.contains(x)) toAdd.add(x);
			for(int y : list) {
				if(!list.contains(x+y)) toAdd.add(x+y);
			}
			list.addAll(toAdd);
		}
	}

	public static int equallyBalancedCashFlow(int[] cashflowIn, int[] cashflowOut) {
		List<Integer> listIn = new ArrayList<>();
		List<Integer> listOut = new ArrayList<>();

		Arrays.sort(cashflowIn);
		Arrays.sort(cashflowOut);

		processCashflow(cashflowIn, listIn);
		processCashflow(cashflowOut, listOut);

		Collections.sort(listIn);
		Collections.sort(listOut);

		//System.out.println("listIn.size == " + listIn.size());
		//System.out.println("listOut.size == " + listOut.size());

		int result = 2147483647;
		int iterIn = 0;
		int iterOut = 0;
		if(listIn.size() > 0 && result > listIn.get(0)) result = listIn.get(0);
		if(listOut.size() > 0 && result > listOut.get(0)) result = listOut.get(0);
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
