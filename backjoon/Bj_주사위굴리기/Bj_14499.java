import java.util.*;
public class Bj_14499 {
	private static Scanner sc = null;
	private static int N,M,K = 0;
	private static int y,x = 0;
	private static int[][] arr= null;
	private static int[] cmd_list= null;
	private static int[] dice = null;
	private static int[] dy = {0,0,-1,1};
	private static int[] dx = {1,-1,0,0};
	
	public static void main(String args[]) {
		init();
		solution();
	}
	
	private static void solution() {
		for(int n : cmd_list) {
			
			int n_y = y+dy[n-1];
			int n_x = x+dx[n-1];
			
			if(isOut(n_y, n_x)) continue;
			
			cmd(n);
			
			y = n_y;
			x = n_x;
			if(arr[y][x]==0) {
				arr[y][x] = dice[6];
			}
			else {
				dice[6] = arr[y][x];
				arr[y][x] = 0;
			}
			System.out.println(dice[1]);
		}
	}
	
	private static boolean isOut(int i, int j) {
		if(i <0 || j<0 || i>=N || j>=M) return true;
		else return false;
	}
	
	private static void cmd(int n) {
		int[] temp = dice.clone();
		
		switch(n) {
		case 1:
			dice[1] = temp[4];
			dice[3] = temp[1];
			dice[4] = temp[6];
			dice[6] = temp[3];
			break;
		case 2:
			dice[1] = temp[3];
			dice[3] = temp[6];
			dice[4] = temp[1];
			dice[6] = temp[4];
			break;
		case 3:
			dice[1] = temp[5];
			dice[2] = temp[1];
			dice[5] = temp[6];
			dice[6] = temp[2];
			break;
		case 4:
			dice[1] = temp[2];
			dice[2] = temp[6];
			dice[5] = temp[1];
			dice[6] = temp[5];
			break;
		}
	}
	
	private static void init() {
		sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		y = sc.nextInt();
		x = sc.nextInt();
		K = sc.nextInt();
		
		arr = new int[N][M];
		cmd_list = new int[K];
		dice = new int[7];
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		for(int i=0;i<K;i++) {
			cmd_list[i] = sc.nextInt();
		}
		
	}
}
