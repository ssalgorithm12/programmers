import java.util.*;

// DP
class Solution {
    
    public int solution(String numbers) {
        
        int[][] cost = {
            {1, 7, 6, 7, 5, 4, 5, 3, 2, 3},
            {7, 1, 2, 4, 2, 3, 5, 4, 5, 6},
            {6, 2, 1, 2, 3, 2, 3, 5, 4, 5},
            {7, 4, 2, 1, 5, 3, 2, 6, 5, 4},
            {5, 2, 3, 5, 1, 2, 4, 2, 3, 5},
            {4, 3, 2, 3, 2, 1, 2, 3, 2, 3},
            {5, 5, 3, 2, 4, 2, 1, 5, 3, 2},
            {3, 4, 5, 6, 2, 3, 5, 1, 2, 4},
            {2, 5, 4, 5, 3, 2, 3, 2, 1, 2},
            {3, 6, 5, 4, 5, 3, 2, 4, 2, 1}
        };
        
        int len = numbers.length();
        int INF = Integer.MAX_VALUE;
        int[][][] dp = new int[len + 1][10][10];
        for(int i = 0; i <= len; i++) {
            for(int j = 0; j < 10; j++) {
                Arrays.fill(dp[i][j], INF);
            }
        }
        dp[0][4][6] = 0;
        for(int i = 0; i < len; i++) {
            int target = numbers.charAt(i) - '0';
            
            for(int j = 0; j < 10; j++) {
                for(int k = 0; k < 10; k++) {
                    if(dp[i][j][k] == INF) continue;
                    
                    // 손가락이 해당 위치에 놓일 가능성이 있는 경우
                    
                    // 왼손이 다음 버튼으로 움직이는 경우
                    if(k != target) dp[i + 1][target][k] = Math.min(dp[i + 1][target][k], dp[i][j][k] + cost[j][target]);
                    // 오른손이 다음 버튼으로 움직이는 경우
                    if(j != target) dp[i + 1][j][target] = Math.min(dp[i + 1][j][target], dp[i][j][k] + cost[k][target]);
                }
            }
        }
        
        int ans = INF;
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                ans = Math.min(ans, dp[len][i][j]);
            }
        }
        
        return ans;
    }
}