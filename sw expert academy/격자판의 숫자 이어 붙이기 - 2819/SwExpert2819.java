import java.util.*;
public class SwExpert2819 {
    private static Scanner sc = null;
    private static int[][] arr = null;
    private static int[] dy = new int[4];   
    private static int[] dx = new int[4];
    private static boolean[] checkArr = null;
 
    public static void main(String[] args) {
        dy[0] = -1; dy[1] = 0; dy[2] = 1; dy[3] =  0;
        dx[0] =  0; dx[1] = 1; dx[2] = 0; dx[3] = -1;
         
        sc = new Scanner(System.in);
        int T = sc.nextInt();
        arr = new int[4][4];
         
        for(int tc=1;tc<=T;tc++)
            solution(tc);
         
    }
    private static void solution(int tc){
        for(int i=0;i<4;i++)
            for(int j=0;j<4;j++)
                arr[i][j] = sc.nextInt();
         
        checkArr = new boolean[10000000];
         
        for(int i=0;i<4;i++)
            for(int j=0;j<4;j++)
                dfs(i, j, 0, 0, checkArr);
         
        int result = 0;
        for(int i=0;i<10000000;i++)
            if(checkArr[i]) result++;
         
        System.out.println("#"+tc+" "+result);
    }
    private static void dfs(int i, int j, int cnt, int num, boolean[] checkArr){
        if(!isIndexOutBound(i,j)) return;
         
        num += arr[i][j];
         
        if(cnt==6){
            checkArr[num] = true;
            return;
        }
         
        for(int dir = 0; dir<4; dir++)
            dfs(i+dy[dir], j+dx[dir], cnt+1, num*10, checkArr);
    }
    private static boolean isIndexOutBound(int i, int j){
        return i >= 0 && j >= 0 && i < 4 && j < 4;
    }
}