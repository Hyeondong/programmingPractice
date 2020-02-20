
public class Cospro3_4 {
	public int solution(String s1, String s2) {
		
		return Math.min(getLength(s1,s2), getLength(s2,s1));
    }
	
	
	public int getLength(String s1, String s2) {
		int size = s1.length();
		int i;
		for(i=0; i<size; i++) {
			String tempA = s1.substring(i);
			String tempB = s2.substring(0, size-i > s2.length() ? s2.length() : size-i);
			
			if(tempA.equals(tempB))
				break;
		}
		
        int answer = i+s2.length();
        
        return answer;
	}
	
	
    
    public static void main(String[] args) {
        Cospro3_4 sol = new Cospro3_4();
        String s1 = new String("ababc");
        String s2 = new String("abcdab");
        int ret = sol.solution(s1, s2);
        
        System.out.println("solution " + ret);
    }
}
