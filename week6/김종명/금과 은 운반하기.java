package programmers;

import java.util.PriorityQueue;

/*
 * 
 * 그리디하게 정렬해서 풀기
 * 
 * 걸리는 시간과 운반할 수 있는 양으로 최적의 값을 찾고싶었음
 * 
 * -> 금과 은이 애매하게 남아있을 경우 처리를 못하겠음
 * 
 * 이분탐색은 이해가 잘 안됨
 * 
 * */

 /*
  * <가은 피드백>
  * 그리디가 적합하지 않은 이유: 조건의 복잡성
  * 1. 도시마다 운반 시간과 무게 제한이 다름
  * 2. 금과 은의 요구량과 합산량을 모두 만족해야 함
  * -> 단순한 기준에 따라 순간의 최적의 선택을 하는 것이 정답을 보장할 수 없음
  * -> 이진탐색을 통해 결정문제로 접근하여 조합을 계산해야 함
  * 
  *[보광]
  * - 그리디에 대해서는 가은님과 같은 의견 
  */

public class 금과은운반하기 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		solution(90, 50, new int[]{70, 70, 0}, new int[] {0, 0, 500}, new int[] {100, 100, 2}, new int[] {4, 8, 1});
		
	}
	
	static class Weight implements Comparable<Weight>{
		
		int g;
		int s;
		int w;
		int t;
		
		public Weight(int g, int s, int w, int t) {
			this.g = g;
			this.s = s;
			this.w = w;
			this.t = t;
		}
		
		@Override
		public int compareTo(Weight o) {
			
			long cw = Math.min(g + s, w);
			long nw = Math.min(o.g + o.s, o.w);
			
			
			// 무게와 시간의 비율이 가장 좋은 수로 정렬
			double eff1 = (double) cw / t;
			double eff2 = (double) nw / o.t;
			
			if(eff1 > eff2) {
				return -1;
			}else if(eff1 == eff2) {
				return 0;
			}else {
				return 1;
			}
		}
	}
	
    static public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
    	
    	int size = g.length;
    	
    	PriorityQueue<Weight> v = new PriorityQueue<>();	
    		
    	for(int i=0; i<size; i++) {
    		
    		v.offer(new Weight(g[i], s[i], w[i], t[i]));

    	}
    	
    	while(!v.isEmpty()) {
    		Weight cur = v.poll();
    		System.out.println(cur.g + " " + cur.s + " " + cur.w + " " + cur.t);
    	}
    	
    	long remainG = a;
    	long remainS = b;
    	
    	while(!v.isEmpty() && (remainG > 0 || remainS > 0)) {

    		Weight cur = v.poll();
    		Weight next = v.peek();
    		
    		
    		
    		
    	}
    	
        long answer = -1;
        return answer;
    }

}
