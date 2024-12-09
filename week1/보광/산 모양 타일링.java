class Solution {
    public int solution(int n, int[] tops) {
        // dp 배열로 저장  
        int[][] dp = new int[n+1][2];
        // dp[n][0]는 n번째 위 타일
        // dp[n][1]은 n+1번째 아래 타일 
      
        dp[0][1] = 1;
        if(tops[0]==1) dp[1][0] = 3;
        else dp[1][0] = 2; 
        dp[1][1] = dp[1][0] + dp[0][1];
        
        for(int i = 2; i<= n; i++){
            // 위 타일 dp 계산 
            int check = tops[i-1];
            if(check == 1) dp[i][0] = dp[i-1][0] + dp[i-1][1] * 2;
            else dp[i][0] = dp[i-1][0] + dp[i-1][1];  
            if(dp[i][0] >= 10007) dp[i][0] %= 10007;

            // 아래 타일 dp 계산
            dp[i][1] = dp[i][0] + dp[i-1][1];
            if(dp[i][1] >= 10007) dp[i][1] %= 10007; 
        }
        
        return dp[n][1];
    }
}
