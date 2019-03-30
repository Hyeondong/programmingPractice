/*
 * 19.03.29 11:58 시작 // 익일 02 : 30 실패 // 04:09 성공(알고리즘 컨닝)
 */

import java.util.*;

public class Bj_15686 {
	private static Scanner sc = null;
	private static int N = 0;
	private static int M = 0;
	
	private static ArrayList<Pos> house_list = null;
	private static ArrayList<Pos> chicken_list = null;
	private static int total_dist = Integer.MAX_VALUE;
	
	
	public static void main(String args[]) {
		initData();
		
		dfs(0,0);
		
		System.out.println(total_dist);
	}
	
	private static void minSearch() {
		int result = 0;

		for(Pos house : house_list) {
			int min = Integer.MAX_VALUE;
			
			for(Pos chicken : chicken_list) {
				if(chicken.visit == 1) {
					int dist = Math.abs(house.i-chicken.i)+Math.abs(house.j-chicken.j);
					if(min > dist)
						min = dist;
				}
			}
			
			result += min;
		}
		if(total_dist > result)
			total_dist = result;
	}
	
	
	
	private static void dfs(int idx, int M_cnt) {
		if(M_cnt == M) {
			minSearch();
			return;
		}
		
		for(int i = idx;i<chicken_list.size();i++) {
			if(chicken_list.get(i).visit==0) {
				int y = chicken_list.get(i).i;
				int x = chicken_list.get(i).j;
				
				//chicken_list.set(i, new Pos(y,x,1));
				chicken_list.get(i).visit= 1;
				dfs(i+1, M_cnt+1);
				//chicken_list.set(i, new Pos(y,x,0));
				chicken_list.get(i).visit= 0;
			}
		}
	}
	
	private static void initData() {
		sc = new Scanner(System.in);
		house_list = new ArrayList<Pos>();
		chicken_list = new ArrayList<Pos>();
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				int n = sc.nextInt();
				
				if(n == 1) 
					house_list.add(new Pos(i,j,0));
				else if(n == 2) 
					chicken_list.add(new Pos(i,j,0));
			}
		}
	}
	
	private static class Pos{
		int i,j;
		int visit;
		public Pos(int i, int j, int visit) {
			this.i = i;
			this.j = j;
			this.visit = visit;
		}
	}
}
