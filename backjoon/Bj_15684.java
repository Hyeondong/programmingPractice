import java.util.*;

public class Bj_15684 {
	private static Scanner sc = null;
	private static int N, M, H = 0;
	private static int[][] arr = null;
	private static int ans = 4;
	public static void main(String args[]) {
		init();
		dfs(1,1,0);
		System.out.println(ans == 4 ? -1 : ans);
		ans = 4;
	}
	
	private static void check(int cnt) {
		boolean isOk = true;
		
		for(int j = 0;j<=N-2;j=j+2) {
			int x = j;
			int i = 1;
			while(i<H) {
				if(x-1>0 && arr[i][x-1] == 1) {
					x=x-2;
				}
				else if(x+2 < N && arr[i][x+1]==1) {
					x=x+2;
				}
				i++;
			}
			
			if(x != j) {
				isOk = false;
				break;
			}
		}
		
		if(isOk) {
			ans = Math.min(ans, cnt);
		}
	}
	
	private static void dfs(int i, int j, int cnt) {
		if(ans <= cnt) return;

		check(cnt);
		if(ans == 3) return;
		
		for(int y = i; y<H;y++) {
			for(int x = j; x<N-1;x=x+2) {
				if(arr[y][x] == 0) {
					if(x-2 > 0 && x+2 < N && arr[y][x-2]==0 && arr[y][x+2] == 0) {
						arr[y][x] = 1;
						dfs(y,x,cnt+1);
						arr[y][x] = 0;
					}
					else if(x-2 < 0 && x+2 <= N-1 && arr[y][x+2] == 0) {
						arr[y][x] = 1;
						dfs(y,x,cnt+1);
						arr[y][x] = 0;
					}
					else if(x+2 >= N-1 && x-2 > 0 && arr[y][x-2]==0) {
						arr[y][x] = 1;
						dfs(y,x,cnt+1);
						arr[y][x] = 0;
					}
					
				}
			}
			j=1;
		}
	}
	
	
	
	private static void init() {
		sc = new Scanner(System.in);
		
		N = sc.nextInt();
		N = N*2;
		M = sc.nextInt();
		H = sc.nextInt();
		H = H+1;
		
		arr = new int[H][N];
		
		for(int i=0;i<M;i++) 
			arr[sc.nextInt()][sc.nextInt()*2 - 1] = 1;
	}
}



//import java.util.*;
//public class Bj_15684 {
//	private static Scanner sc = null;
//	private static int N = 0;
//	private static int M = 0;
//	private static int H = 0;
//	private static int arr[][] = null;
//	private static int answer = 4;
//	private static ArrayList<Pos> list = null;
//	
//	public static void main(String args[]) {
//		System.out.println(solution());
//	}
//	
//	
//	private static int solution() {
//		int result=0;
//		
//		init();
//		
//		for(int i = 0; i<4;i++) {
//			dfs(0,0,i);
//		}
//		result = answer;
//		return result;
//	}
//	 
//	
//	private static void dfs(int idx, int cnt, int max) {
//		
//		if(cnt == max) {
//			//System.out.println(cnt);
//			if(isFinal()) {
//				System.out.println(cnt);
//				if(answer > cnt) {
//					answer = cnt;
//				}
//			}
//			return;
//		}
//		
//		for(int i = idx; i<list.size();i++) {
//				if(!isIdxOutOfBound(list.get(i).i, list.get(i).j+2) && arr[list.get(i).i][list.get(i).j+2] == 0) {
//					arr[list.get(i).i][list.get(i).j] = 1;
//					dfs(i+1, cnt+1, max);
//					arr[list.get(i).i][list.get(i).j] = 0;
//				}
//		}
//	}
//	
//	private static boolean isFinal() {
//		int n_y=0;
//		int n_x=0;
//		
//		for(int i=0;i<N-2; i++) {
//			if(i%2 == 0) {
//				n_y = 0;
//				n_x = i;
//				while(true) {
//					if(n_y == H-1) break;
//					if(!isIdxOutOfBound(n_y, n_x+1) && arr[n_y][n_x+1] == 1) { 
//						n_x += 2;
//					}
//					else if(!isIdxOutOfBound(n_y, n_x-1) && arr[n_y][n_x-1] == 1) { 
//						n_x -= 2;
//					}
//					n_y +=1;
//				}
//			}
//			if(n_x != i) return false;
//		}
//		
//		return true;
//	}
//	private static boolean isIdxOutOfBound(int i, int j) {
//		if(i<0 || j<0 || i>=H || j >= N) return true;
//		return false;
//	}
//	
//	private static void init() {
//		
//		sc = new Scanner(System.in);
//		
//		N = sc.nextInt();
//		M = sc.nextInt();
//		H = sc.nextInt();
//		H += 1;
//		N = 2*N - 1;
//		
//		arr = new int[H][N];
//		
//		for(int i=0;i<H;i++) {
//			for(int j=0;j<N;j++) {
//				if(j%2 ==0) {
//					arr[i][j]=1;
//				}
//			}
//		}
//		
//		for(int i = 0; i<M;i++) {
//			int a = sc.nextInt();
//			int b = sc.nextInt();
//			
//			arr[a-1][(b-1)*2+1]=1;
//		}
//		
//
//		list = new ArrayList<Pos>();
//		
//		for(int i=0;i<H-1;i++) {
//			for(int j=0;j<N;j++) {
//				if(arr[i][j]==0) 
//					list.add(new Pos(i, j, 0));
//			}
//		}
//		
//	}
//	
//	private static class Pos {
//		int i,j;
//		int visit;
//		public Pos(int i, int j, int visit) {
//			this.i = i;
//			this.j = j;
//			this.visit = visit;
//		}
//	}
//}
