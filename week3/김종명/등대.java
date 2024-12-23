package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class 등대 {

	public static void main(String[] args) {
		
		solution(8 , new int[][] {{1, 2}, {1, 3}, {1, 4}, {1, 5}, {5, 6}, {5, 7}, {5, 8}});

	}
	
	static class P implements Comparable<P>{
		
		int index;
		ArrayList<Integer> connect;
		
		public P(int index) {
			this.index = index;
			connect = new ArrayList<>();
		}
		
		@Override
		public int compareTo(P o) {
			
			if(this.connect.size() > o.connect.size()) {
				return -1;
			}else if(this.connect.size() == o.connect.size()) {
				return 0;
			}else {
				return 1;
			}
			
		}
	}
	
    static public int solution(int n, int[][] lighthouse) {
    	
    	P[] container = new P[n + 1];
    	
    	for(int i=0; i<n+1; i++) {
    		container[i] = new P(i);
    	}
    	
    	for(int i=0; i<lighthouse.length; i++) {
    		
    		int a = lighthouse[i][0];
    		int b = lighthouse[i][1];
    		
    		container[a].connect.add(b);
    		container[b].connect.add(a);
    	}
    	
    	Arrays.sort(container);
    	
    	for(int i=0; i<n+1; i++) {
    		System.out.println(container[i].index);
    	}
    	
    	int answer = 0;
    	
        return answer;
    }

}
