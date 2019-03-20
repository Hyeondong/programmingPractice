import java.util.*;

public class WordExchange {
	public static void main(String args[]) {

		String begin = "hit";
		String target = "cog";
		//String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
		//String[] words = {"hot", "dot", "dog", "lot", "log", "cog", "hig", "cig"};
		String[] words = {"hig", "cig", "hot", "dot", "dog", "lot", "log", "cog"};

		Solution sol = new Solution();
		System.out.println(sol.solution(begin, target, words));
	}
}
class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;

		if(!isTargetFind(target, words)) return 0;

		Stack<String> stack = new Stack<String>();
		Stack<String> temp_stack = new Stack<String>();
		boolean[] visit = new boolean[words.length];
		stack.push(begin);

		while(true) {
			while(!stack.isEmpty()) {
				String word = stack.pop();
				if(word.equals(target)) return answer;

				for(int i = 0;i<words.length;i++) {
					if(isChangeable(word, words[i]) && !visit[i]) {
						temp_stack.push(words[i]);
						visit[i] = true;
					}
				}
			}
			for(String temp_stack_word : temp_stack)
				stack.add(temp_stack_word);
			answer++;
		}
    }

    private boolean isChangeable(String now, String next) {
		int cnt = 0;
		for(int i=0;i<now.length();i++)
			if(now.charAt(i) == next.charAt(i)) cnt++;

		if(cnt == now.length()-1) return true;
		else return false;
	}

	private boolean isTargetFind(String target, String[] words) {
		for(String word : words)
			if(target.equals(word)) return true;
		return false;
	}
}
