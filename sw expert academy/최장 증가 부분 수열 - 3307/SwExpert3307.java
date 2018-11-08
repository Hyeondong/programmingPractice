import java.util.*;
public class SwExpert3307 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int tc=1;tc<=T;tc++) {
			int N = sc.nextInt();
			int[] arr = new int[N];
			int[] cnt = new int[N];
			for(int i=0;i<N;i++) {
				arr[i] = sc.nextInt();
				cnt[i] = 1;
			}
			
			for(int i=1;i<N;i++) 
				for(int j=0;j<i;j++) 
					if(arr[i]>arr[j] && cnt[i]<=cnt[j])
						cnt[i] = cnt[j]+1;
			
			int max=0;
			for(int i=0;i<N;i++) 
				if(max<cnt[i])
					max=cnt[i];
			
			System.out.println("#"+tc+" "+max);
			
		}
	}
}
