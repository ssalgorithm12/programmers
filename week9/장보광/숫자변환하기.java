import java.util.*;

class Solution {
    public int solution(int x, int y, int n) {
        // 방문 여부를 확인할 배열 v. -1이면 아직 방문하지 않은 상태
        int[] v = new int[y + 1];
        Arrays.fill(v, -1);

        // 정답 초기값(-1) 설정
        int answer = -1;

        // 큐를 이용한 BFS. 큐에는 [현재값, 연산 횟수]를 담는다.
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, 0});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int t1 = current[0]; // 현재 수
            int t2 = current[1]; // 현재까지의 연산 횟수

            // 목표값 y에 도달했다면, answer 갱신
            if (t1 == y) {
                answer = t2;
            }

            // 가능한 연산 1) t1 * 2
            if (t1 * 2 <= y && v[t1 * 2] == -1) {
                v[t1 * 2] = 1;  // 방문 처리
                queue.offer(new int[]{t1 * 2, t2 + 1});
            }
            // 가능한 연산 2) t1 * 3
            if (t1 * 3 <= y && v[t1 * 3] == -1) {
                v[t1 * 3] = 1;
                queue.offer(new int[]{t1 * 3, t2 + 1});
            }
            // 가능한 연산 3) t1 + n
            if (t1 + n <= y && v[t1 + n] == -1) {
                v[t1 + n] = 1;
                queue.offer(new int[]{t1 + n, t2 + 1});
            }
        }

        return answer;
    }
}
