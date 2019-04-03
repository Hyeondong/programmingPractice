/*
 * 시간 개오래걸림
 * 마지막에 전체 다돌면서 하지말고
 * 미리 저장해뒀다가 한번에 list에서 끄집어내면서 출력하면 괜찮을듯
 */
import java.util.*;

public class Bj_14889 {
	private static Scanner sc = null;
	private static int N = 0;
	private static int[][] arr = null;
	private static int[] visit = null;
	private static int ans = Integer.MAX_VALUE;
	
	
	public static void main(String args[]) {
		solution();
	}
	
	
	private static void solution() {
		init();
		dfs(0,0);
		
		System.out.println(ans);
	}
	
	private static int getResult(int[] visit) {
		ArrayList<Integer> team1_list = new ArrayList<Integer>();
		ArrayList<Integer> team2_list = new ArrayList<Integer>();
		int team1 = 0;
		int team2 = 0;
		
		for(int i=0;i<N;i++) {
			if(visit[i] == 1) team1_list.add(i);
			else team2_list.add(i);
		}
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(team1_list.contains(i) && team1_list.contains(j)) {
					team1 += arr[i][j];
				}
				else if(team2_list.contains(i) && team2_list.contains(j)) {
					team2 += arr[i][j];
				}
			}
		}
		return Math.abs(team1 - team2);
	}
	
	
	private static void dfs(int idx, int cnt) {
		if(cnt == N/2) {
			int result = getResult(visit);
			if(ans > result)
				ans = result;
			
			return;
		}
		
		for(int i = idx;i<N;i++) {
			visit[i] = 1;
			dfs(i+1, cnt+1);
			visit[i] = 0;
		}
		
	}
	
	
	private static void init() {
		sc = new Scanner(System.in);
		N = sc.nextInt();
		
		arr = new int[N][N];
		visit = new int[N];
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				arr[i][j]=sc.nextInt();
				
			}
		}
	}
}
