import java.util.*;

class Cospro5_6 {	
	
	int char_to_int(char ch) {
	    return ch-'0';
	}
	
	char int_to_char(int val) {
		return (char) (val+'0');
	}
	
	String convert_scale(int num, int q) {
		if (num == 0) return "";
		return convert_scale(num / q, q) + int_to_char(num % q);
	}

	public int parse_decimal(String s, int p) {
		int num = 0;
		for (int i = s.length() - 1, mul = 1; i >= 0; i--, mul *= p) {
			num += char_to_int(s.charAt(i)) * mul;
		}
		return num;
	}
	
    public String solution(String s1, String s2, int p, int q) {
    	int num1 = parse_decimal(s1, p);
    	int num2 = parse_decimal(s2, p);
    	String answer = convert_scale(num1 + num2, q);
    	return answer;
    }
	
    
    public static void main(String[] args) {
    	Cospro5_6 sol = new Cospro5_6();
    	String s1 = new String("30");
        String s2 = new String("0");
        int p = 10;
        int q = 8;
    	String ret = sol.solution(s1, s2, p, q);
    	
        System.out.println("solution " + ret);
   }
}
