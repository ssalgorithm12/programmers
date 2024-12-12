package programmers;

import java.util.Arrays;

public class 표현가능한이진트리 {

	public static void main(String[] args) {


		System.out.println(Arrays.toString(solution(new long[] {63, 111, 95})));

	}

    static public int[] solution(long[] numbers) {
    	
    	int size = numbers.length;
    	
    	int[] answer = new int[size];
    	
    	for(int i=0; i<size; i++) {
    		
    		long number = numbers[i];
    		
    		String convert = convertToTwo(number);
    		
    		answer[i] = checkTreeMake(convert);
    	}
    	
        return answer;
    }
    
    static int checkTreeMake(String number) {
    	
    	char[] num = number.toCharArray();
    	
    	int answer = 1;
    	
    	// 체크 로직 만들어야함
    	for(int i=1; i<num.length; i++) {
    		
    		if(i % 2 == 1) {
    			
    			if(num[i] == '0') {
    				
    				if(num[i-1] == '0' && num[i+1] == '0') {
    					continue;
    				}
    				
    				answer = 0;
    			}
    			
    		}
    	}
    	
    	return answer;
    }
    
    static String convertToTwo(long number) {
    	
    	String convert = "";
    	
    	while(number > 0) {
    		
    		String num = Long.toString(number % 2);
    		
    		convert = convert.concat(num);
    		
    		number = number / 2;
    	}
    	
    	String answer = "";
    	
    	if(convert.length() % 2 == 0) {
    		answer = answer.concat("0");
    	} 
    	
    	for(int i=convert.length()-1; i>=0; i--) {
    		answer = answer.concat(String.valueOf(convert.charAt(i)));
    	}
    	
    	System.out.println(answer);
    
    	return answer;
    }
    
}
