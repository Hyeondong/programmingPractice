import java.util.*;
import java.util.ArrayList;

class Cospro6_9 {
    Integer func_a(ArrayList<Integer> stack) {
        Integer item = stack.remove(stack.size() - 1);
        return item;
    }
    
    void func_b(ArrayList<Integer> stack1, ArrayList<Integer> stack2) {
        while(!func_c(stack1)) {
            Integer item = func_a(stack1);
            stack2.add(item);
        }
    }
    
    boolean func_c(ArrayList<Integer> stack) {
        return (stack.size() == 0);
    }
    
    public int solution(ArrayList<Integer> stack1, ArrayList<Integer> stack2) {
        if(func_c(stack2)) {
            func_b(stack1, stack2);
        }
        Integer answer = (int)func_a(stack2);
        return answer;
    }
     
    public static void main(String[] args) {
    	Cospro6_9 sol = new Cospro6_9();
        
        ArrayList<Integer> stack1_1 = new ArrayList<Integer>();
        ArrayList<Integer> stack2_1 = new ArrayList<Integer>();
        stack1_1.add(1);
        stack1_1.add(2);
        stack2_1.add(3);
        stack2_1.add(4);
        int ret1 = sol.solution(stack1_1, stack2_1);
        System.out.println("solution " + ret1);

    
        ArrayList<Integer> stack1_2 = new ArrayList<Integer>();
        ArrayList<Integer> stack2_2 = new ArrayList<Integer>();
        stack1_2.add(1);
        stack1_2.add(2);
        stack1_2.add(3);
        int ret2 = sol.solution(stack1_2, stack2_2);
        System.out.println("solution " + ret2);
    }
}