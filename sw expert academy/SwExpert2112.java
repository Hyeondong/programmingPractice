import java.util.*;

public class SwExpert2112 {
	private static Scanner sc = null;
	private static int D,W,K;
	private static int[][] arr = null;
	private static boolean[] visit = null;
	private static int ans=0;
	private static Stack<Integer> stack = null;
	
	public static void main(String args[]) {
		sc = new Scanner(System.in);
		int tc = sc.nextInt();
		
		for(int i=1;i<=tc;i++) {
			init();
			//if(check()) System.out.println("true");
			ans=D+1;
			solution();
			
			System.out.println("#"+i+" "+ans);
			
		}
	}
	
	private static void insertMedicine(int idx, int cnt) {
		if(ans <= cnt) return;
		if(cnt >= stack.size()) {
//			for(int i : stack) {
//				System.out.print(i+" ");
//			}
//			System.out.println();
			int[][] temp_arr = new int[stack.size()][W];
			int temp_idx = 0;
			
			for(int i=0;i<D;i++) {
				if(visit[i]) {
					for(int j=0;j<W;j++) {
						temp_arr[temp_idx][j] = arr[i][j];
						arr[i][j] = stack.get(temp_idx);
					}
					temp_idx++;
				}
			}
			if(check())
				ans = stack.size();
			else {
				temp_idx = 0;
				for(int i=0;i<D;i++) {
					if(visit[i]) {
						for(int j=0;j<W;j++) {
							arr[i][j] = temp_arr[temp_idx][j];
						}
						temp_idx++;
					}
				}
			}
			return;
		}
		
		
		for(int i=idx;i<D;i++) {
			if(!visit[i]) {
				visit[i] = true;
				insertMedicine(i+1, cnt+1);
				visit[i] = false;
			}
		}
	}
	
	
	private static void dfs(int cnt, int max) {
		if(ans == cnt) return;
		//ans랑 비교해서 중간에 끊어줄만한 예외처리 해주는게 좋을듯
		if(cnt > max) {
			
			insertMedicine(0,0);
			return;
		}

		
		for(int i=0;i<2;i++) {
			stack.push(i);
			dfs(cnt+1, max);
			stack.pop();
		}
	}
	
	
	private static void solution() {
		if(check()) {
			ans = 0;
			return;
		}
		for(int cnt=0;cnt<D;cnt++) {  
			dfs(0,cnt);
		}
	}
	
	
	
	private static boolean check() {
		for(int j=0;j<W;j++) {
			int pre = arr[0][j];
			int cnt = 1;
			for(int i=1;i<D;i++) {
				if(pre == arr[i][j]) {
					cnt++;
				}
				else {
					cnt=1;
					pre = arr[i][j];
				}
				if(cnt >= K) {
					break;
				}
			}
			if(cnt<K) 
				return false;
		}
		return true;
	}
	
	
	private static void init() {
		D = sc.nextInt();
		W = sc.nextInt();
		K = sc.nextInt();
		
		arr = new int[D][W];
		visit = new boolean[D];
		
		stack = new Stack<Integer>();
		
		for(int i=0;i<D;i++) {
			for(int j=0;j<W;j++) {
				arr[i][j] = sc.nextInt();
			}
		}
	}
}