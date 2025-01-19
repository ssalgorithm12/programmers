package programmers;

import java.util.Stack;

public class 옮기기110 {
	
	// 문제의 접근법만 참고
	// Stack에 저장하며 110의 개수를 세는 방법 
	// 110의 개수만큼 마지막 0 뒤에 붙히는 방법
	
	
	// 테스트 케이스 9, 13, 14, 15, 18, 19, 20, 21 실패(런타임 에러)
	

	public static void main(String[] args) {
		solution(new String[] {"1110", "100111100", "0111111010" });
	}
	
    static public String[] solution(String[] s) {
    	
    	int size = s.length;
    	
    	String[] answer = new String[size];
    	
    	for(int i=0; i<size; i++) {
    		
    		answer[i] = makeSmall(s[i]);
    	}
    	
        return answer;
    }
    
    static String makeSmall(String s) {
    	
    	Stack<Character> container = new Stack<>(); 	// 숫자 저장할 공간 Stack
    	
    	int size = s.length();		
    	int count = 0;		// 110의 개수
    	
    	for(int i=size-1; i>=0; i--) {
    		
    		container.add(s.charAt(i));
    		
    		if(container.peek() == '1' && container.size() >= 3) {
    			
    			char a = container.pop();
    			char b = container.pop();
    			char c = container.pop();
    			
    			// 110이 만들어지면 stack에서 제거하고 개수 1 증가
    			if(a == '1' && b == '1' && c == '0') {
    				count += 1;
    				continue;
    			}
    			
    			container.add(c);
    			container.add(b);
    			container.add(a);
    		}
    	}
    	
    	StringBuffer answer = new StringBuffer();
    	
    	while(!container.isEmpty()) {
    		
    		answer.append(container.pop());
    	}
    	
        size = answer.length() - 1;
    	int index = size;
    	boolean isZero = false;
    	
    	while(index > 0) {
    		
    		if(answer.charAt(index) == '0') {
    			isZero = true;
    			break;
    		}
    		
    		index -= 1;
    	}
    	
    	// 110을 넣어줄 자리 찾기
    	if(isZero || answer.charAt(0) == '0') {
    		index += 1;
    	}
    	
    	// 마지막 0 뒤에 110을 넣어줌
    	while(count > 0) {
    	   answer.insert(index, "110");
    	   count -= 1;
    	}
    	
    	return answer.toString();
    }
    

}
