import java.util.*;

public class SwExpert5656 {
	private static Scanner sc = null;
	private static int[] dy = {-1, 0, 1, 0};
	private static int[] dx = {0, 1, 0, -1};
	private static int N, W, H = 0;
	private static int[][] arr = null;
	private static int[][] map = null;
	private static int[] ball_idx = null;
	private static int result = 0;
	private static int ans = Integer.MAX_VALUE;
	
	
	
	public static void main(String args[]) {
		sc = new Scanner(System.in);
		int tc = sc.nextInt();
		
		for(int i=1; i<=tc; i++) {
			init();
			solution();
			System.out.println("#"+i+" "+ans);
			ans = Integer.MAX_VALUE;
		}
		
	}
	
	private static void solution() {
		
		//process();
		combi(0);
	}
	
	private static void combi(int cnt) {
		if(cnt == N) {
			mapCopy();
			process();
			return;
		}
		
		
		for(int i = 0; i<W;i++) {
			ball_idx[cnt] = i;
			combi(cnt+1);
		}
	}
	
	private static void mapCopy() {
		for(int i=0;i<H;i++) {
			for(int j=0;j<W;j++) {
				arr[i][j] = map[i][j];
			}
		}
	}
	
	private static int getResult() {
		int cnt=0;
		for(int i=0;i<H;i++) {
			for(int j=0;j<W;j++) {
				if(arr[i][j]!=0) cnt++;
			}
		}
		return cnt;
	}
	
	private static void process() {
		for(int j=0;j<N;j++) {
			for(int i=0;i<H;i++) {
				if(arr[i][ball_idx[j]] != 0) {
					//벽돌 깨기
					//System.out.println(i+", "+ball_idx[j]);
					break_brick(i,ball_idx[j],arr[i][ball_idx[j]]);
					//벽돌 재정비
					arrange_brick();
					break;
				}
			}
		}
		
		int temp = getResult();
		ans = Math.min(temp, ans);
	}
	
	private static void arrange_brick() {
		for(int j=0;j<W;j++) {
			int[] temp = new int[H];
			for(int i=H-1;i>=0;i--) {
				temp[i] = arr[i][j];
				arr[i][j]=0;
			}
			int k = H-1;
			for(int i=H-1;i>=0;i--) {
				if(temp[i] != 0) {
					arr[k--][j] = temp[i];
				}
			}
		}
	}
	
	private static void break_brick(int i, int j, int n) {
		for(int d=0;d<4;d++) {
			for(int k=0;k<n;k++) {
				int n_i = i+dy[d]*k;
				int n_j = j+dx[d]*k;
				
				if(isOut(n_i,n_j)) continue;
				
				if(arr[n_i][n_j]>1) {
					int temp_num = arr[n_i][n_j];
					arr[n_i][n_j] = 0;
					break_brick(n_i,n_j, temp_num);
					
				}
				else {
					arr[n_i][n_j] = 0;
				}
			}
		}
	}
	
	private static boolean isOut(int i, int j) {
		if(i<0 || j<0 || i>=H || j>=W) return true;
		else return false;
	}
	
	private static void init() {
		N = sc.nextInt();
		W = sc.nextInt();
		H = sc.nextInt();
		
		arr = new int[H][W];
		map = new int[H][W];
		ball_idx = new int[N];
		
		for(int i=0;i<H;i++) {
			for(int j=0;j<W;j++) {
				map[i][j] = sc.nextInt();
			}
		}
	}
}
