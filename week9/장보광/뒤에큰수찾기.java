import java.util.Stack;
import java.util.Arrays;

class Solution {
    public int[] solution(int[] numbers) {
        // 결과 배열을 -1로 초기화
        int[] answer = new int[numbers.length];
        Arrays.fill(answer, -1);

        // 인덱스를 저장할 스택
        Stack<Integer> stack = new Stack<>();

        // 모든 원소를 순회
        for (int i = 0; i < numbers.length; i++) {
            // 스택이 비어있지 않고, 현재 숫자가 스택 top에 해당하는 숫자보다 크면
            // 오큰수를 갱신
            while (!stack.isEmpty() && numbers[stack.peek()] < numbers[i]) {
                answer[stack.pop()] = numbers[i];
            }
            // 현재 인덱스를 스택에 push
            stack.push(i);
        }

        // -1로 남아있는 곳은 오큰수가 없으므로 그대로 -1
        return answer;
    }
}
