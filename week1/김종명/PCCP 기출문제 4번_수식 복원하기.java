package programmers;

import java.util.Arrays;

// 채점 결과 47.8 / 100.0

public class 수식복원하기 {

	static String[] expressions = {"1 + 1 = 2", "1 + 3 = 4", "1 + 5 = X", "1 + 2 = X"};
	static boolean[] isCorrect;
	static int maxNum;
	
	public static void main(String[] args) {
		
		maxNum = 2;
		isCorrect = new boolean[10];
		
		System.out.println(Arrays.toString(solution(expressions)));
		
	}
	
	
	static String[] solution(String[] expressions) {
		
		int size = expressions.length;
		int count = 0;
		
		String[] answer;
		
		// 최대 숫자 찾기
		for(int i=0; i<size; i++) {
			
			String[] expression = expressions[i].split(" ");
			
			String A = expression[0];
			String B = expression[2];
			String C = expression[4];
			
			if(C.equals("X")) {
				count += 1;
			}
			
			findMaxNum(A, B, C);
		}
		
		// 답변 개수 찾기
		answer = new String[count];
		
		// 몇 진법이 가능한지 찾기
		for(int i=0; i<size; i++) {
			
			String[] expression = expressions[i].split(" ");
			
			String C = expression[4];
			
			if(C.equals("X")) {
				continue;
			}
			
			String A = expression[0];
			String B = expression[2];
			
			String cal = expression[1];
			
			for(int j=maxNum; j<10; j++) {
				checkCalcul(j, A, B, C, cal);
			}
		}
		
		// 가능한 모든 진법으로 값 찾기
		int index = 0;
		for(int i=0; i<size; i++) {
			
			String[] expression = expressions[i].split(" ");
			
			String C = expression[4];
			
			if(!C.equals("X")) {
				continue;
			}
			
			String A = expression[0];
			String B = expression[2];
			
			String cal = expression[1];
			
			int num = -1;
			for(int j=maxNum+1; j<10; j++) {
				if(isCorrect[j]) {
					
					if(cal.equals("-")) {
						
						int ans = decodeNum(j, A) - decodeNum(j, B);
						// 값이 다를 시 X 값은 ? 가 됩니다.
						if(num != -1 && num != encodeNum(j, ans)) {
							C = "?";
						}else {
							num = encodeNum(j, ans);
						}
						
					}else {
						
						int ans = decodeNum(j, A) + decodeNum(j, B);
						
						if(num != -1 && num != encodeNum(j, ans)) {
							C = "?";
						}else {
							num = encodeNum(j, ans);
						}
					}
				}
			}// C 값 찾는 for 
			
			if(!C.equals("?")) {
				C = Integer.toString(num);
			}
			
			answer[index] = parseString(A, B, C, cal, "=");
			index += 1;
			
		}
		
		return answer;
	}
	
	// String 변환
	static String parseString(String A, String B, String C, String cal, String equal) {
		return A + " " + cal + " " + B + " " + equal + " " + C;
	}
	
	// 진법 변환
	static int decodeNum(int n, String A) {
		
		int a = Integer.parseInt(A);
		
		int result = 0;
		int step = 0;
		
		while(a > 0) {
			
			result += (a % 10) * Math.pow(n, step);
			
			step += 1;
			
			a = a / 10;
			
		}
		
		return result;
	}
	
	// 진법 재변환
	static int encodeNum(int n, int A) {
		
		int result = 0;
		int step = 0;
		int a = A;
		
		while(a > 0) {
			
			result += (a % n) * (Math.pow(10, step));
			
			a = a / n;
			step += 1;
		}
	
		return result;
	}
	
	// 가장 큰 숫자가 무엇인지 찾는 함수
	static void findMaxNum(String A, String B, String C) {
		
		int a = Integer.parseInt(A);
		int b = Integer.parseInt(B);
		int c = 0;
		
		if(!C.equals("X")) {
			c = Integer.parseInt(C);
		}
		
		splitNum(a);
		splitNum(b);
		splitNum(c);
	}
	
	//  정수 쪼개기
	static void splitNum(int n) {
		
		while( n > 0) {
			int num = n % 10;
			
			if(num > maxNum) {
				maxNum = num;
			}
			
			n = n / 10;
		}
	}
	
	// 연산 확인 함수
	static void checkCalcul(int n, String A, String B, String C, String cal) {
		
		int a = decodeNum(n, A);
		int b = decodeNum(n, B);
		int c = decodeNum(n, C);
		
		if(cal.equals("-")) {
			
			if(a - b == c) {
				isCorrect[n] = true;
			}else {
				isCorrect[n] = false;
			}
						
		}else {
			
			if(a + b == c) {
				isCorrect[n] = true;
			}else {
				isCorrect[n] = false;
			}
		}
		
	}
	
}
