import java.util.*;
import java.io.*;

public class SwExpert5658 {
	private static Scanner sc = null;
	private static LinkedList<Character> list = null;
	private static int N, K = 0;
	private static Set<String> set = null;
	private static int ans = 0;
	
	public static void main(String args[]) {
		sc = new Scanner(System.in);
		int tc= sc.nextInt();
		
		for(int i=1;i<=tc;i++) {
			init();
			solution();
			System.out.println("#"+i+" "+ans);
		}
		
	}
	
	private static void solution() {
		int size = N/4;
		for(int k=0;k<size;k++) {
			for(int i=0;i<N;i=i+size) {
				StringBuilder num = new StringBuilder();
				for(int j=i;j<i+size;j++) {
					num.append(list.get(j));
				}
				set.add(num.toString());
			}
			char c = list.pollLast();
			list.offerFirst(c);
		}
		ArrayList<Integer> intList = new ArrayList<Integer>();
		
		for(String num : set) {
			intList.add(toInteger(num));
		}
		
		Collections.sort(intList);
		Collections.reverse(intList);
		ans = intList.get(K-1);	
	}
	
	private static int toInteger(String num) {
		int k=0;
		int result = 0;
		for(int i=num.length()-1;i>=0;i--) {
			char c = num.charAt(i);
			result += (int)Math.pow(16, k++)*returnNum(c);	
		}
		
		return result;
	}
	
	private static int returnNum(char c) {
		if('0'<= c && c <= '9') return c-'0';
		else
			return c-'A'+10;
	}
	
	private static void init() {
		N = sc.nextInt();
		K = sc.nextInt();
		
		sc.nextLine();
		String line = sc.nextLine();
		
		list = new LinkedList<Character>();
		set = new HashSet<String>();
		
		for(int i=0;i<N;i++) {
			list.offer(line.charAt(i));
		}
	}
}