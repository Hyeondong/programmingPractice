import java.util.*;
public class Bj_14890 {
	private static Scanner sc = null;
	private static int[][] arr1 = null;
	private static int[][] arr2 = null;

	private static int N,L,ans = 0;
	
	
	public static void main(String args[]) {
		solution();
		System.out.println(ans);
	}
	private static void solution() {
		init();
		
		for(int i=0;i<N;i++) {
			detectRoad(i, arr1);
			detectRoad(i, arr2);
		}
	}
	
	private static void detectRoad(int idx, int[][] arr) {
		int[] visit = new int[N];
		
		for(int i=0;i<N-1;i++) {
			if(arr[idx][i] != arr[idx][i+1]) {

				int diff = arr[idx][i]-arr[idx][i+1];
				
				if(Math.abs(diff) > 1) return;
				
				if(diff == -1) {
					for(int j=0;j<L;j++) {
						if(i-j < 0 || visit[i-j]==1) return;
						
						if(arr[idx][i] == arr[idx][i-j]) visit[i-j]=1;
						else return;
					}
				}
				else if(diff == 1) {
					for(int j=1;j<=L;j++) {
						if(i+j >= N || visit[i+j]==1) return;
						
						if(arr[idx][i] -1 == arr[idx][i+j]) visit[i+j] =1;
						else return;
					}
				}
			}
		}
		ans++;
	}
	
	
	private static void init() {
		sc = new Scanner(System.in);
		
		N = sc.nextInt();
		L = sc.nextInt();
		
		arr1 = new int[N][N];
		arr2 = new int[N][N];
		
		for(int i=0;i<N;i++)
			for(int j=0;j<N;j++)
				arr2[j][i] = arr1[i][j] = sc.nextInt();
	}
}
