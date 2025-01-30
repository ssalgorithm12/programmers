import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int len = numbers.length;
        int[] answer = new int[len];
        Stack<int[]> stack = new Stack<>();
        
        for(int i = 0; i < len; i++) {
            int val = numbers[i];
            
            while(!stack.isEmpty() && stack.peek()[1] < val) {
                answer[stack.pop()[0]] = val;
            }
            stack.push(new int[]{i, val});
        }
        
        while(!stack.isEmpty()) {
            answer[stack.pop()[0]] = -1;
        }
        
        return answer;
    }
}