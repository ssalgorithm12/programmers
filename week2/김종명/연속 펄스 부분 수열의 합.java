package Programmers;

import java.util.Arrays;

// 55.0 / 100.0 
// 누적합을 통해서 구하는 방식

public class 연속펄스부분수열의합 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		solution(new int[] {2, 3, -6, 1, 3, -1, 2, 4});
		
	}
	
	static long[] sum;
	
    static public long solution(int[] sequence) {
        
    	int size = sequence.length;
    	
    	sum = new long[size];
    	
    	sum[0] = sequence[0];
    	
    	for(int i=1; i<size; i++) {
    		
    		if(i % 2 == 1) {
    			
    			sum[i] = sum[i-1] - sequence[i];
    			
    		}else {
    			
    			sum[i] = sum[i-1] + sequence[i];
    			
    		}
    		
    	}
    	
    	long max = 0;
    	int maxIdx = 0;
    	
    	for(int i=1; i<size; i++) {
    		
    		if(Math.abs(sum[i]) >= max) {
    			max = Math.abs(sum[i]);
    			maxIdx = i;
    		}
    		
    	}
    	
    	long answer = 0;
    	
    	for(int i=maxIdx; i>=0; i--) {
    		
    		if(Math.abs(sum[maxIdx] - sum[i]) > answer) {
    			answer = Math.abs(sum[maxIdx] - sum[i]);
    		}
    		
    	}
    	
        return answer;
    }

}
