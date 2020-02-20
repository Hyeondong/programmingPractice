class Cospro5_2 {
    public int solution(int[][] walls) {
        int answer = 0;

        for(int i = 0; i < walls.length; i++) {
        	for(int j = i+1; j < walls.length; j++) {
        		int area = 0;
        		if(walls[i][1] < walls[j][1])
        			area = walls[i][1] * (walls[j][0] - walls[i][0]);
        		else
        			area = walls[j][1] * (walls[j][0] - walls[i][0]);

        		if(answer < area)
        			answer = area;
        	}
        }

        return answer;
    }

    public static void main(String[] args) {
    	Cospro5_2 sol = new Cospro5_2();
    	int[][] walls = {{1, 4}, {2, 6}, {3, 5}, {5, 3}, {6, 2}};
    	int ret = sol.solution(walls);

    	System.out.println("solution " + ret);
    }
}