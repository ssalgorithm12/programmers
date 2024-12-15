class Solution {
    public long solution(int[] sequence) {
        long[] sum = new long[sequence.length];
        sum[0] = sequence[0];
        long answer = 0, max = 0, min = 0;
        answer = Math.abs(sequence[0]);
        for(int i = 1; i < sequence.length; i++){
            if(sum[i-1] < min) min = sum[i-1];
            else if(sum[i-1] > max) max = sum[i-1];
            sum[i] = sum[i-1] + sequence[i] * (1 - 2 * (i % 2)); 
            if(answer < sum[i] - min) answer = sum[i] - min;
            if(answer < max - sum[i]) answer = max - sum[i];
        }
        
        return answer;
    }
}
