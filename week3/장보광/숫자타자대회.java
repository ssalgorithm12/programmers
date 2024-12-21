import java.util.*;

class Solution {
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
    
    public int solution(String numbers) {
        int[][][] dp = new int[numbers.length()+1][10][10];
        for(int i = 0; i <= numbers.length(); i++){
            for(int j = 0; j < 10; j++)
                Arrays.fill(dp[i][j], Integer.MAX_VALUE);
        }
        
        dp[0][4][6] = 0;
        char[] arr = numbers.toCharArray();
        for(int i = 0; i < numbers.length(); i++){
            int x = arr[i] - '0';
            for(int j = 0; j < 10; j++){
                for(int k = 0; k < 10; k++){
                    if(dp[i][j][k]!=Integer.MAX_VALUE){
                        if(j!=x)
                            dp[i+1][j][x] = Math.min(dp[i+1][j][x] , dp[i][j][k] + cost[k][x]);               
                        if(k != x)
                            dp[i+1][x][k] = Math.min(dp[i+1][x][k] , dp[i][j][k] + cost[j][x]);
                    }
                }
            }
        }
        
        int answer = Integer.MAX_VALUE;
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
               answer = Math.min(answer, dp[numbers.length()][i][j]);
            }
        }
        
        return answer;
    }
}
