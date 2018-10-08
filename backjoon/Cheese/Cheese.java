import java.util.*;

public class Cheese {
	private static int N;
	private static int M;
	private static int arr[][];
	private static int visit[][];
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);    
		N = sc.nextInt();
	    M = sc.nextInt();
	    arr = new int[N][M];
	    visit = new int[N][M];
	    
	    for(int i=0;i<N;i++) 
	    	for(int j=0;j<M;j++) { 
	    		arr[i][j]=sc.nextInt();
	    		visit[i][j]=arr[i][j];
	    	}
	    int result=0;
	    while(true) {  
	    	ArrayList<Pos> rmPos = new ArrayList<Pos>();
	    	outerAir(0,0);					//dfs해서 외부공기 확인
	    	
	    	for(int i=1;i<N-1;i++) {
		    	for(int j=1;j<M-1;j++) {	
		    		if(arr[i][j]==1 && check(arr, i, j)) {
		    			Pos item = new Pos(i,j);
		    			rmPos.add(item);
		    		}
		    	}
	    	}
		    
		    if(rmPos.isEmpty()) break;		//제거할 놈 없으면 종료
		    
		    for(Pos item : rmPos) 
		    	arr[item.x][item.y] = 0;
		    result++;
		    visitRefresh();					//visit배열 초기화
	    }
	    System.out.print(result);
	}
	private static void visitRefresh() {
		for(int i=0;i<N;i++) 
			for(int j=0;j<M;j++) 
				if(visit[i][j]==2)
					visit[i][j]=0;	
	}
	private static void outerAir(int i, int j) {
		if(i<0 || j<0 || i>N-1 || j>M-1) return;
		if(visit[i][j]==2 || arr[i][j]==1) return;
		if(arr[i][j]==0) arr[i][j]=2;
		
		visit[i][j]=2;
		outerAir(i, j+1);
		outerAir(i+1, j);
		outerAir(i, j-1);
		outerAir(i-1, j);
	}
	
	private static boolean check(int arr[][], int i, int j) {
		int cnt=0;
		if(arr[i-1][j]==2) cnt++;
		if(arr[i][j+1]==2) cnt++;
		if(arr[i+1][j]==2) cnt++;
		if(arr[i][j-1]==2) cnt++;
		
		if(cnt>=2) return true;
		else return false;
	}
}
class Pos{
	int x;
	int y;
	public Pos(int x,int y) {
		this.x=x;
		this.y=y;
	}
}