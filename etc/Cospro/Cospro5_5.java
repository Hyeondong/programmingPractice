
import java.util.*;

class Cospro5_5 {
    public int solution(int[] enemies, int[] armies) {

        int answer = 0;
        
        Arrays.sort(enemies);
        Arrays.parallelSort(armies);
        int idx1 = 0;
        int idx2 = 0;
        
        while(idx1 < enemies.length && idx2 < armies.length) {
        	if(enemies[idx1] <= armies[idx2]) {
        		answer++;
        		idx1++;
        		idx2++;
        	}
        	else
        		idx2++;
        }
        
        
        
        
        return answer;
    }

    public static void main(String[] args) {
    	Cospro5_5 sol = new Cospro5_5();
    	int[] enemies1 = {1, 4, 3};
    	int[] armies1 = {1, 3};
    	int ret1 = sol.solution(enemies1, armies1);

    	System.out.println("solution  " + ret1);

    	int[] enemies2 = {1, 1, 1};
    	int[] armies2 = {1, 2, 3, 4};
    	int ret2 = sol.solution(enemies2, armies2);

    	System.out.println("solution  " + ret2);
    }
}