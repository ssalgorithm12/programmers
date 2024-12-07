import java.util.*;

class Solution {
    public long solution(int n, int[] tops) {
        int len = 0;                   // 윗변에 붙일 정삼각형 개수
        for(int i = 0; i < tops.length; i++) {
            if(tops[i] == 1) len++;
        }
        int mod = 10007;
        long[] dp = new long[2 * n + 1 + len + 1];
        
        dp[0] = 1;
        dp[1] = 1;
        int cnt = 0;                    // 지금까지 산 모양 타일이 몇 개 있었는지 저장
        boolean mountainAfter = false;  // 다음 타일이 산 모양인지 여부
        boolean mountainBefore = false;  // 이전 타일이 산 모양인지 여부
        
        for(int i = 2; i <= 2 * n + 1 + len; i++) {
            
            // 산 모양 타일을 붙일 차례인지에 따라 분기
            if(mountainAfter) {
                cnt++;
                mountainAfter = false;
                dp[i] = (dp[i - 1] + dp[i - 2]) % mod;
                mountainBefore = true;
                
            } else {

                // 다음이 산 모양 타일 붙일 차례인지 확인
                int tmp = (i - cnt) / 2 - 1;
                if((i - cnt) % 2 == 0 && tops[tmp] == 1) {
                    mountainAfter = true;
                }
                
                // 이전에 산 모양 타일 붙였는지에 따라 분기
                if(mountainBefore) {
                    dp[i] = (dp[i - 1] + dp[i - 3]) % mod;
                    mountainBefore = false;

                } else dp[i] = (dp[i - 1] + dp[i - 2]) % mod;
            }       
        }
        
        return dp[2 * n + 1 + len];
    }
}