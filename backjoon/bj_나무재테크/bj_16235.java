import java.util.*;

public class bj_16235 {
	private static Scanner sc = null;
	private static PriorityQueue<Tree> list = null;
	private static PriorityQueue<Tree> temp_list = null;
	private static PriorityQueue<Tree> rmList = null;
	
	private static int arr[][] = null;
	private static int add_arr[][] = null;
	
	private static int N = 0;
	private static int M = 0;
	private static int K = 0;
	private static Tree[] dir = null;
	
	public static void main(String args[]) {
		sc = new Scanner(System.in);
		
		initDir();
		dataInput();
		
		System.out.print(solution());
		
	}
	
	private static int solution() {
		
		for(int year =0; year < K; year++) {
			spring();
			summer();
			fall();
			winter();
		}
		
		return list.size();
	}
	
	private static void spring() {
		while(!list.isEmpty()) {
			Tree item = list.poll();
			
			if(item.age > arr[item.i][item.j]) 
				rmList.offer(new Tree(item));
			else {
				arr[item.i][item.j] -= item.age;
				item.age = item.age +1;
				temp_list.offer(new Tree(item));
			}
		}
		list.addAll(temp_list);
		temp_list.clear();
	}
	private static void summer() {
		for(Tree item : rmList) {
			arr[item.i][item.j] += (item.age/2);
		}
		rmList.clear();
	}
	private static void fall() {
		
		for(Tree item : list) {
			if(item.age %5 == 0) {
				for(int k=0;k<8;k++) {
					int n_i = item.i + dir[k].i;
					int n_j = item.j + dir[k].j;
					
					if(isIndexOutOfBound(n_i,n_j)) continue;
					
					temp_list.offer(new Tree(n_i, n_j, 1));
				}
			}
		}
		for(Tree item : temp_list)
			list.offer(new Tree(item));
		temp_list.clear();
	}
	
	private static void winter() {
		for(int i = 0;i<N;i++)
			for(int j=0;j<N;j++) 
				arr[i][j] += add_arr[i][j];
	}
	
	private static boolean isIndexOutOfBound(int i, int j) {
		if(i<0 || j<0 || i >= N || j >=N) return true;
		else return false;
	}
	
	private static void initDir() {
		dir = new Tree[8];
		dir[0] =new Tree(-1,0);
		dir[1] =new Tree(-1,1);
		dir[2] =new Tree(0,1);
		dir[3] =new Tree(1,1);
		dir[4] =new Tree(1,0);
		dir[5] =new Tree(1,-1);
		dir[6] =new Tree(0,-1);
		dir[7] =new Tree(-1,-1);
	}
	
	private static void dataInput() {
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		
		arr= new int[N][N];
		add_arr = new int[N][N];
		list = new PriorityQueue<Tree>();
		rmList = new PriorityQueue<Tree>();
		temp_list = new PriorityQueue<Tree>();
		for(int i=0;i<N;i++) {
			for(int j=0; j<N;j++) { 
				arr[i][j] = 5;
				add_arr[i][j] = sc.nextInt();
			}
		}
		
		for(int i=0; i<M; i++)
			list.add(new Tree(sc.nextInt()-1, sc.nextInt()-1, sc.nextInt()));
	}
	
	private static class Tree implements Comparable<Tree>{
		int i;
		int j;
		int age;
		public Tree(int i, int j) {
			this.i = i;
			this.j = j;
			this.age = 0;
		}
		public Tree(int i, int j, int age) {
			this.i=i;
			this.j=j;
			this.age=age;
		}
		public Tree(Tree tree) {
			this.i = tree.i;
			this.j = tree.j;
			this.age = tree.age;
		}
		@Override
		public int compareTo(Tree o) {
			return this.age - o.age;
		}
	}
}
