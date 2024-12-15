class Solution {
    public int solution(int n) {
        long[] dp = new long[n+1];
        long[] mid = new long[n+3];
        dp[0] = 1;
        mid[1] = 1;
        mid[2] = 3;
        mid[3] = 10;
        
        for(int i = 1; i <= n; i++){
            dp[i] += dp[i-1];
            if(i > 1) dp[i] += dp[i-2]*2;
            if(i > 2) dp[i] += dp[i-3]*5;
            dp[i] %= 1000000007;
            
            if(i > 3){
                dp[i] += mid[i-4] * 2;
                if(i > 4)
                    dp[i] += mid[i-5] * 2;
                if(i > 5)
                    dp[i] += mid[i-6] * 4;       
                if(i%3==0) dp[i] += 4;
                else dp[i] += 2;
                dp[i] %= 1000000007;                
                mid[i] = (dp[i] + mid[i-3])%1000000007; 
            }
                
        }
            
        return (int)dp[n];
    }
}
