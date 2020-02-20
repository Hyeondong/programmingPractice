import java.util.*;

class Cospro3_5 {
    public String solution(String phrases, int second) {
      
        String answer = "";
        
        
        if( (second / 14) %2 == 0) {
        	
        	for(int i=0;i<14-second%14;i++) {
        		answer += "_";
        	}
        	
        	answer += phrases.substring(0,second % 14);
        	
        	
        }
        else {
        	answer += phrases.substring(second % 14);
        	
        	for(int i=0;i<second % 14;i++)
        		answer += "_";
        	
        	
        	
        }
        
        
        return answer;
    }
    
    public static void main(String[] args) {
    	Cospro3_5 sol = new Cospro3_5();
        String phrases = new String("happy-birthday");
        int second = 3;
        String ret = sol.solution(phrases, second);
        System.out.println("solution " + ret);
        
    }
}