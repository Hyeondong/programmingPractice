class Cospro6_4 {
    public int solution(int n, int mix, int k) {
        int answer = 0;

        int[] card = new int[n];
        for(int i = 0; i < n; i++)
            card[i] = i+1;

        while((mix--) != 0) {
            int[] cardA = new int[n/2];
            int[] cardB = new int[n/2];

            for(int i = 0; i < n; i++) {
                if(i < n/2)
                    cardA[i] = card[i];
                else
                    cardB[i-n/2] = card[i];
            }

            for(int i = 0; i < n; i++) {
                if(i % 2 == 0)
                    card[i] = cardA[i/2];
                else
                    card[i] = cardB[i/2];
            }
        }

        answer = card[k-1];

        return answer;
    }

   public static void main(String[] args) {
    	Cospro6_4 sol = new Cospro6_4();
    	int n = 6;
    	int mix = 3;
    	int k = 3;
    	int ret = sol.solution(n, mix, k);

        System.out.println("solution  " + ret);
    }
}