import java.util.*;

public class Bj_14502 {
	private static Scanner sc = null;
	private static int N,M = 0;
	private static int[][] arr = null;
	private static boolean[][] visit = null;
	private static ArrayList<Pos> virus = null;
	private static ArrayList<Pos> empty_list = null;
	private static Stack<Pos> max_wall = null;
	private static Pos[] dir = null;
	private static int ans = 0;
	
	
	public static void main(String args[]) {
		init();
		solution();
		System.out.println(ans);
	}
	
	private static void solution() {
		dfs(0);
	}
	
	private static int getEmpty() {
		int temp_map[][] = new int[N][M];
		int temp_visit[][] = new int[N][M];
		
		copyArray(temp_map, arr);
		
		for(Pos item : max_wall) 
			temp_map[item.i][item.j] = 1;
		
		Queue<Pos> q = new LinkedList<Pos>();
		q.addAll(virus);
		
		while(!q.isEmpty()) {
			Pos item = q.poll();
			for(int i=0;i<4;i++) {
				int n_i = item.i + dir[i].i;
				int n_j = item.j + dir[i].j;
				
				if(n_i < 0 || n_j < 0 || n_i >=N || n_j >= M || temp_map[n_i][n_j]==1 || temp_visit[n_i][n_j] == 1) 
					continue;
				temp_map[n_i][n_j] = 2;
				temp_visit[n_i][n_j] = 1;
				q.offer(new Pos(n_i, n_j,0));
			}
		}
		
		int result = 0;
		for(int i=0;i<N;i++) 
			for(int j =0;j<M;j++)
				if(temp_map[i][j]==0)
					result++;
		
		return result;
	}
	
	private static void dfs(int idx) {
		if(max_wall.size()==3) {
			int result = getEmpty();
			if(ans < result)
				ans = result;
		}
		else {
			for(int i=idx;i<empty_list.size();i++) {
				Pos item = empty_list.get(i);
				if(!visit[item.i][item.j]) {
					visit[item.i][item.j] = true;
					max_wall.push(new Pos(item));
					dfs(i+1);
					visit[item.i][item.j] = false;
					max_wall.pop();
				}
			}	
		}
	}
	
	private static void copyArray(int[][] arr1, int[][] arr2) {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				arr1[i][j] = arr2[i][j];
			}
		}
	}
	private static void init() {
		
		dir = new Pos[4];
		dir[0] = new Pos(-1,0,0);
		dir[1] = new Pos(0,1,0);
		dir[2] = new Pos(1,0,0);
		dir[3] = new Pos(0,-1,0);
		
		sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		arr = new int[N][M];
		visit = new boolean[N][M];
		
		virus = new ArrayList<Pos>();
		empty_list = new ArrayList<Pos>();
		max_wall = new Stack<Pos>();
		
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				arr[i][j] = sc.nextInt();
				if(arr[i][j] == 0) empty_list.add(new Pos(i,j,0));
				else if(arr[i][j]== 2) virus.add(new Pos(i,j,0));
			}
		}
	}
	
	private static class Pos{
		int i,j;
		int visit;
		public Pos(int i, int j, int visit) {
			this.i=i;
			this.j=j;
			this.visit=visit;
		}
		public Pos(Pos item) {
			this.i=item.i;
			this.j=item.j;
			this.visit=item.visit;
		}
	}
}
