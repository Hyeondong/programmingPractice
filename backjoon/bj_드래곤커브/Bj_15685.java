/*
 * 19.03.31 
 * �� ���� ���� ���� ������ 
 * ���۸��ؼ� ��Ģ �����ϰ� �ڵ� ������
 * �������� ��Ģ �𸣰����� ���ú��鼭 ���� ������ �׷����鼭 ��Ģã�ƺ���
 * �̰� ���۸������ϱ� 20�� ¥�� Ǯ��
 * 
 * arr 100x100 ���� ��Ƴ��� 99���� �˻��ϴϱ� ��Ÿ�� ������
 * ã�ƺ��ϱ� �Ʒ��� ���� �亯 �־���
 * ��ǥ 100�� ��ȿ�� ��ǥ�Դϴ�. �׷��� map�� 99�� ��ǥ�����ۿ� ó���� �� �մϴ�.
 * map�� [101][101]�� �ٲٰ� 61,62��° ���� �˻縦 < 100���� �ٲٸ� ����˴ϴ�.
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
		
		//y x ��Ī ������ߵ� ��ǥ��ǥ
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
