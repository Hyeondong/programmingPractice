import java.util.*;

public class SwExpert5650 {
	private static Scanner sc = null;
	private static int N = 0;
	private static int[][] map = null;
	private static Node[][] w_hole = null;
	private static int startY, startX = 0;
	private static int d = 0;
	private static int[] dy = {-1, 0, 1, 0};
	private static int[] dx = {0, 1, 0, -1};
	private static int ans = 0;
	
	
	public static void main(String args[]){
		sc = new Scanner(System.in);
		int TC = sc.nextInt();
		for(int i=1;i<=TC;i++) {
			init();
			solution();
			System.out.println("#"+i+" "+ans);
			ans = 0;
		}
	}
	
	private static void solution() {
		for(int i=1;i<N-1;i++) {
			for(int j=1;j<N-1;j++) {
				if(map[i][j]==0) {
					for(int dir = 0;dir<4;dir++) {
						int result = process(i,j,dir);
						if(ans < result) ans = result;
					}
				}
			}
		}
	}
	private static int process(int i, int j, int dir) {
		startY = i;
		startX = j;
		d = dir;
		
		int cnt = 0;
		while(true) {
	
			//ºó°ø°£ÀÎ°æ¿ì
			if(map[i][j] == 0) {
				i = i+dy[d];
				j = j+dx[d];
			}
			//ºí·ÏÀÎ°æ¿ì
			else if(1<=map[i][j] && map[i][j] <=5) {
				d = block(map[i][j], d);
				i = i+dy[d];
				j = j+dx[d];
				cnt++;
			}
			//¿úÈ¦ÀÎ °æ¿ì
			else if(6<=map[i][j] && map[i][j] <= 10) {
				int temp_i;
				int temp_j;
				if(w_hole[0][map[i][j]-6].i == i && w_hole[0][map[i][j]-6].j == j) {
					temp_i = w_hole[1][map[i][j]-6].i + dy[d];
					temp_j = w_hole[1][map[i][j]-6].j + dx[d];
					i = temp_i;
					j = temp_j;
				}
				else {
					temp_i = w_hole[0][map[i][j]-6].i + dy[d];
					temp_j = w_hole[0][map[i][j]-6].j + dx[d];
					i = temp_i;
					j = temp_j;
				}
			}
			
			if(map[i][j]==-1 || (startY==i && startX == j))
				break;
			
		}
		return cnt;
	}
	
	private static int block(int block, int d){
		switch(block) {
		case 1:
			if(d==0 || d==1)
				return (d+2)%4;
			else if(d==2)
				return (d+3)%4;
			else if(d==3)
				return (d+1)%4;
			break;
		case 2:
			if(d==1 || d==2)
				return (d+2)%4;
			else if(d==3)
				return (d+3)%4;
			else if(d==0)
				return (d+1)%4;
			break;
		case 3:
			if(d==2 || d==3)
				return (d+2)%4;
			else if(d==0)
				return (d+3)%4;
			else if(d==1)
				return (d+1)%4;
			break;
		case 4:
			if(d==0 || d==3)
				return (d+2)%4;
			else if(d==1)
				return (d+3)%4;
			else if(d==2)
				return (d+1)%4;
			break;
		case 5:
			return (d+2)%4;
		}
		return -1;
	}
	
	private static void init() {
		N = sc.nextInt();
		N = N+2;
		
		map = new int[N][N];
		w_hole = new Node[2][5];

		for(int i=1;i<N-1;i++) {
			for(int j=1;j<N-1;j++) {
				map[i][j] = sc.nextInt();
				if(6<=map[i][j] && map[i][j] <= 10) {
					if(w_hole[0][map[i][j]-6] == null)
						w_hole[0][map[i][j]-6] = new Node(i,j);
					else
						w_hole[1][map[i][j]-6] = new Node(i,j);
				}
			}
		}
		for(int i=0;i<N;i++) {
			map[0][i] = 5;
			map[i][0] = 5;
			map[N-1][i] = 5;
			map[i][N-1] = 5;
		}
	}
	
	
	private static class Node {
		int i, j;

		public Node(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
