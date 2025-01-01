package programmers;


public class 카운트다운 {

	/*
	 * 1 번에 맞출 수 있는 점수
	 * 
	 * 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20
	 * 22, 24, 26, 28, 30, 32, 34, 36, 38, 40
	 * 21, 24, 27, 30, 33, 36, 39, 42, 45, 48, 51, 54, 57, 60
	 * 
	 * 2 번에 나눠서 맞추는 점수
	 * 
	 * 23, 25, 29, 31, 35, 37, 41, 43, 44, 46, 47, 49, 50, 52, 53, 55, 56, 58, 59
	 * 
	 * */
	
	// 33ms
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public int[] solution(int target) {
		
		int[][] DP = new int[100001][2];
		
		// 싱글
		for(int i=1; i<21; i++) {
			DP[i][0] = 1;
			DP[i][1] = 1;
		}
		
		for(int i=21; i<61; i++) {
			
			// 더블
			if(i % 2 == 0 && i < 41) {
				DP[i][0] = 1;
				DP[i][1] = 0;
			}
			// 트리플
			if(i % 3 == 0) {
				DP[i][0] = 1;
				DP[i][1] = 0;
			}
			
			if(i % 2 > 0 && i % 3 > 0 && i < 41) {
				DP[i][0] = 2;
				DP[i][1] = 2;
			}
			
			if(i > 40 && i % 3 > 0) {
				
				DP[i][0] = 2;
				DP[i][1] = 1;
				
				if(i > 50) {
					DP[i][0] = 2;
					DP[i][1] = 2;
				}
			}
		}
		
		// 불
		DP[50][0] = 1;
		DP[50][1] = 1;
		
		for(int i=61; i<target+1; i++) {
			
			int countA = Integer.MAX_VALUE;
			int sumA = 0;
			
			for(int j=1; j<21; j++) {
				
				if(DP[i-j][0] + 1 < countA) {
					
					countA = DP[i - j][0] + 1;
					sumA = DP[i-j][1] + 1;
					
				}else if(DP[i-j][0] + 1 == countA) {
					
					if(DP[i-j][1] + 1 > sumA) {
						
						sumA = DP[i-j][1] + 1;
					}
				}
			}
			
			DP[i][0] = countA;
			DP[i][1] = sumA;
			
			int countB = DP[i-50][0] + 1;
			int sumB = DP[i-50][1] + 1;
			
			if(countB < DP[i][0]) {
				
				DP[i][0] = countB;
				DP[i][1] = sumB;
			}else if(countB == DP[i][0]) {
			
				DP[i][1] = Math.max(DP[i][1], sumB);
			}
			
			int countC = Integer.MAX_VALUE;
			int sumC = 0;
			
			for(int j=21; j<61; j++) {
				
				if(j % 3 == 0) {
					if(DP[i-j][0] + 1 < countC) {
						countC = DP[i-j][0] + 1;
						sumC = DP[i-j][1];
						
					}else if(DP[i-j][0] + 1 == countC) {
						
						if(DP[i-j][1] > sumC) {
							sumC = DP[i-j][1];
						}
					}
				}
			}
			
			if(countC < DP[i][0]) {
				
				DP[i][0] = countC;
				DP[i][1] = sumC;
			}else if(countC == DP[i][0]) {
			
				DP[i][1] = Math.max(DP[i][1], sumC);
			}
			
			int countD = Integer.MAX_VALUE;
			int sumD = 0;
			
			for(int j=21; j<41; j++) {
				
				if(j % 2 == 0) {

					if(DP[i-j][0] + 1 < countD) {
						countD = DP[i-j][0] + 1;
						sumD = DP[i-j][1];
						
					}else if(DP[i-j][0] + 1 == countD) {
						
						if(DP[i-j][1] > sumD) {
							sumD = DP[i-j][1];
						}
					}
					
				}
			}
			
			if(countD < DP[i][0]) {
				
				DP[i][0] = countD;
				DP[i][1] = sumD;
			}else if(countD == DP[i][0]) {
			
				DP[i][1] = Math.max(DP[i][1], sumD);
			}
		}
		
        return DP[target];
    }

}
