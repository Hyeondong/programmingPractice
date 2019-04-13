import java.util.*;

public class SwExpert2383 {
	private static Scanner sc = null;
	private static int[][] arr = null;
	private static int N = 0;
	
	private static LinkedList<Person> p = null;
	private static Stair[] stairs = null;
	private static int[] p_arr = null;
	private static int ans = Integer.MAX_VALUE;
	
	public static void main(String args[]) {
		sc = new Scanner(System.in);
		int tc = sc.nextInt();
		
		for(int i=1;i<=tc;i++) {
			init();
			dfs(0);
			System.out.println("#"+i+" "+ans);
			ans = Integer.MAX_VALUE;
		}
	}
	
	private static void dfs(int cnt) {
		//사람들 중에 두가지 입구 고르기 브루트포스
		if(cnt >= p.size()) {
			check();
			return;
		}
		
		p_arr[cnt] = 0;
		dfs(cnt+1);
		p_arr[cnt] = 1;
		dfs(cnt+1);
	}
	
	private static void check() {
		//그냥 LinkedList로 했다가 낭패봤음
		//PriorityQueue로 구현해서 먼저 입구에 도착한애들 먼저 집어넣어주게 해야함
		
		PriorityQueue<Person>[] wait_q = new PriorityQueue[2];
		wait_q[0] = new PriorityQueue<Person>();
		wait_q[1] = new PriorityQueue<Person>();
		LinkedList<Person>[] stair_q = new LinkedList[2];
		stair_q[0] = new LinkedList<Person>();
		stair_q[1] = new LinkedList<Person>();
		
		//조합에있는 사람들 다 wait_q에 넣음
		for(int i=0;i<p_arr.length;i++) {
			p.get(i).selected = p.get(i).dist[p_arr[i]];
			p.get(i).time = stairs[p_arr[i]].time;
			wait_q[p_arr[i]].offer(p.get(i));
		}
		int total_time=0;
		
		while(true) {
			
			//계단에 있는 사람들 --;
			//if time == 0 이면 탈출시키기
			for(int i=0;i<2;i++) {
				for(int j=0;j<stair_q[i].size();j++) {
					stair_q[i].get(j).time--;
					if(stair_q[i].get(j).time == 0) {
						stair_q[i].remove(j);
						j--;
					}
				}
			}
			//입구에 도착하고 계단 자리 비면 계단에 집어넣어주기
			//Priority Queue로 구현해서 먼저 도착한애들 먼저 집어넣어주기 중요
			for(int i=0;i<2;i++) {
				for(int j=wait_q[i].size()-1;j>=0;j--) {
					if(wait_q[i].peek().selected <= total_time && stair_q[i].size() < 3) {
						stair_q[i].offer(wait_q[i].poll());
					}
				}
			}
			
			//stair_q wait_q p 다 비면
			//종료
			total_time++;
			
			if(stair_q[0].isEmpty() && stair_q[1].isEmpty() && wait_q[0].isEmpty() && wait_q[1].isEmpty()) 
				break;
		}
		if(ans > total_time)
			ans = total_time;
	}
	
	private static void init() {
		N = sc.nextInt();
		
		arr = new int[N][N];
		p = new LinkedList<Person>();
		stairs = new Stair[2];
		int s_idx=0;
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				int n = sc.nextInt();
				if(n == 0) continue;
				if(n == 1) {
					p.add(new Person(i,j));
				}
				else {
					stairs[s_idx++] = new Stair(i,j,n);
				}
				
			}
		}
		p_arr = new int[p.size()];
		
		for(Person item : p) {
			for(int i=0;i<2;i++) {
				item.dist[i] = getDist(item.i, item.j, stairs[i].i, stairs[i].j);
			}
		}
	}
	
	private static int getDist(int i, int j, int dst_i, int dst_j) {
		return Math.abs(i-dst_i) + Math.abs(j - dst_j);
	}
	
	private static class Person implements Comparable<Person> {
		int i, j;
		int time;
		int selected;
		int[] dist;
		public Person(Person p) {
			this.i = p.i;
			this.j = p.j;
			this.dist = new int[2];
			this.dist[0] = p.dist[0];
			this.dist[1] = p.dist[1];
			this.selected = 100;
			this.time = 0;
		}
		public Person(int i, int j) {
			this.i = i;
			this.j = j;
			this.dist = new int[2];
			this.selected = 100;
			this.time = 0;
		}
		@Override
		public int compareTo(Person o) {
			// TODO Auto-generated method stub
			return  this.selected - o.selected;
		}
	}
	private static class Stair {
		int i,j;
		int time;
		
		public Stair(int i, int j, int time) {
			this.i = i;
			this.j = j;
			this.time = time;
		}
	}
}
