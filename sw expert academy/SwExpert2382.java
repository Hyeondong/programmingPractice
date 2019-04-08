import java.util.*;

public class SwExpert2382 {
	private static Scanner sc = null;
	private static int N,M,K = 0;
	private static int[][] arr = null;
	private static LinkedList<Node> list= null;
	
	
	public static void main(String args[]) {
		sc = new Scanner(System.in);
		int Tc = sc.nextInt();
		for(int i=1;i<=Tc;i++) {
			init();
			
			System.out.print("#"+i+" ");
			solution();
		}
	}
	
	private static void solution() {
		while(M-- > 0) {
			for(Iterator<Node> it = list.iterator(); it.hasNext();) {
				Node item = it.next();
				if(arr[item.i][item.j] != -1) arr[item.i][item.j]--;
				
				item.i += item.dy;
				item.j += item.dx;
				if(arr[item.i][item.j] == -1) {
					item.num /= 2;
					item.dy *= -1;
					item.dx *= -1;
					
					if(item.num == 0) {
						it.remove();
					}
				}
				else 
					arr[item.i][item.j]++;
			}
			
			for(int i=0;i<list.size();i++) {
				Node item = list.get(i);
				if(arr[item.i][item.j] > 1) {
					int maxdy = item.dy;
					int maxdx = item.dx;
					int maxNum = item.num;
					int sum = 0;
					for(Node rmItem : list) {	
						if(rmItem.i == item.i && rmItem.j == item.j) {
							if(maxNum < rmItem.num) {
								maxNum = rmItem.num;
								maxdy = rmItem.dy;
								maxdx = rmItem.dx;
							}
							sum += rmItem.num;
						}
					}
					
					Node addItem = new Node(item.i, item.j, sum, 0);
					addItem.dy = maxdy;
					addItem.dx = maxdx;
					
					for(Iterator<Node> it = list.iterator(); it.hasNext();) {
						Node rmItem = it.next();
						if(rmItem.i == item.i && rmItem.j ==item.j) {
							it.remove();
						}
					}
					list.addLast(addItem);
					i--;
					arr[item.i][item.j] = 1;
				}
			}
		}
		
		int result = 0;
		for(Node item : list) {
			result += item.num;
		}
		
		System.out.println(result);
	}
	
	private static void init() {
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		
		arr = new int[N][N];
		
		for(int i=0;i<N;i++) {
			arr[0][i] = -1;
			arr[N-1][i] = -1;
			arr[i][0] = -1;
			arr[i][N-1] = -1;
		}
		
		list= new LinkedList<Node>();
		
		for(int i=0;i<K;i++) {
			int y = sc.nextInt();
			int x = sc.nextInt();
			arr[y][x] = 1;
			list.add(new Node(y,x, sc.nextInt(), sc.nextInt()));
		}
	}
	
	private static class Node{
		int i,j;
		int num;
		int dy,dx;
		
		public Node(Node item) {
			this.i = item.i;
			this.j = item.j;
			this.num = item.num;
			this.dy = item.dy;
			this.dx = item.dx;
		}
		public Node(int i, int j, int num, int dir) {
			this.i = i;
			this.j = j;
			this.num = num;
			
			switch(dir) {
			case 1:
				this.dy = -1;
				this.dx = 0;
				break;
			case 2:
				this.dy = 1;
				this.dx = 0;
				break;
			case 3:
				this.dy = 0;
				this.dx = -1;
				break;
			case 4:
				this.dy = 0;
				this.dx = 1;
				break;
			default:
				this.dy = 0;
				this.dx = 0;
				break;
			}
		}
	}
	
}
