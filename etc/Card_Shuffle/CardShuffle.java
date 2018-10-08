import java.util.*;
public class CardShuffle {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int C;			//카드 숫자
		int P;			//셔플 횟수
		C = sc.nextInt();
		P = sc.nextInt();
		
		int []arr = new int[P];		//기준 N 저장하는 Array
		
		for(int i=0;i<P; i++) 
			arr[i] = sc.nextInt();		
		
		LinkedList<Integer> list = new LinkedList<Integer>();		//삭제와 first node 추가가 빈번하므로 LinkedList사용
		
		for(int i=0;i<C;i++) 
			list.add(new Integer(i));
		
		for(int i=0;i<P;i++) {
			int n = arr[i];
			
			int front= n; 				//front index
			int rear = C-1;				//rear index
			
			int q=0;					//한번 섞을때마다 rear값을 *n 해서 당겨야하므로 변수 q선언
			while( (rear-front+1) >= 2*arr[i] ) {
				q++;
				rear=rear-q*n;				//rear값 선언
				
				for(int k=rear;k>=front;k--) {		//위로 올리는 갯수만큼 반복
					int item = list.get(rear);
					list.remove(rear);
					list.addFirst(new Integer(item));
				}
			}
		}
		for(int i=0;i<5;i++) 
			System.out.println(list.get(i)+1);
	}
}
