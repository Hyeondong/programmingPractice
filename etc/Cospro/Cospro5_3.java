import java.util.*;

class Cospro5_3 {
	public void swap(int[] numbers, int i, int j) {
		int tmp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = tmp;
	}

    public int[] solution(int[] numbers) {
        int[] answer = {};

        Arrays.sort(numbers);
        int mid = (numbers.length - 1) / 2;
        swap(numbers, mid, numbers.length-1);

        int left = mid + 1;
        int right = numbers.length-2;
        while(left <= right) {
        	swap(numbers, left, right);
        	left = left + 1;
        	right = right - 1;
        }

        answer = numbers;
        return answer;
    }

    public static void main(String[] args) {
    	Cospro5_3 sol = new Cospro5_3();
    	int[] numbers = {7, 3, 4, 1, 2, 5, 6,8};
    	int[] ret = sol.solution(numbers);

    	System.out.print("solution");
    	for(int i = 0; i < ret.length; i++) {
    		if(i != 0)
    			System.out.print(", ");
    		System.out.print(ret[i]);
    	}
    }
}