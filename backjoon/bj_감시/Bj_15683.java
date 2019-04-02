import java.util.*;

public class Bj_15683 {
	private static Scanner sc= null;
	private static int N = 0;
	private static int M = 0;
	private static int arr[][] = null;
	private static ArrayList<Pos> cctv_list= null;
	private static int result = Integer.MAX_VALUE;
	
	
	public static void main(String args[]) {
		solution();
	}
	
	private static void solution() {
		init();
		search(0,arr);
		System.out.println(result);
		return;
	}
	
	
	private static void search(int cctvNum, int[][] prev) {
		int visit[][] = new int[N][M];
		
		if(cctvNum == cctv_list.size()) {
			int cnt=0;
			for(int y=0;y<N;y++) {
				for(int x=0;x<M;x++) {
					if(prev[y][x] == 0)
						cnt++;
				}
			}
			if(result>cnt)
				result = cnt;
			return;
		}
		else {
			
			Pos item = cctv_list.get(cctvNum);
			switch(item.n) {
			case 1:
				for(int k = 0;k<4;k++) {
					copyArr(prev, visit);
					markVisit(visit, item.i, item.j, k);
					search(cctvNum+1, visit);
				}
				break;
			
			case 2:
				for(int k=0;k<2;k++) {
					copyArr(prev, visit);
					markVisit(visit, item.i, item.j, k);
					markVisit(visit, item.i, item.j, k+2);
					search(cctvNum+1, visit);
				}
				break;
			case 3:
				for(int k=0;k<4;k++) {
					copyArr(prev, visit);
					markVisit(visit, item.i, item.j, k);
					markVisit(visit, item.i, item.j, (k+1)%4);
					search(cctvNum+1, visit);
				}
				break;
			case 4:
				for(int k=0;k<4;k++) {
					copyArr(prev,visit);
					markVisit(visit, item.i, item.j, k);
					markVisit(visit, item.i, item.j, (k+1)%4);
					markVisit(visit, item.i, item.j, (k+2)%4);
					search(cctvNum+1, visit);
				}
				break;
			case 5:
				copyArr(prev,visit);
				markVisit(visit, item.i, item.j, 0);
				markVisit(visit, item.i, item.j, 1);
				markVisit(visit, item.i, item.j, 2);
				markVisit(visit, item.i, item.j, 3);
				search(cctvNum+1, visit);
				break;
			}
		}
	}
	private static void markVisit(int[][] visit, int i, int j, int dir) {
		
		switch(dir) {
		case 0:
			for(int k = j; k < M; k++) {
				if(visit[i][k]==6) 
					break;
				visit[i][k] = 7;
			}
			break;
			
		case 1:
			for(int k=i; k<N;k++) {
				if(visit[k][j]==6)
					break;
				visit[k][j] = 7;
			}
			break;
			
		case 2:
			for(int k=j; k>=0;k--) {
				if(visit[i][k]==6)
					break;
				visit[i][k] = 7;
			}
			break;
			
		case 3:
			for(int k=i;k>=0;k--) {
				if(visit[k][j]==6)
					break;
				visit[k][j] = 7;
			}
			break;
		}
	}
	private static void copyArr(int arr1[][], int arr2[][]){
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				arr2[i][j] = arr1[i][j];
			}
		}
	}
	private static void init() {
		sc = new Scanner(System.in);
		cctv_list = new ArrayList<Pos>();
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		arr = new int[N][M];
		
		for(int i = 0;i<N;i++) {
			for(int j=0;j<M;j++) {
				arr[i][j]=sc.nextInt();
				if(arr[i][j]>=1 && arr[i][j]<=5)
					cctv_list.add(new Pos(i,j,arr[i][j]));
			}
		}
	}
	
	
	
	private static class Pos{
		int i,j;
		int n;
		public Pos(int i, int j) {
			this.i = i;
			this.j = j;
			this.n = 0;
		}
		public Pos(int i, int j, int n) {
			this.i=i;
			this.j=j;
			this.n=n;
		}
	}
}
