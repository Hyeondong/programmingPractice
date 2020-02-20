class Cospro5_4 {
    public String solution(int number) {
        String answer = "";

        int[] numberCount = new int[10];
        while(number > 0) {
            numberCount[number % 10]++;
            number /= 10;
        }

        for(int i = 9; i >=0; i--)
            if(numberCount[i] != 0)
                answer += (String.valueOf(i) + String.valueOf(numberCount[i]));
        
        return answer;
    }

    public static void main(String[] args) {
    	Cospro5_4 sol = new Cospro5_4();
    	int number1 = 2433;
    	String ret1 = sol.solution(number1);

    	System.out.println("solution " + ret1);

    	int number2 = 662244;
    	String ret2 = sol.solution(number2);

    	System.out.println("solution " + ret2);
    }
}