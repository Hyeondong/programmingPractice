import java.util.*;

public class Bj_14891 {
	private static Scanner sc= null;
	private static LinkedList<Integer>[] q = null;
	private static int N =0;
	private static int[] wheel_num = null;
	private static int[] wheel_dir = null;
	
	public static void main(String args[]) {
		solution();
	}
	
	
	private static void solution() {
		init();
		
		for(int i=0; i<N;i++) {
			extend(wheel_num[i], wheel_dir[i], wheel_num[i]);
		}
		showResult();
	}
	
	private static void showResult() {
		int result = 0;
		
		for(int i=0;i<4;i++) {
			if(q[i].get(0) == 1)
				result += (int)Math.pow(2, i);
		}
		System.out.println(result);
	}
	
	private static void extend(int pre_num, int pre_dir, int num) {
		if(num<0 || num > 3) return;
		
		if(pre_num == num) {
			extend(num, pre_dir, num+1);
			extend(num, pre_dir, num-1);
			rotate_wheel(num, pre_dir);
		}
		else if(pre_num < num) {
			if(!q[pre_num].get(2).equals(q[num].get(6))) {
				extend(num, pre_dir*(-1), num+1);
				rotate_wheel(num, pre_dir*(-1));
			}
		}
		else {
			if(!q[pre_num].get(6).equals(q[num].get(2))) {
				extend(num, pre_dir*(-1), num-1);
				rotate_wheel(num, pre_dir*(-1));
			}
		}
	}
	
	private static void rotate_wheel(int n, int dir) {
		//시계방향
		if(dir == 1) {
			int tail = q[n].getLast();
			q[n].removeLast();
			q[n].addFirst(new Integer(tail));
		}
		else {
			int head = q[n].getFirst();
			q[n].removeFirst();
			q[n].addLast(new Integer(head));
		}
	}
	
	private static void init() {
		sc = new Scanner(System.in);
		
		q = new LinkedList[4];
		
		for(int i=0;i<4;i++) {
			q[i] = new LinkedList<Integer>();
			String line = sc.nextLine();
			for(int j=0;j<8;j++) {
				q[i].add(new Integer(line.substring(j, j+1).charAt(0) - '0'));
			}
		}
		
		N = sc.nextInt();
		wheel_num = new int[N];
		wheel_dir = new int[N];
		
		for(int i=0;i<N;i++) {
			wheel_num[i] = sc.nextInt()-1;
			wheel_dir[i] = sc.nextInt();
		}
	}
}
