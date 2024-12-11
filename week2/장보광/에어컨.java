import java.util.*;

class Solution {
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        // 계산을 쉽게 하기 위하여 적정기온보다 낮게 설정
        if(temperature > t2){
            int diff = temperature - t2;
            temperature = t1 - diff;
        }
        
        // 실외 온도를 0으로 초기화 
        int diff2 =  0 - temperature;
        temperature += diff2;
        t1 += diff2; 
        int length = onboard.length;
        
        // dp 배열 
        int[][] dp = new int[length][t1+2];
        
        // 모든 값을 임의의 큰 수로 초기화 
        for(int i = 0; i < length; i++)
            Arrays.fill(dp[i], 1000000);
        
        // 시작 값 초기화 
        dp[0][0] = 0; 
        
        // 리턴 값 초기화 
        int answer  = 1000000;
        
        // DP 계산 시작
        for(int i = 1; i < length; i++){
            int s = 0;
            if(onboard[i]==1) s = t1;
            for(int j = s; j <= t1+1; j++){
                if(j < t1 + 1 && dp[i-1][j+1] < dp[i][j]) 
                    dp[i][j] = dp[i-1][j+1];
                if(dp[i-1][j] + b < dp[i][j])
                    dp[i][j] = dp[i-1][j] + b;
                if(j > 0 && dp[i-1][j-1] + a < dp[i][j])
                    dp[i][j] = dp[i-1][j-1] + a;
                if(j == 0 && dp[i-1][j] < dp[i][j]) 
                    dp[i][j] = dp[i-1][j];
            }
        }
        
        for(int i = 0; i <= t1+1; i++)
            if(dp[length-1][i] < answer) answer = dp[length-1][i];
        
        return answer;
    }
}
