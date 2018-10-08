import java.util.*;
import java.io.*;

public class Main {
	private static int N;
	private static int [][]arr;
	private static int [][]visit;
	private static int cnt=1;
	private static ArrayList<Integer> cluster = new ArrayList<Integer>();
	public static void main(String args[]) {

		 Scanner sc = new Scanner(System.in);
	      N = sc.nextInt();
	      
	      arr = new int[N][N];
	      visit = new int[N][N];
	      
	      for (int i = 0; i < N; i++) {
	         int k=0;
	         String temp = sc.next();
	         for (int j = 0; j < N; j++) {
	            arr[i][j] = temp.charAt(k++)-'0';
	            
	         }
	      }
	      
	      
	      for(int i=0;i<N;i++) {
	    	  for(int j=0;j<N;j++) {
	    		  if(arr[i][j]==1 && visit[i][j]!=1) {

	    			  cnt++;
	    			  bfs(i,j);
	    			  
	    		  }
	    	  }
	      }
	      
	      Collections.sort(cluster);
	      System.out.println(cluster.size());
	      for(Integer item : cluster) {
	    	  System.out.println(item);
	      }
	      
	}	
	private static void bfs(int i, int j) {
		if(i < 0 || j < 0 || i > N-1 || j > N-1) return;
		Queue<Pos> q = new LinkedList<Pos>();
		int houseCnt=0;
		q.add(new Pos(i,j));
		visit[i][j]=1;
		arr[i][j] = cnt;
		houseCnt++;
		while(!q.isEmpty()) {
			Pos item = new Pos(q.peek().x, q.peek().y);
			
			if(item.x>=0 && item.y+1 >= 0 && item.x < N && item.y+1 < N) {
				if(visit[item.x][item.y+1]!=1 && arr[item.x][item.y+1]==1) {
					visit[item.x][item.y+1]=1;
					arr[item.x][item.y+1]=cnt;
					q.add(new Pos(item.x, item.y+1));
					houseCnt++;
					
				}
			}
			if(item.x+1 >= 0 && item.y >= 0 && item.x+1 < N && item.y < N) {
				if(visit[item.x+1][item.y]!=1 && arr[item.x+1][item.y]==1) {
					visit[item.x+1][item.y]=1;
					arr[item.x+1][item.y]=cnt;
					q.add(new Pos(item.x+1, item.y));
					houseCnt++;
				}
			}
			if(item.x >= 0 && item.y-1 >= 0 && item.x < N && item.y-1 < N) {
				if(visit[item.x][item.y-1]!=1 && arr[item.x][item.y-1]==1) {
					visit[item.x][item.y-1]=1;
					arr[item.x][item.y-1]=cnt;
					q.add(new Pos(item.x, item.y-1));
					houseCnt++;
				}
			}
			if(item.x-1 >= 0 && item.y >= 0 && item.x-1 < N && item.y < N) {
				if(visit[item.x-1][item.y]!=1 && arr[item.x-1][item.y]==1) {
					visit[item.x-1][item.y]=1;
					arr[item.x-1][item.y]=cnt;
					q.add(new Pos(item.x-1, item.y));
					houseCnt++;
				}
			}
			q.poll();
		}
		cluster.add(new Integer(houseCnt));		
	}		
}
class Pos{
	int x;
	int y;
	public Pos() {
		
	}
	public Pos(int x,int y) {
		this.x=x;
		this.y=y;
	}
}