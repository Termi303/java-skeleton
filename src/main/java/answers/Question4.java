package answers;

public class Question4 {

	public static int selectionFailedTradedesks(String[][] rows, int numberMachines) {
		long bigNumber = 1000000000000000007L;
		long result = bigNumber;
		long[][] arr = new long[rows.length][rows[0].length];
		for(int i = 0; i < rows.length; i++) {
			for(int j = 0; j < rows[i].length; j++) {
				if(rows[i][j].equals("X")) arr[i][j] = -1L;
				else arr[i][j] = (long)(Integer.parseInt(rows[i][j]));
			}
		}
		for(int i = 0; i < arr.length; i++) {
			int start = 0, end = 0;
			long sum = 0L;
			while(end < arr[i].length) {
				if(arr[i][end] == -1L) {
					end++;
					start = end;
					sum = 0L;
					continue;
				} else {
					if(end-start == numberMachines) {
						sum = (sum - arr[i][start] + arr[i][end]);
						start++;
						end++;
					} else {
						sum += arr[i][end++];
					}
					if(end-start == numberMachines && sum < result) result = sum;
				}
			}
		}
		return (int)result;
	}

}
