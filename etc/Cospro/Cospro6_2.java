
import java.util.*;

class Cospro6_2{
    public int solution(int K, String[] words) {
        int answer = 0;
        
        int idx=0;
        int temp_k = K;
        
        while(idx < words.length) {
        	if(temp_k == K) {
        		temp_k = temp_k - words[idx].length() - 1;
        		idx++;
        	}
        	else {
        		if(temp_k < words[idx].length()) {
        			idx++;
        			answer++;
        		}
        		else {
        			temp_k = temp_k - words[idx].length() - 1;
            		idx++;
        		}
        	}
        }
        
        return answer;
    }

    public static void main(String[] args) {
    	Cospro6_2 sol = new Cospro6_2();
        int K = 10;
        String[] words = {new String("nice"), new String("happy"), new String("hello"), new String("world"), new String("hi")};
        int ret = sol.solution(K, words);

        
        System.out.println("solution " + ret);
    }
}