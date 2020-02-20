import java.util.*;

public class Cospro3_3 {
	
	public int solution(String[] bishops) {
        
        
        int[][] arr = new int[8][8];
        
        int dx[] = {-1,1,-1,1};
        int dy[] = {-1,1,1,-1};
        
		for(int k=0;k<bishops.length;k++) {
			int x = bishops[k].charAt(0) - 'A';
			int y = Math.abs(bishops[k].charAt(1) - '0' - 8);
			
			arr[y][x] = 2;
			
			for(int d=0;d<4;d++) {
				for(int dist=1;dist<8;dist++) {
					int next_x = x + dx[d]*dist;
					int next_y = y + dy[d]*dist;
					
					if(next_x < 0 || next_y < 0 || next_x >=8 || next_y >= 8)
						break;
					
					if(arr[next_y][next_x] == 0) {
						arr[next_y][next_x] = 1;
						
					}
				}
			}
		}
		
		
		int answer=0;
		
    	for(int i=0;i<8;i++) 
    		for(int j=0;j<8;j++) 
    			if(arr[i][j]==0)
    				answer++;
    	
        return answer;
    }
	
	
	public static void main(String[] args) {
		Cospro3_3 sol = new Cospro3_3();
        String[] bishops1 = {new String("D5")};
        int ret1 = sol.solution(bishops1);
        
        System.out.println("solution1: " + ret1 + "");
       
        String[] bishops2 = {new String("D5"), new String("E8"), new String("G2")};
        int ret2 = sol.solution(bishops2);
        
        System.out.println("solution2: " + ret2 + "");
    }
}

