/*
 	* SwExpertAcademy2117.java - SW expert academy 2117
	
	* 문제 출처 - https://www.swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5V61LqAf8DFAWu&
	
	* 만든이 - 정현동
	
	* 목적 - 프로그래밍 연습
	
	* 작성일 - 2018.10.07
 */

import java.util.*;

public class SwExpertAcademy2117 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		
		int T;
		int N, M;
		int K;
		int mat[][];
		int visit[][];
		/*
		 상하좌우 방향 세팅
		 */
		Pos[] dir = new Pos[4];
		dir[0] = new Pos(-1,0);
		dir[1] = new Pos(0,1);
		dir[2] = new Pos(1,0);
		dir[3] = new Pos(0,-1);
		
		T = sc.nextInt();
		
		for(int testcase=0;testcase<T;testcase++) {
			N = sc.nextInt();
			M = sc.nextInt();
			K = N;
			
			mat = new int[N][N];
			visit = new int[N][N];
			for(int i = 0;i<N;i++)  
				for(int j=0;j<N;j++) { 
					mat[i][j] = sc.nextInt();
					visit[i][j] = 0;				//bfs를 위한 visit 배열
				}
			
			int maxHouse = 0;
			int cnt=0;
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					ArrayList<LinkedList<Pos>> qList = new ArrayList<LinkedList<Pos>>(); //큐를 k개만큼 만들어야하므로 타입이 linkedlist인 ArrayList 생성
					LinkedList<Pos> q = new LinkedList<Pos>();
					Pos item = new Pos(i,j);
					q.addLast(item);
					if(mat[i][j]==1) cnt++;
					visit[i][j]=1;
					qList.add(q);
					int cost = getCost(1);
					if(cnt*M-cost>=0 && maxHouse < cnt) 
						maxHouse = cnt;
					
					for(int k=1;k<=K;k++) {	
						LinkedList<Pos> qK = new LinkedList<Pos>();
						qList.add(qK);
						cost = getCost(k+1);
						while(!qList.get(k-1).isEmpty()) {
							Pos tempItem = new Pos();
							tempItem = qList.get(k-1).getFirst();
							qList.get(k-1).removeFirst();
							/*
							 * 4방향 보기
							 */
							for(int dirIdx = 0; dirIdx<4;dirIdx++) {
								int y, x;
								y = tempItem.i+dir[dirIdx].i;
								x = tempItem.j+dir[dirIdx].j;
								if(y >= 0 && x >= 0 && y < N && x < N && visit[y][x] == 0) {
									visit[y][x] = 1;
									qList.get(k).addLast(new Pos(y,x));
									if(mat[y][x]==1) cnt++;
								}
							}
							if(cnt*M-cost>=0 && maxHouse < cnt) 
								maxHouse = cnt;
						}
					}
					cnt=0;
					for(int y=0;y<N;y++) 
						for(int x=0;x<N;x++)
							visit[y][x]=0;
					
				}
			}
			System.out.println("#"+(testcase+1)+ " " +maxHouse);
		}
		
		
	}
	private static int getCost(int k) {
		return k*k+(k-1)*(k-1);
	}
}

class Pos{
	public int i,j;
	
	public Pos() {
		
	}
	public Pos(int i,int j) {
		this.i=i;
		this.j=j;
	}
}
