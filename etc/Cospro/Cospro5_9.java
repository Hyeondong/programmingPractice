
import java.util.*;

class Cospro5_9 {
	
	public int solution(int number, int target) {
		int answer = 0;
		
		int[] visited = new int[10001];
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(number);
		visited[number] = 1;

		while(!q.isEmpty()) {
			int x = q.poll();

			if(x == target)
				break;

			if(x+1 <= 10000 && visited[x+1] == 0) {
				visited[x+1] = visited[x]+1;
				q.offer(x+1);
			}
			if(x-1 >= 0 && visited[x-1] == 0) {
				visited[x-1] = visited[x]+1;
				q.offer(x-1);
			}
			if(2*x <= 10000 && visited[2*x] == 0) {
				visited[2*x] = visited[x]+1;
				q.offer(2*x);
			}
		}

		answer = visited[target]-1;
		
		
		return answer;
	}
	
	

	public static void main(String[] args) {
		Cospro5_9 sol = new Cospro5_9();
		int number1 = 5;
		int target1 = 9;
		int ret1 = sol.solution(number1, target1);

		System.out.println("solution " + ret1);

		int number2 = 3;
		int target2 = 11;
		int ret2 = sol.solution(number2, target2);

		System.out.println("solution " + ret2);
	}
}