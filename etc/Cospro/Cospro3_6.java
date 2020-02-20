import java.util.ArrayList;

class Cospro3_6 {
    public int solution(int n) {
        int answer = 0;
        ArrayList<Integer> primes = new ArrayList<Integer>();
        primes.add(2);
        for (int i = 3; i <= n; i += 2) {
            
            int j;
            for (j = 2; j < i; j++)
                if (i % j == 0)
                    break;
                
            if (j==i)
                primes.add(i);
        }
        
        
        int primeLen = primes.size();
        for (int i = 0; i < primeLen - 2; i++)
            for (int j = i + 1; j < primeLen - 1; j++)
                for (int k = j + 1; k < primeLen; k++)
                    if (primes.get(i)+primes.get(j)+primes.get(k)==n)
                        answer++;
        return answer;
    }
    
    public static void main(String[] args) {
    	Cospro3_6 sol = new Cospro3_6();
        int n1 = 33;
        int ret1 = sol.solution(n1);

        System.out.println("solution " + ret1);
        
        int n2 = 9;
        int ret2 = sol.solution(n2);

        System.out.println("solution  " + ret2);
    }    
}