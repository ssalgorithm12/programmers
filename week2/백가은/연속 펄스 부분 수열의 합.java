class Solution {
    public long solution(int[] sequence) {
        
        long[] sum = new long[sequence.length];
        sum[0] = sequence[0];
        
        long answer = 0;
        long max = 0;
        long min = 0;
        
        answer = Math.abs(sequence[0]);
        
        for(int i = 1; i < sequence.length; i++){
            
            min = Math.min(min, sum[i-1]);
            max = Math.max(max, sum[i-1]);
            
            if(i % 2 == 1) sum[i] = sum[i-1] - sequence[i]; 
            else sum[i] = sum[i-1] + sequence[i]; 
            
            if(answer < sum[i] - min) answer = sum[i] - min;
            if(answer < -(sum[i] - max)) answer = -(sum[i] - max);
        }
        
        return answer;
    }
}