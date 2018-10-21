package answers;

public class Question4 {

	public static int selectionFailedTradedesks(String[][] rows, int numberMachines) {
		long bigNumber = 1000000000000000007L;
		long result = bigNumber;
		long[][] arr = new long[rows.length][rows[0].length];
		for(int i = 0; i < rows.length; i++) {
			int start = 0, end = 0;
			long sum = 0L;
			while(end < arr[i].length) {
				if(rows[i][end].equals("X")) {
					end++;
					start = end;
					sum = 0L;
					continue;
				}
				arr[i][end] = (long)(Integer.parseInt(rows[i][end]));
				if(end-start == numberMachines) {
					sum = (sum - arr[i][start++] + arr[i][end++]);
				} else {
					sum += arr[i][end++];
				}
				if(end-start == numberMachines && sum < result) result = sum;
			}
		}
		if(result == bigNumber) return 0;
		return (int)result;
	}

}
