class Cospro6_7 {
    public int solution(int K, int[] numbers, String[] UpDown) {
        int left = 1;
        int right = K;
        for(int i = 0; i < numbers.length; i++){
            int num = numbers[i];
            if(UpDown[i].equals("UP"))
                left = Math.max(left, num+1);
            else if(UpDown[i].equals("DOWN"))
                right = Math.min(right, num-1);
            else if(UpDown[i].equals("RIGHT"))
                return 1;
        }
        return right - left + 1;
    }

    public static void main(String[] args) {
    	Cospro6_7 sol = new Cospro6_7();
        int K1 = 10;
        int[] numbers1 = {4, 9, 6};
        String[] UpDown1 = {new String("UP"), new String("DOWN"), new String("UP")};
        int ret1 = sol.solution(K1, numbers1, UpDown1);

        System.out.println("solution " + ret1);

        int K2 = 10;
        int[] numbers2 = {2, 1, 6};
        String[] UpDown2 = {new String("UP"), new String("UP"), new String("DOWN")};
        int ret2 = sol.solution(K2, numbers2, UpDown2);

        System.out.println("solution  " + ret2);

        int K3 = 100;
        int[] numbers3 = {97, 98};
        String[] UpDown3 = {new String("UP"), new String("RIGHT")};
        int ret3 = sol.solution(K3, numbers3, UpDown3);

        System.out.println("solution " + ret3);
    }
}