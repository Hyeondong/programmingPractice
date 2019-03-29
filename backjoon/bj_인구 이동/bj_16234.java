/*
 * 19.3.29 16:56 시작 / 17:20 ~ 17:27 아빠전화 / 18:37 완료
*/

import java.util.*;
public class bj_16234 {
	private static int N,L,R;
	private static int[][] arr = null;
	private static boolean[][] visit = null;
	private static Pos[] dir = null;
	private static int totalPerson = 0;
	private static Queue<Pos> q = null;
	
	
	public static void main(String args[]) {
		dataInput();
		System.out.println(solution());
	}
	
	private static int solution() {
		int cnt=0;
		
		while(true) {
			//4방향 탐색
			for(int i=0;i<N;i+=2) {
				for(int j=0;j<N;j+=2) {
					visit[i][j] = true;
					for(int k = 0; k<4;k++) {
						int n_i = i + (dir[k].i*2);
						int n_j = j + (dir[k].j*2);
						
						if(isIdxOutOfBound(n_i,n_j) || visit[n_i][n_j]) continue;
						
						int diff = Math.abs(arr[i][j]-arr[n_i][n_j]);
						if(diff>=L && diff <= R) 
							arr[i+dir[k].i][j+dir[k].j] = 1;
					}
				}
			}
			//종료조건
			if(isFinal()) return cnt;
			
			visitClear();
			//갈 수 있는길 dfs로 탐색 후 인구 분배
			for(int i=0;i<N;i+=2) {
				for(int j=0;j<N;j+=2) {
					dfs(i,j);
					int dividePerson = totalPerson/q.size();
					
					while(!q.isEmpty()) {
						Pos item = q.poll();
						arr[item.i][item.j] = dividePerson;
					}
					totalPerson = 0;
				}
			}
			
			openRoadClear();
			visitClear();
			cnt++;
		}
	}
	
	private static void dfs(int i, int j) {
		visit[i][j]=true;
		q.offer(new Pos(i,j));
		totalPerson += arr[i][j];
		
		for(int k = 0;k<4;k++) {
			int n_i = i+dir[k].i*2;
			int n_j = j+dir[k].j*2;
			
			if(isIdxOutOfBound(n_i,n_j) || visit[n_i][n_j]) continue;
			
			if(arr[i+dir[k].i][j+dir[k].j]==1)
				dfs(n_i, n_j);
		}
	}
	
	
	private static void visitClear() {
		for(int i=0;i<N;i++)
			for(int j=0;j<N;j++)
				visit[i][j] = false;
	}
	
	private static void openRoadClear() {
		for(int i=0;i<N;i++) 
			for(int j=0;j<N;j++) 
				if(i%2==1 || j%2==1)
					arr[i][j]=0;
	}
	
	private static boolean isFinal() {
		for(int i=0;i<N;i++) 
			for(int j=0;j<N;j++) 
				if(i%2==1 || j%2==1) 
					if(arr[i][j]==1) 
						return false;
		return true;
	}
	
	private static boolean isIdxOutOfBound(int i, int j) {
		if(i<0 || j<0 || j>=N || i >=N) return true;
		return false;
	}
	private static void dataInput() {
		Scanner sc = new Scanner(System.in);
		dir = new Pos[4];
		dir[0] = new Pos(-1,0);
		dir[1] = new Pos(0,1);
		dir[2] = new Pos(1,0);
		dir[3] = new Pos(0,-1);
		
		N = sc.nextInt();
		L = sc.nextInt();
		R = sc.nextInt();
		N = 2*N;
		
		arr = new int[N][N];
		visit = new boolean[N][N];
		q = new LinkedList<Pos>();
		
		for(int i=0;i<N;i++) 
			for(int j=0;j<N;j++) 
				if(i%2 == 0 && j%2 == 0) arr[i][j] = sc.nextInt();
	}
	
	private static class Pos{
		int i,j;
		public Pos(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}

