import java.util.*;

public class SwExpert1949 {
	private static Scanner sc = null;
	private static int[][] arr = null;
	private static boolean[][] visit = null;
	private static int tc, N, K = 0;
	private static ArrayList<Node> list = null;
	private static Node[] dir = null;
	private static int max=0;
	private static int ans=0;


	public static void main(String args[]) {
		sc = new Scanner(System.in);
		int TC = tc = sc.nextInt();
		dir = new Node[4];
		dir[0] = new Node(0,1);
		dir[1] = new Node(1,0);
		dir[2] = new Node(0,-1);
		dir[3] = new Node(-1,0);


		for(int t=1;t<=tc;t++){
			N = sc.nextInt();
			K = sc.nextInt();
			list = new ArrayList<Node>();
			arr = new int[N][N];
			visit = new boolean[N][N];

			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					arr[i][j] = sc.nextInt();
					if(max == arr[i][j]) {
						list.add(new Node(i,j));
					}
					else if(max<arr[i][j]) {
						max = arr[i][j];
						list.clear();
						list.add(new Node(i,j));
					}
				}
			}


			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					for(int k=0;k<=K;k++) {

						arr[i][j] -= k;
						for(Node item : list) {
							if(arr[item.i][item.j]<max) continue;
							//System.out.println(item.i+", "+item.j);
							dfs(item.i, item.j, 0, max+1);
						}
						arr[i][j] += k;
					}
				}
			}

			System.out.println("#"+t+" "+ans);
			ans=0;
			max=0;
		}
	}

	private static void dfs(int i, int j, int cnt, int num) {

		if(isOut(i,j) || arr[i][j] >= num) {
			if(ans < cnt)
				ans = cnt;
			return;
		}

		for(int d=0;d<4;d++) {
			visit[i][j] = true;
			dfs(i+dir[d].i, j+dir[d].j, cnt+1, arr[i][j]);
			visit[i][j] = false;
		}
	}

	private static boolean isOut(int i, int j) {
		if(i<0 || j < 0 || i>=N || j >=N || visit[i][j]) return true;
		else return false;
	}

	private static class Node {
		int i,j;
		public Node(int i, int j) {
			this.i=i;
			this.j=j;
		}
	}
}
