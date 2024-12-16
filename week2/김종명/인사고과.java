package programmers;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class 인사고과 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(solution(new int[][] {{3, 1}, {2, 2}, {1, 3}, {3, 2}, {2, 3}}));
	}
	
	static class Score implements Comparable<Score>{
		
		int a;
		int b;
		int sum;
		int index;
		
		public Score(int a, int b, int index) {
			this.a = a;
			this.b = b;
			this.sum = a + b;
			this.index = index;
		}
		
		@Override
		public int compareTo(Score o) {
			
			if(this.sum > o.sum) {
				return -1;
			}else if(this.sum == o.sum) {
				return 0;
			}else {
				return 1;
			}
		}
	}
	
	static PriorityQueue<Score> container;

    static public int solution(int[][] scores) {
    	
    	container = new PriorityQueue<>();
    	
    	int wonA = scores[0][0];
    	int wonB = scores[0][1];
    	
    	// 우선순위 queue에 정렬하면서 대입
    	for(int i=0; i<scores.length; i++) {
    		
    		int a = scores[i][0];
    		int b = scores[i][1];
    		int index = i;
    		
    		if(a > wonA && b > wonB) {
    			return -1;
    		}
    		
    		container.offer(new Score(a, b, index));
    	}
    	
    	int rank = 1;
    	int wonSum = wonA + wonB;
    	
    	ArrayList<int[]> list = new ArrayList<>();
    	
    	while(!container.isEmpty()) {
    		
    		Score cur = container.poll();
    		
    		// 원호보다 큰 사람들 list에 추가
    		if(cur.a + cur.b >= wonSum) {
    			list.add(new int[] {cur.a, cur.b});
    		}
    		
    		boolean valid = true;
    		
    		// 인센티브 받을 수 있는 사람인지 비교
    		for(int i=0; i<list.size(); i++) {
    			
    			int[] big = list.get(i);
    			
    			if(cur.a < big[0] && cur.b < big[1]) {
    				valid = false;
    			}
    		}
    		 
    		// 원호에 도달하면 끝
    		if(cur.index == 0) {
    			break;
    		}
    		
    		// 원호보다 점수 높은 사람들일때만 rank++
    		if(valid && cur.a + cur.b > wonSum) {
    			rank += 1;
    		}
    	}

        return rank;
    }
    
}
