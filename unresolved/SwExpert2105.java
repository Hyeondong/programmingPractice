import java.util.*;

public class SwExpert2105 {
	private static Pos_dir[] dir;
	private static int[][] mat;
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		dir = new Pos_dir[4];
		dir[0] = new Pos_dir(1,1);
		dir[1] = new Pos_dir(1,-1);
		dir[2] = new Pos_dir(-1,-1);
		dir[3] = new Pos_dir(-1,1);
		
		int T = sc.nextInt();
		for(int tc = 1; tc<=T;tc++) {
			int max = -1;
			int N = sc.nextInt();
			mat = new int[N][N];
			for(int i=0;i<N;i++) 
				for(int j=0;j<N;j++) 
					mat[i][j] = sc.nextInt();
			
			for(int i=0;i<N-2;i++) {
				for(int j=1;j<N-1;j++) {
					
					int next_i, next_j;
					boolean found=false;
					int[][] visit = new int[N][N];
					visit[i][j]=1;
					ArrayList<Pos_dir> list = new ArrayList();
					Pos_dir first = new Pos_dir(i, j);
					first.dir = 0;
					first.dir_cvt = 1;
					list.add(first);
					
					while(!list.isEmpty() && !found) {
						Pos_dir item = new Pos_dir(list.get(list.size()-1));
						
						while(!found) {
							if(item.dir_cvt==2 || item.dir==4 ) {
								visit[list.get(list.size()-1).i][list.get(list.size()-1).j]=0; // pop해야 될 경우 방문한 곳 다시 초기화
								list.remove(list.size()-1);
								
								if(!list.isEmpty()) {
									list.get(list.size()-1).dir++;
									list.get(list.size()-1).dir_cvt++;
									//pop 할 노드 방향 ++;
								}
								break;
							}
							next_i = item.i + dir[item.dir].i;
							next_j = item.j + dir[item.dir].j;
							
							if(next_i == i && next_j ==j && item.dir == 3) {
								//다시 원점으로 돌아오면 종료
								found = true;
								break;
							}
							
							if(next_i<0 || next_j<0 || next_i > N-1 || next_j > N-1 || visit[next_i][next_j] == 1 || is_overlap(mat[next_i][next_j], list)) {
								//배열 범위 벗어나거나 방문한 곳, 이미 갔던 디저트 카페 일 경우 방향 틀어줌
								item.dir++;
								item.dir_cvt++;
							}
							else {
								//다음 노드 집어넣고 다음 좌표를 현재좌표로 수정
								Pos_dir temp = new Pos_dir(next_i, next_j, item.dir, 0);
								list.add(temp);
								visit[next_i][next_j] = 1;
								item.i = next_i;
								item.j = next_j;
								item.dir_cvt=0;
							}
						}
					}
					if(max < list.size() && found) 
						max = list.size();
				}
			}
			System.out.println("#"+tc+" "+max);
		}
	}
	private static boolean is_overlap(int n, ArrayList<Pos_dir> list) {
		for(Pos_dir item : list)
			if(mat[item.i][item.j] == n)
				return true;
		
		return false;
	}
}
class Pos_dir{
	int i;
	int j;
	int dir;
	int dir_cvt;
	
	public Pos_dir() {
		
	}
	public Pos_dir(int i, int j) {
		this.i = i;
		this.j = j;
		this.dir = 0;
		this.dir_cvt = 0;
	}
	public Pos_dir(int i, int j, int dir, int dir_cvt) {
		this.i = i;
		this.j = j;
		this.dir = dir;
		this.dir_cvt = dir_cvt;
	}
	public Pos_dir(Pos_dir temp) {
		this.i = temp.i;
		this.j = temp.j;
		this.dir = temp.dir;
		this.dir_cvt = temp.dir_cvt;
	}
}
