class Solution {
    public int solution(int n, int[] tops) {
        int[][] dp = new int[n+1][2];
      
        // dp 초기화 
        dp[0][1] = 1;
        dp[0][0] = 1;
        
        // dp 계산 
        for(int i = 1; i<= n; i++){
            dp[i][0] = (dp[i-1][0] + dp[i-1][1]*(1+tops[i-1]))%10007;  
            dp[i][1] = (dp[i][0] + dp[i-1][1])%10007; 
        }
        
        return dp[n][1];
    }
}
