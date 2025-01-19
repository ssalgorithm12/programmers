package programmers;

import java.util.HashMap;

/*
 * 피드백
 * [가은]
 * - 정답 배열을 따로 만들 필요 없이
 *   부모와 금액을 묶은 클래스를 분리하여 관리하고
 *   바로 리턴하면 더 효율적일 것 같음
 */

public class 다단계칫솔판매 {
	
	public static void main(String[] args) {
		
	}

	// 기존에 class를 배열로 선언해서 문제를 풀려고 했으나 탐색량이 너무 많아서
	// Map을 사용해서 탐색 횟수를 줄일 수 있다는 점을 참고함
	
	// 사람 class 생성
	static class P{
		
		P parent;
		int sell;
		
		public P(P parent, int sell) {
			this.parent = parent;
			this.sell = sell;
		}
		
		// 추천인에게까지 돈 계산해주는 재귀함수
		public void calcSell(int sell) {
			int sellToParent = sell / 10;
			
			this.sell += sell - sellToParent;
			
			if(this.parent != null) {
				if(sellToParent > 0) {
					this.parent.calcSell(sellToParent);
				}
			}
		}
	}
	
	public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
		
		int size = enroll.length;
		
		// Map 생성
		HashMap<String, P> map = new HashMap<>();
		
		// Map에 키, 값 생성
		for(int i=0; i<size; i++) {
			
			map.put(enroll[i], new P(null, 0));
		}
		
		// Map에 추천인 설정
		for(int i=0; i<size; i++) {
			if(referral[i].equals("-")) {
				continue;
			}
			
			map.get(enroll[i]).parent = map.get(referral[i]);
		}
		
		// Map에 돈 할당
		for(int i=0; i<amount.length; i++) {
			
			map.get(seller[i]).calcSell(amount[i] * 100);
		}
		
		int[] answer = new int[size];
		
		for(int i=0; i<size; i++) {
			answer[i] = map.get(enroll[i]).sell;
		}
		
        return answer;
    }
}
