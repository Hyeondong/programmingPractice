import java.util.*;

public class bj_16236 {
	private static Scanner sc = null;
	private static int N = 0;
	private static int arr[][] = null;
	private static boolean visit[][] = null;
	private static Pos shark = null;
	private static Pos[] dir = new Pos[4];
	
	
	public static void main(String args[]) {
		sc = new Scanner(System.in);
		//방향 초기화
		dir[0] = new Pos(-1,0);
		dir[1] = new Pos(0,1);
		dir[2] = new Pos(1,0);
		dir[3] = new Pos(0,-1);
		
		dataInput();
		
		System.out.println(solution());
		
	}
	
	
	private static int solution() {
		int result = 0;
		int shark_size = 2;
		int dist = 1;
		int eat_cnt = 0;
		
		Pos target = null;
		Queue<Pos> q = new LinkedList<Pos>();
		Queue<Pos> temp_q = new LinkedList<Pos>();
		q.offer(shark);
		visit[shark.i][shark.j]=true;
		
		
		while(!q.isEmpty()) {
			Pos item = q.poll();
			
			for(int i = 0; i<4; i++) {
				int n_i = item.i + dir[i].i;
				int n_j = item.j + dir[i].j;
				if(isIndexOfOutBound(n_i, n_j) || arr[n_i][n_j] > shark_size) continue;
				
				temp_q.offer(new Pos(n_i, n_j));
				visit[n_i][n_j] = true;
				
				if(arr[n_i][n_j]!=0 && shark_size > arr[n_i][n_j]) {//먹을 수 있는 물고기일 경우
					if(target == null) target = new Pos(n_i, n_j); //먹을 수 있는 물고기가 이전까지 없었다면
					else {
						if(target.i > n_i) { // 물고기 우선순위 선택하기
							target.i = n_i;
							target.j = n_j;
						}
						else if (target.i == n_i){
							if(target.j > n_j) {
								target.i = n_i;
								target.j = n_j;
							}
						}
					}
				}
			}
			
			if(q.isEmpty()) { //거리 기준 한바퀴 다돈거라면
				if(target!=null) { //먹을 수 있는 물고기가 있다면 먹고 그 자리로 이동
					arr[target.i][target.j] = 0;
					eat_cnt++;
					q.offer(new Pos(target.i, target.j));
					temp_q.clear();
					visitClear();
					
					if(eat_cnt==shark_size){
						eat_cnt = 0;
						shark_size++;
					}
					result += dist;
					dist = 1;
					
					target=null;
				}
				else { //먹을 수 있는 물고기 없으면 한바퀴 더 돌아야 돼
					dist++;
					q.addAll(temp_q);
					temp_q.clear();
				}
			}
		}
		
		return result;
	}
	private static void visitClear() {
		for(int i=0; i<N; i++)
			for(int j=0; j<N;j++)
				visit[i][j] = false;
	}
	
	private static boolean isIndexOfOutBound(int i, int j) {
		if(i<0 || j<0 || i>=N || j >= N || visit[i][j] ) return true;
		else return false;
	}
	
	private static void dataInput() {
		N = sc.nextInt();
		arr = new int[N][N];
		visit = new boolean[N][N];
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				arr[i][j]=sc.nextInt();
				if(arr[i][j]==9) { 
					shark = new Pos(i,j);
					arr[i][j]=0;
				}
			}
		}
	}
	private static class Pos {
		int i;
		int j;
		public Pos(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
