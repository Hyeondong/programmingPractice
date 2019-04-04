import java.util.*;

public class Bj_14500_2 {
	private static Scanner sc = null;
	private static int N,M = 0;
	private static int[][] arr = null;
	private static int[][] visit = null;
	private static int[] dy = {0,1,0,-1};
	private static int[] dx = {1,0,-1,0};
	private static int ans = 0;
	
	
	public static void main(String args[]) {
		init();
		solution();
		System.out.println(ans);
	}
	private static void solution() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				getMax(i, j);
			}
		}
	}
	private static void getMax(int i, int j) {
		dfs(i,j, 0, 0);
		for(int cnt=0;cnt<4;cnt++) {
			int sum = arr[i][j];
			for(int d=0;d<3;d++) {
				int n_i = i+dy[(d+cnt)%4];
				int n_j = j+dx[(d+cnt)%4];
				
				if(isOut(n_i,n_j)) {
					break;
				}
				sum += arr[n_i][n_j];
			}
			if(ans < sum)
				ans = sum;
		}
	}
	private static boolean isOut(int i, int j) {
		if(i < 0 || j < 0 || i>=N || j >=M) return true;
		return false;
	}
	
	private static void dfs(int i, int j, int cnt, int sum) {
		if(isOut(i,j) || visit[i][j]==1) return;
		
		if(cnt == 4) {
			if(ans < sum)
				ans = sum;
			return;
		}
		
		for(int d=0;d<4;d++) {
			visit[i][j] = 1;
			dfs(i+dy[d], j+dx[d], cnt+1, sum+arr[i][j]);
			visit[i][j] = 0;
		}
		
	}
	
	
	private static void init() {
		sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		arr= new int[N][M];
		visit = new int[N][M];
		for(int i=0;i<N;i++)
			for(int j=0;j<M;j++)
				arr[i][j] = sc.nextInt();
	}
	
}
