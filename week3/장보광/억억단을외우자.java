class Solution {
    public int[] solution(int e, int[] starts) {
        int[] count = new int[e+1];
        for(int i = 1; i <= e; i++){
            int index = 1;
            while(i * index <= e)
                count[i * index++]++;
        }
        
        int[] dp = new int[e+1];
        int max = e;
        for(int i = e; i > 0; i--){
            if(count[i] >= count[max])
                max = i;
            dp[i] = max;
        }
        
        int[] answer = new int[starts.length];
        for(int i = 0; i < starts.length; i++)
            answer[i] = dp[starts[i]];
        
        return answer;
    }
}
