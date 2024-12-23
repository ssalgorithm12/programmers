package programmers;

public class 숫자타자대회 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
    public int solution(String numbers) {
    	
    	char[] number = numbers.toCharArray();
    	
    	int[] left = {1, 0};
    	int[] right = {1, 2};
    	
    	
    	
    	
        int answer = 0;
        return answer;
    }
 
    // 다음 해당 좌표를 찾아주는 함수
    public int[] findNextP(char number) {
    	
    	switch(number) {
    	
    		case '1':
    			return new int[] {0, 0};
    		case '2':
    			return new int[] {0, 1};
    		case '3':
    			return new int[] {0, 2};
    		case '4':
    			return new int[] {1, 0};
    		case '5':
    			return new int[] {1, 1};
    		case '6':
    			return new int[] {1, 2};
    		case '7':
    			return new int[] {2, 0};
    		case '8':
    			return new int[] {2, 1};
    		case '9':
    			return new int[] {2, 2};
    		case '*':
    			return new int[] {3, 0};
    		case '0':
    			return new int[] {3, 1};
    		case '#':
    			return new int[] {3, 2};
    			
    	}
    	
    	return new int[] {-1, -1};
    }

}
