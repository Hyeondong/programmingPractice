class Cospro6_6 {
    public int solution(int[][] grid) {
        int answer = 0;
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++)
                for(int k = j + 1; k < 4; k += 2)
                    answer = Math.max(answer, Math.max(grid[i][j] + grid[j][k], grid[j][k] + grid[k][i]));
        return answer;
    }

     public static void main(String[] args) {
    	 Cospro6_6 sol = new Cospro6_6();
    	int[][] grid = {{1, 4, 16, 1}, {20, 5, 15, 8}, {6, 13, 36, 14}, {20, 7, 19, 15}};
    	int ret = sol.solution(grid);

    	System.out.println("solution " + ret);
    }
}