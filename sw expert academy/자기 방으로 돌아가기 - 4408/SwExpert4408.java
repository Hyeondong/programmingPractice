import java.util.Scanner;

public class SwExpert4408 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		
		int tc = sc.nextInt();
		for(int T = 1; T <= tc; T++) {
			int result=0;
			int N = sc.nextInt();
			int[] road = new int[201];
			
			for(int n = 0; n<N;n++) {
				int src = sc.nextInt();	
				int dest = sc.nextInt();
				src = getRoad(src);
				dest = getRoad(dest);
				
				if(src<=dest)
					for(int i = src; i <= dest;i++)
						road[i]++;
				else
					for(int i = src; i >= dest;i--)
						road[i]++;
			}
			for(int i=1;i<=200;i++)
				if(road[i]>result)
					result=road[i];
			System.out.println("#"+T+" "+result);
		}
	}
	private static int getRoad(int room) {
		if(room % 2 ==0) return room/2;
		else return room/2+1;
	}
}
