import java.util.Stack;

public class Programmers_IntegerTriangle {
	public static void main(String args[]) {
		int[][] triangle =  { {7}
							, {3, 8}
							, {8, 1, 0}
							, {2, 7, 4, 4}
							, {4, 5, 2, 6, 5} };
		
		Solution sol = new Solution();
		System.out.println(sol.solution(triangle));
	}
}
class Solution {
    public int solution(int[][] triangle) {
    	int answer = 0;
    	
    	for(int i = triangle.length-1; i > 0; i--) {
    		for(int j = 0; j < triangle[i].length-1;j++) {
    			if(triangle[i][j] > triangle[i][j+1]) triangle[i-1][j] += triangle[i][j];
    			else triangle[i-1][j] += triangle[i][j+1];
    		}
    	}
        return triangle[0][0];
    }
    
    
}