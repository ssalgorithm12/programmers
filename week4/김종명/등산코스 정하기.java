package programmers;

import java.util.ArrayList;

public class 등산코스정하기 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	// 다익스트라
	
	static class Way{
		int target;
		int cost;
		
		public Way(int target, int cost) {
			this.target = target;
			this.cost = cost;
		}
	}
	
	static class P{
		
		ArrayList<Way> container;
		
		public P() {
			container = new ArrayList<>();
		}
		
	}
	
	static P[] spots;
	
	public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
		
		spots = new P[n + 1];
		
		for(int i=0; i<n+1; i++) {
			spots[i] = new P();
		}
		
		int size = paths.length;
		
		for(int i=0; i<size; i++) {

			int a = paths[i][0];
			int b = paths[i][1];
			int cost = paths[i][2];
			
			Way A = new Way(b , cost);
			Way B = new Way(a, cost);
			
			spots[a].container.add(A);
			spots[b].container.add(B);
		}
		
		
		
        int[] answer = {};
        return answer;
    }

}
