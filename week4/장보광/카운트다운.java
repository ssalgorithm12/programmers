class Solution {
    public int[] solution(int target) {
        int[][] dp = new int[target+1][2];

        // dp 탐색  
        for(int i = 1; i <= target; i++){
            dp[i][0] = Integer.MAX_VALUE; 
            for(int j = 1; j < 21; j++){
                if(i - j >= 0)
                    dp[i] = check(dp[i], dp[i - j], 1);
                if(i - j * 2 >= 0)
                    dp[i]= check(dp[i], dp[i - j * 2], 0);
                if(i - j * 3 >= 0)
                    dp[i] = check(dp[i], dp[i - j * 3], 0);
                if(i - 50 >= 0)
                    dp[i] = check(dp[i], dp[i - 50], 1);
            }
        }

        return dp[target];
    }

   // 점수 비교 후 갱신하는 코드  
    int[] check(int[] a, int[] b, int count){
        int[] result = a;
        if(a[0] > b[0] + 1){
            result[0] = b[0] + 1;
            result[1] = b[1] + count;
        }
        if(a[0] == b[0] + 1 && a[1] < b[1] + count){
            result[0] = b[0] + 1;
            result[1] = b[1] + count;
        }
        return result; 
    }
}
