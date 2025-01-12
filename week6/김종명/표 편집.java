package programmers;

import java.util.ArrayList;
import java.util.Stack;

/*
 * 단순 구현으로 풀었는데 잘 풀리지 않음
 * 
 * 연결 리스트를 구현해서 풀라는 글을 토대로 구상중
 * 
 * */

 /*
  * <가은 피드백>
  * - if 중첩을 줄이면 가독성이 올라갈 것 같습니다
  */

public class 표편집 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println(solution(8, 2, new String[] {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"}));

	}

    static public String solution(int n, int k, String[] cmd) {
    	
    	Stack<Integer> container = new Stack<>();
    	
    	int clength = cmd.length;
    	
    	for(int i=0; i<clength; i++) {
    		
    		if(cmd[i].length() == 1) {
    			
    			// 삭제 명령어
    			if(cmd[i].equals("C")) {
    				
    				if(k + 1 == n) {
    					container.add(k);
    					k -= 1;
    				}else {
    					container.add(k + 1);
    				}
    				
    				n -= 1;
    				
    			}else {
    				
    				// 복구 명령어
    				int id = container.pop();
    				
    				if(id <= k) {
    					k += 1;
    				}
    				
    				n += 1;
    				
    			}
    			
    		}else {
    			
    			if(cmd[i].charAt(0) == 'D') {
    				// 하강 명령어
    				
    				k += cmd[i].charAt(2) - '0';
    				
    			}else {
    				// 상승 명령어
    				
    				k -= cmd[i].charAt(2) - '0';
    			}
    			
    		}
    		
    	}
    	
    	
    	
    	return "";
    }
}
