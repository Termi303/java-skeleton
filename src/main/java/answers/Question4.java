package answers;

public class Question4 {

	public static int selectionFailedTradedesks(String[][] rows, int numberMachines) {
		int bigNumber = 10000007;
		int result = bigNumber;
		int[][] arr = new int[rows.length][rows[0].length];
		for(int i = 0; i < rows.length; i++) {
			for(int j = 0; j < rows[i].length; j++) {
				if(rows[i][j].equals("X")) arr[i][j] = bigNumber;
				else arr[i][j] = Integer.parseInt(rows[i][j]);
			}
		}

		for(int i = 0; i < arr.length; i++) {
			int sum = 0;
			for(int j = 0; j < numberMachines; j++) {
				sum += arr[i][j];
			}
			if(sum < result) result = sum;
			for(int j = numberMachines; j < arr[i].length; j++) {
				sum -= arr[i][j-numberMachines];
				sum += arr[i][j];
				if(sum < result) result = sum;
			}
		}
		return result;
	}

}
