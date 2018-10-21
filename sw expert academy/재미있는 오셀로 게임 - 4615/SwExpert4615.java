import java.util.*;
import java.util.Scanner;

public class SwExpert4615 {
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		Pos[] dir = new Pos[8];
		dir[0] = new Pos(-1,0);
		dir[1] = new Pos(-1,1);
		dir[2] = new Pos(0,1);
		dir[3] = new Pos(1,1);
		dir[4] = new Pos(1,0);
		dir[5] = new Pos(1,-1);
		dir[6] = new Pos(0,-1);
		dir[7] = new Pos(-1,-1);
		
		
		for(int tc = 1; tc<=T;tc++) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			int[][] mat = new int[N][N];
			int pos = N/2 - 1;
			mat[pos][pos] = 2;
			mat[pos][pos+1] = 1;
			mat[pos+1][pos] = 1;
			mat[pos+1][pos+1] = 2;
			
			for(int step=0;step<M;step++) {
				int y = sc.nextInt();
				int x = sc.nextInt();
				y -=1;
				x -=1;
				int stone = sc.nextInt();
				mat[y][x] = stone;
				//입력 예외처리 해줘야될지 모르겠네
				ArrayList<Pos> cvt_list = new ArrayList<Pos>();
				
				for(int i =0;i<8;i++) {
					Stack<Pos> stack = new Stack<Pos>();
					int next_y = y;
					int next_x = x;
					while(true) {
						next_y = next_y+dir[i].i;
						next_x = next_x+dir[i].j;
						if(next_y < 0 || next_x <0 || next_y > N-1 || next_x > N-1) break;
						if(stone==mat[next_y][next_x]) {
							while(!stack.isEmpty())
								cvt_list.add(stack.pop());
							break;
						}
						else if(mat[next_y][next_x]==0)
							break;
						else {
							stack.push(new Pos(next_y, next_x));
						}
					}
				}
				for(Pos p : cvt_list) {
					mat[p.i][p.j] = stone;
				}
				
			}
			int black=0;
			int white=0;
			for(int y=0;y<N;y++) {
				for(int x=0;x<N;x++) {
					if(mat[y][x]==1) black++;
					else if(mat[y][x]==2) white++;
				}
			}
			
			System.out.println("#"+tc+" "+black+ " "+white);
		}
		
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
