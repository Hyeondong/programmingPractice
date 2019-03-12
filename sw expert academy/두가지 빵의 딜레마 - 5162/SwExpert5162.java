import java.util.*;

public class SwExpert5162 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();

		for(int i = 1; i<=tc;i++) {
			int A = sc.nextInt();
			int B = sc.nextInt();
			int C = sc.nextInt();

			System.out.println("#"+i+" "+(A<B ? C/A : C/B));
		}
	}
}
