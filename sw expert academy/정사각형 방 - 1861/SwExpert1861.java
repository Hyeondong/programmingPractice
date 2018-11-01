import java.util.*;

public class SwExpert1861 {
	static Pos[] dir = new Pos[4];
	static int N;
	static int rooms[][];
	static int max;
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		
		dir[0] = new Pos(-1,0);
		dir[1] = new Pos(0,1);
		dir[2] = new Pos(1,0);
		dir[3] = new Pos(0,-1);
		
		int T = sc.nextInt();
		
		for(int tc=1; tc<=T;tc++) {
			N = sc.nextInt();
			rooms = new int[N][N];
			
			for(int y=0;y<N;y++) 
				for(int x=0;x<N;x++)
					rooms[y][x] = sc.nextInt();
			
			int max_room_number=1000000;
			int max2=max;
			for(int y=0;y<N;y++) {
				for(int x=0;x<N;x++) {
					int count = 0;
					max=0;
					find(y,x,rooms[y][x]-1, count);
					
					if(max2<max) {
						max2=max;
						max_room_number = rooms[y][x];
					}
					else if(max2==max && max_room_number > rooms[y][x]) {
						max_room_number = rooms[y][x];
					}
				}
			}
			System.out.println("#"+tc+" "+max_room_number+" "+max2);
		}
	}
	private static void find(int i, int j, int n, int count) {
		if(i<0 || j<0 || i>N-1 || j>N-1) return;
		if(n + 1 != rooms[i][j]) return;
		
		count++;
		if(max<count)
			max = count;
		for(int k = 0; k<4;k++)
			find(dir[k].i+i, dir[k].j+j, rooms[i][j], count);
		
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
