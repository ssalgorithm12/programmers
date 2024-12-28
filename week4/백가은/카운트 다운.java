import java.util.*;

// Tabulation
class Solution {
    public int[] solution(int target) {

        // dp 초기화
        int[][] dp = new int[target + 1][2];
        dp[0][0] = 0;
        dp[0][1] = 0;
        for(int i = 1; i <= target; i++) {
            dp[i][0] = Integer.MAX_VALUE;
        }

        // 다트로 만들 수 있는 점수
        List<Integer> scores = new ArrayList<>();
        scores.add(50);
        for(int i = 1; i <= 20; i++) {
            scores.add(i);
            scores.add(i * 2);
            scores.add(i * 3);
        }
        
        // bottom-up
        for(int i = 1; i <= target; i++) {
            for(int s : scores) {
                if(i - s < 0) continue;                                         // 현재 점수보다 큰 경우 continue;
                int cnt = dp[i - s][0] + 1;                                     // 다트 수
                int special = dp[i - s][1] + (s == 50 || s <= 20 ? 1 : 0);      // 불 또는 싱글 수
                
                // 다트 수가 적거나 불/싱글 수가 많은 경우 갱신
                if(cnt < dp[i][0] || 
                  (cnt == dp[i][0] && special > dp[i][1])) {
                    dp[i][0] = cnt;
                    dp[i][1] = special;
                }
            }
        }
        return dp[target];
    }
}
