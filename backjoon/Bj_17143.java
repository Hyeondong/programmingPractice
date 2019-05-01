import java.util.*;

public class Bj_17143 {
	private static Scanner sc = null;
	private static Shark[] list = null;
	private static int R,C,M = 0;
	private static int[][] arr = null;
	private static int dy[] = {-1, 0, 1, 0};
	private static int dx[] = {0, 1, 0, -1};
	private static int result = 0;
	
	public static void main(String args[]) {
		 init();
		 solution();
		 System.out.println(result);
	}
	
	public static void solution() {
		for(int i=0;i<C;i++) {
			fishing(i);
			moveShark();
			eating();
		}
	}
	
	public static void moveShark() {
		for(int i=0;i<M;i++) {
			Shark item = list[i];
			if(item == null) continue;
			
			arr[item.i][item.j]--;
			
			for(int t=0;t<item.s;t++) {
				int n_i = item.i+dy[item.d];
				int n_j = item.j+dx[item.d];
				if(n_i < 0 || n_j < 0 || n_i >= R || n_j >= C) {
					item.d = (item.d+2)%4;
					n_i = item.i+dy[item.d];
					n_j = item.j+dx[item.d];
				}
				
				item.i = n_i;
				item.j = n_j;
			}
			arr[item.i][item.j]++;
		}
	}
	
	public static void eating() {
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				if(arr[i][j] > 1) {
					int max_idx = -1;
					int max = -1;
					int[] rmlist = new int[arr[i][j]];
					int rmIdx = 0;
					for(int k=0;k<M;k++) {
						if(list[k] == null) continue;
						if(i == list[k].i && j == list[k].j) {
							rmlist[rmIdx++] = k;
							if(max < list[k].z) {
								max = list[k].z;
								max_idx = k;
							}
							
						}
					}
					
					for(int n : rmlist) {
						if(n != max_idx)
							list[n] = null;
					}
					arr[i][j] = 1;
				}
			}
		}
	}
	
	public static void fishing(int king) {
		for(int i=0;i<R;i++) {
			if(arr[i][king] == 1) {
				arr[i][king] = 0;
				for(int k=0;k<M;k++) {
					if(list[k] == null) continue;
					if(list[k].i == i && list[k].j == king) {
						result += list[k].z;
						list[k] = null;
					}
				}
				break;
			}
		}
	}
	
	
	private static void init() {
		sc = new Scanner(System.in);
		
		R = sc.nextInt();
		C = sc.nextInt();
		M = sc.nextInt();
		
		arr = new int[R][C];
		list = new Shark[M];
		
		for(int i=0;i<M;i++) {
			int y = sc.nextInt()-1;
			int x = sc.nextInt()-1;
			int s = sc.nextInt();
			int d = sc.nextInt()-1;
			int z = sc.nextInt();
			if(d == 1) {
				d = 2;
			}
			else if(d == 2) {
				d = 1;
			}
			arr[y][x] = 1;
			list[i] = new Shark(y,x,s,d,z);
		}
		
	}
	
	private static class Shark{
		int i,j;
		int s,d,z;
		public Shark(int i, int j, int s, int d, int z) {
			this.i = i;
			this.j = j;
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}
}
