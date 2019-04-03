import java.util.*;
public class Bj_14503 {
	private static Scanner sc = null;
	private static int N, M = 0;
	private static int[] dy = {-1,0,1,0};
	private static int[] dx = {0,-1,0,1};
	private static int[][] arr = null;
	private static int r,c,d, ans = 0;
	
	public static void main(String args[]) {
		init();
		solution();
		System.out.println(ans);
	}
	private static void solution() {
		int n_r = 0;
		int n_c = 0;
		
		while(true) {
			arr[r][c] = 2;
			ans++;
			
			boolean flag = false;
			for(int i=0;i<4;i++) {
				n_r = r + dy[(d+1)%4];
				n_c = c + dx[(d+1)%4];
				
				if(!isOut(n_r,n_c) && arr[n_r][n_c]==0) {
					r=n_r;
					c=n_c;
					d = (d+1)%4;
					flag = true;
					break;
				}
				d = (d+1)%4;	
			}
			if(!flag) {
				n_r = r+dy[(d+2)%4];
				n_c = c+dx[(d+2)%4];
				if(isOut(n_r,n_c)) return;
				r = n_r;
				c = n_c;
				ans--;
			}
			
		}
		
	}
	
	private static boolean isOut(int i, int j) {
		if(i<0 || j < 0 || i>=N || j >=M || arr[i][j]==1) return true;
		else return false;
	}
	
	private static void init() {
		sc = new Scanner(System.in);
		
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		arr = new int[N][M];
		
		r = sc.nextInt();
		c = sc.nextInt();
		d = sc.nextInt();
		
		if(d == 1) d=3;
		else if(d==3) d=1;
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
	}
	
}
