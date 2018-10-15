import java.util.*;

public class SwExpertAcademy4050 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		
		int testcase = sc.nextInt();
		
		for(int T=0;T<testcase; T++) {
			int N = sc.nextInt();
			ArrayList<Integer> pList = new ArrayList<Integer>();
			
			for(int i=0;i<N;i++)
				pList.add(sc.nextInt());
			
			Collections.sort(pList, new Desc());
			
			int total = 0;
			for(int i=0;i<N;i++) 
				if((i%3) != 2)
					total += pList.get(i);
			
			System.out.println("#"+(T+1)+" "+total);
		}
	}
}
class Desc implements Comparator<Integer> {
	@Override
	public int compare(Integer n1, Integer n2) {
		return n2.compareTo(n1);
	}
}