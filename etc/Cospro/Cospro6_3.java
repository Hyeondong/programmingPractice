
import java.util.*;

class Cospro6_3 {
    public int solution(int[] arr, int K) {
        int answer = 0;
        
        //sorting 해서 두개만 골라서 작은값 찾는데 왜그러는지 모르겠음
        //소팅해서 k개의 범위안에서는 앞에께 최소 뒤에께 max이니까 그중에서 min값을 비교
        return answer;
    }
    
    
    

    public static void main(String[] args) {
    	Cospro6_3 sol = new Cospro6_3();
    	int[] arr = {9, 11, 9, 6, 4, 19};
    	int K = 4;
    	int ret = sol.solution(arr, K);

    	System.out.println("solution  " + ret);
    }
}