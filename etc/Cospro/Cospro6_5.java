class Cospro6_5 {
    public int solution(int[][] board) {
        int answer = 0;

        int[][] coins = new int[4][4];
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                if(i == 0 && j == 0)
                    coins[i][j] = board[i][j];
                else if(i == 0 && j != 0)
                    coins[i][j] = board[i][j] + coins[i][j-1];
                else if(i != 0 && j == 0)
                    coins[i][j] = board[i][j] + coins[i-1][j];
                else
                    coins[i][j] = board[i][j] + Math.max(coins[i-1][j], coins[i][j-1]);
            }
        }

        answer = coins[3][3];
        return answer;
    }

     public static void main(String[] args) {
    	Cospro6_5 sol = new Cospro6_5();
        int[][] board = {{6, 7, 1, 2}, {3, 5, 3, 9}, {6, 4, 5, 2}, {7, 3, 2, 6}};
        int ret = sol.solution(board);

        System.out.println("solution " + ret);
    }
}