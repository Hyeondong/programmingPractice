import java.util.*;

public class SwExpert1249 {
	private static Scanner sc = null;
	private static Pos[] dir = new Pos[4];

	public static void main(String args[]) {
		sc = new Scanner(System.in);
		dir[0] = new Pos(-1,0);
		dir[1] = new Pos(0,1);
		dir[2] = new Pos(1,0);
		dir[3] = new Pos(0,-1);

		int tc = sc.nextInt();

		for(int i=0;i<tc;i++)
			System.out.println("#"+(i+1)+" "+solution());

	}

	private static int solution() {
		int N = sc.nextInt();
		int [][]map= new int[N][N];
		int [][]dist= new int [N][N];

		dataInput(map, dist, N);

		Queue<Pos> q = new LinkedList<>();
		q.add(new Pos(0,0));
		dist[0][0] = 0;

		while(!q.isEmpty()) {
			Pos item = q.poll();

			for(int i=0; i<4; i++) {
				int next_i = item.i + dir[i].i;
				int next_j = item.j + dir[i].j;

				if(isIndexOutBound(N, next_i, next_j)) continue;

				if(dist[next_i][next_j] > dist[item.i][item.j] + map[next_i][next_j]) {
					dist[next_i][next_j] = dist[item.i][item.j] + map[next_i][next_j];
					q.add(new Pos(next_i, next_j));
				}
			}
		}

		return dist[N-1][N-1];
	}

	private static boolean isIndexOutBound(int n, int i, int j){
		if(i < 0 || j < 0 || i >= n || j >= n) return true;
		else return false;
	}

	private static void dataInput(int map[][], int dist[][], int N) {
		String line;
		sc.nextLine();



		for(int i=0;i<N;i++) {
			line = sc.nextLine();
			for(int j=0;j<N;j++) {
				map[i][j] = line.charAt(j) - '0';
				dist[i][j] = Integer.MAX_VALUE;
			}
		}
	}
}
class Pos{
	int i;
	int j;
	public Pos(){}
	public Pos(int i, int j) {
		this.i = i;
		this.j = j;
	}
}
