import java.util.*;

class Solution {
    
    static int[][] dp;
    static List<Integer> scores;

    public int[] solution(int target) {
        dp = new int[target + 1][2];

        // 초기값 설정
        dp[0][0] = 0; // 다트 수
        dp[0][1] = 0; // 싱글 또는 불 횟수

        // 점수 계산
        for (int i = 1; i <= target; i++) {
            dp[i][0] = Integer.MAX_VALUE;   // 다트 수 최댓값으로 초기화
            dp[i][1] = 0;                   // 싱글 또는 불 횟수 초기화
        }

        // 다트로 얻을 수 있는 점수 저장
        scores = new ArrayList<>();
        scores.add(50); // 불
        for (int i = 1; i <= 20; i++) {
            scores.add(i);       // 싱글
            scores.add(i * 2);   // 더블
            scores.add(i * 3);   // 트리플
        }

        // Top-Down
        memo(target);

        return dp[target];
    }

    static int[] memo(int cur) {
        if (cur < 0) return new int[]{Integer.MAX_VALUE, 0}; // 불가능한 점수
        if (dp[cur][0] < Integer.MAX_VALUE) return dp[cur];  // 이미 계산된 경우

        for (int score : scores) {
            if (cur - score >= 0) {
                int[] prev = memo(cur - score);
                int dartCount = prev[0] + 1;
                int specialCount = prev[1] + (score == 50 || score <= 20 ? 1 : 0);

                // 더 적은 다트 수이거나, 같을 경우 싱글/불 횟수 비교
                if (dartCount < dp[cur][0] || 
                    (dartCount == dp[cur][0] && specialCount > dp[cur][1])) {
                    dp[cur][0] = dartCount;
                    dp[cur][1] = specialCount;
                }
            }
        }

        return dp[cur];
    }
}
