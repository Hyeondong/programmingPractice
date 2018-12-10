import java.util.*;

public class SwExpert3146 {
	private static Scanner sc = null;
	
	public static void main(String args[]) {
		sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc = 1; tc<=T;tc++) {
			int[] arr = new int[sc.nextInt()];
			for(int i=0;i<arr.length;i++)
				arr[i] = sc.nextInt();
			System.out.println(solution(arr));
		}
	}
	private static int solution(int[] arr) {
		int size = arr.length;
		int[][] dp = new int[size][size];
		int[] sumArr = new int[size];
		
		sumArr[0] = arr[0];
		for(int i=1;i<size;i++) 
			sumArr[i] = sumArr[i-1]+arr[i];
		
		for(int i=0;i<size-1;i++)
			dp[i][i+1] = arr[i]+arr[i+1];
		
		for(int d = 2; d<size;d++) {
			for(int i=0;i+d<size;i++) {
				int j = i+d;
				dp[i][j] = Integer.MAX_VALUE;
				int p_sum = sum(sumArr, i, j);
				for(int k = i;k<j;k++) {
					dp[i][j] = Math.min(dp[i][k]+dp[k+1][j] + p_sum, dp[i][j]);
				}
			}
		}
		return dp[0][size-1];
	}
	
	private static int sum(int[]s, int i, int j) {
		if(i == 0)  return s[j];
		else 		return s[j] - s[i-1];
	}
}
