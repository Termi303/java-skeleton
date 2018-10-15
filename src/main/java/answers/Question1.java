package answers;

public class Question1 {

	public static int bestMergedPortfolio(int[] portfolios) {
		int result = 0;
		for(int i = 0; i < portfolios.length; i++) {
			for(int j = i+1; j < portfolios.length; j++) {
				if( (portfolios[i]^portfolios[j]) > result ) {
					result = (portfolios[i]^portfolios[j]);
				}
			}
		}
		return result;
	}

}
