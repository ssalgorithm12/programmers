package programmers;

public class 아방가르드타일링 {

	public static void main(String[] args) {


		System.out.println(solution(2));

	}

    static public int solution(int n) {
        
    	if(n == 2) {
    		return 3;
    	}
    	
    	int[] DP = new int[n+1];
    	
    	DP[2] = 3;
    	DP[3] = 10;
    	
    	for(int i=4; i<n+1; i++) {
    		DP[i] = ((DP[i-1] * 2 - (DP[i-1] / 2)) + (DP[i-2] * 3 - DP[i-2])) % 1000000007;
    	
    	}
    	
        return DP[n];
    }
}
