import java.util.*;

public class SwExpert4740 {
	private static int N;
	private static int M;
	private static int Q;
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int testcase = sc.nextInt();
		
		for(int T=1; T<=testcase;T++) {
			N = sc.nextInt();
			M = sc.nextInt();
			Q = sc.nextInt();
			ArrayList<String> qList = new ArrayList<String>();
			char[][] arr = new char[N][M];
			
			sc.nextLine();
			for(int i=0;i<N;i++) {
				String line = sc.nextLine();
				
				for(int j=0;j<M;j++) 
					arr[i][j] = line.charAt(j);
			}
			
			for(int i=0;i<Q;i++)
				qList.add(sc.nextLine());
			
			
			for(String s : qList) {
				if(s.charAt(0)=='U') {
					String line = s.substring(2);
					up(arr, line);
				}
				else if(s.charAt(0)=='R')
					right(arr);
				else if(s.charAt(0)=='L')
					left(arr);
				else if(s.charAt(0)=='D')
					delete(arr);
			}
			
			System.out.println("#"+T);
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) 
					System.out.print(arr[i][j]);
				System.out.println();
			}
			System.out.println();
		}
	}
	
	private static void delete(char arr[][]) {
		ArrayList<DelPos> list = new ArrayList<DelPos>();
		boolean[][] visit = new boolean[N][M];
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(arr[i][j]!='#' && visit[i][j] == false) {
					DelPos temp = new DelPos();
					temp = bfs_max(arr, i, j, visit);
					
					if(!list.isEmpty()) {
						DelPos cmpItem = list.get(list.size()-1);
						if(cmpItem.cnt < temp.cnt) {
							list.clear();
							list.add(temp);
						}
						else if(cmpItem.cnt == temp.cnt)
							list.add(temp);
					}
					else
						list.add(temp);
				}
			}
		}
		for(DelPos pos : list) {
			bfs_del(arr, pos.i, pos.j);
		}
		emptyCheck(arr);
	}
	private static void bfs_del(char arr[][], int i, int j) {
		DelPos[] dir = new DelPos[4];
		dir[0] = new DelPos(-1,0);
		dir[1] = new DelPos(0,1);
		dir[2] = new DelPos(1,0);
		dir[3] = new DelPos(0,-1);
		Queue<DelPos> q = new LinkedList<DelPos>();
		boolean[][] visit = new boolean[N][M];
		
		q.add(new DelPos(i,j));
		visit[i][j] = true;
		
		ArrayList<DelPos> list = new ArrayList<DelPos>();
		while(!q.isEmpty()) {
			DelPos item = new DelPos(q.peek().i, q.peek().j);
			char temp = arr[item.i][item.j];
			arr[item.i][item.j]='#';
			for(int dirIdx = 0; dirIdx<4; dirIdx++) {
				int y, x;
				y = item.i+dir[dirIdx].i;
				x = item.j+dir[dirIdx].j;
				if(y >= 0 && x >= 0 && y < N && x < M && visit[y][x] == false && (arr[y][x] == temp)) {
					visit[y][x] = true;
					q.add(new DelPos(y, x));
				}
			}
			q.poll();
		}
	}
	
	private static DelPos bfs_max(char arr[][], int i, int j, boolean visit[][]) {
		DelPos[] dir = new DelPos[4];
		dir[0] = new DelPos(-1,0);
		dir[1] = new DelPos(0,1);
		dir[2] = new DelPos(1,0);
		dir[3] = new DelPos(0,-1);
		int cnt=0;
		DelPos last = new DelPos();
		Queue<DelPos> q = new LinkedList<DelPos>();
		
		q.add(new DelPos(i,j));
		visit[i][j] = true;
		cnt++;
		
		while(!q.isEmpty()) {
			DelPos item = new DelPos(q.peek().i, q.peek().j);
			
			for(int dirIdx = 0; dirIdx<4;dirIdx++) {
				int y, x;
				y = item.i+dir[dirIdx].i;
				x = item.j+dir[dirIdx].j;
				
				if(y >= 0 && x >= 0 && y < N && x < M && visit[y][x] == false && (arr[y][x] == arr[item.i][item.j]) ) {
					visit[y][x] = true;
					q.add(new DelPos(y, x));
					cnt++;
				}
			}
			last = q.poll();
		}
		last.cnt= cnt;
		return last;
	}
	
	private static void right(char arr[][]) {
		for(int i=0;i<N;i++) {
			for(int j=M-1;j>=0;j--) {
				if(arr[i][j]=='#') {
					for(int k=j-1;k>=0;k--) {
						if(arr[i][k]!='#') {
							swap(arr, i, j, i, k);
							break;
						}
					}
				}
			}
		}
	}
	
	private static void left(char arr[][]) {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M-1;j++) {
				if(arr[i][j]=='#') {
					for(int k=j+1;k<M;k++) {
						if(arr[i][k]!='#') {
							swap(arr, i,j,i,k);
							break;
						}
					}
				}
			}
		}
	}
	
	private static void up(char arr[][], String line) {
		for(int j=0;j<M;j++) 
			if(arr[0][j]!='#') return;
		
		for(int i=1;i<=N-1;i++) 
			for(int j=0;j<M;j++) 
				arr[i-1][j]=arr[i][j];
			
		for(int j=0;j<M;j++)
			arr[N-1][j]=line.charAt(j);
		
		emptyCheck(arr);
	}
	private static void emptyCheck(char arr[][]) {
		for(int j=0;j<M;j++) {
			for(int i=N-1;i>=0;i--) {
				if(arr[i][j]=='#') {
					for(int k=i-1;k>=0;k--)
						if(arr[k][j]!='#') {
							swap(arr, i, j, k, j);
							break;
						}
				}
			}
		}
	}
	private static void swap(char arr[][], int i, int j, int y, int x) {
		char temp = arr[i][j];
		arr[i][j] = arr[y][x];
		arr[y][x] = temp;
	}
}

class DelPos{
	int i;
	int j;
	int cnt;
	public DelPos() {
		
	}
	public DelPos(int i, int j) {
		this.i = i;
		this.j = j;
	}
}