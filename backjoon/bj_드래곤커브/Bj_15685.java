/*
 * 19.03.31 
 * 딱 보고 문제 이해 못했음 
 * 구글링해서 규칙 컨닝하고 코딩 시작함
 * 문제보고 규칙 모르겠으면 예시보면서 내가 손으로 그려가면서 규칙찾아보자
 * 이건 구글링했으니까 20점 짜리 풀이
 * 
 * arr 100x100 으로 잡아놓고 99까지 검색하니까 런타임 에러남
 * 찾아보니까 아래와 같은 답변 있었음
 * 좌표 100도 유효한 좌표입니다. 그런데 map은 99의 좌표까지밖에 처리를 못 합니다.
 * map을 [101][101]로 바꾸고 61,62번째 줄의 검사를 < 100으로 바꾸면 통과됩니다.
 * 
 */

import java.util.*;

public class Bj_15685 {
	private static Scanner sc = null;
	private static Queue<Cmd> cmd_list = null;
	private static int N = 0;
	private static Cmd[] dir = null;
	private static int[][] arr = null;
	private static ArrayList<Integer> curve_dir = null;
	
	public static void main(String args[]) {
		System.out.println(solution());
	}
	
	private static int solution(){
		int result = 0;
		
		init();
		drawingCurve();
		
		result = getRectCnt();
		
		return result;
	}
	private static void drawingCurve() {
		
		while(!cmd_list.isEmpty()) {
			int y=0;
			int x=0;
			
			Cmd item = cmd_list.poll();
			curve_dir = new ArrayList<Integer>();
			
			arr[item.y][item.x]=1;
			
			y = item.y + dir[item.d].y;
			x = item.x + dir[item.d].x;
			
			arr[y][x] = 1;
			
			curve_dir.add(item.d);
			
			int cnt=1;
			for(int k = 0;k<item.g;k++) {
				for(int i = (int)Math.pow(2, k)-1;i>=0;i--) {
					curve_dir.add( (curve_dir.get(i)+1)%4 );
					y += dir[curve_dir.get(cnt)].y;
					x += dir[curve_dir.get(cnt)].x;
					
					arr[y][x]=1;
					cnt++;
				}
			}
		}
	}
	
	private static int getRectCnt() {
		int cnt = 0;
		
		for(int i=0;i<100;i++) {
			for(int j=0;j<100;j++) {
				if(arr[i][j] == 1 && arr[i][j+1] == 1 && arr[i+1][j] == 1 && arr[i+1][j+1] == 1) 
					cnt++;
			}
		}
		
		return cnt;
	}
	
	private static void init() {
		
		dir = new Cmd[4];
//		dir[0] = new Cmd(0,1,0,0);
//		dir[1] = new Cmd(-1,0,0,0);
//		dir[2] = new Cmd(0,-1,0,0);
//		dir[3] = new Cmd(1,0,0,0);
//		
		
		//y x 대칭 시켜줘야됨 별표별표
		dir[0] = new Cmd(1,0,0,0);
		dir[1] = new Cmd(0,-1,0,0);
		dir[2] = new Cmd(-1,0,0,0);
		dir[3] = new Cmd(0,1,0,0);
		
		arr = new int[101][101];
		
		sc = new Scanner(System.in);
		N = sc.nextInt();
		
		cmd_list = new LinkedList<Cmd>();
		
		for(int i = 0;i<N;i++) {
			int y = sc.nextInt();
			int x = sc.nextInt();
			int d = sc.nextInt();
			int g = sc.nextInt();
			
			cmd_list.add(new Cmd(y,x,d,g));
		}
		
	}
	
	private static class Cmd{
		int y;
		int x;
		int d;
		int g;
		public Cmd(int y, int x, int d, int g){
			this.y = y;
			this.x = x;
			this.d = d;
			this.g = g;
		}
	}
}
