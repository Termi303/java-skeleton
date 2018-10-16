package answers;

public class Question4 {

	public static int selectionFailedTradedesks(String[][] rows, int numberMachines) {
		int bigNumber = 1000000007;
		int result = bigNumber;
		int[][] arr = new int[rows.length][rows[0].length];
		for(int i = 0; i < rows.length; i++) {
			for(int j = 0; j < rows[i].length; j++) {
				if(rows[i][j].equals("X")) arr[i][j] = bigNumber;
				else arr[i][j] = Integer.parseInt(rows[i][j]);
			}
		}
		for(int i = 0; i < arr.length; i++) {
			int start = 0, end = 0;
			int sum = 0;
			while(end < arr[i].length) {
				if(rows[i][j].equals("X")) {
					end++;
					start = end;
					sum = 0;
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
		return result;
	}

}
