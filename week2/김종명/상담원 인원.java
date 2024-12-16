package programmers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class 상담원인원 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		solution(3, 5, new int[][]{{10, 60, 1},{15, 100, 3}});
	}
	
	static int K, N;
	static int[][] input;
	static int[] mentos;
	static int minWaiting;
	
    static public int solution(int k, int n, int[][] reqs) {
        
    	K = k;
    	N = n;
    	
    	mentos = new int[K+1];
    	input = new int[reqs.length][3];
    	
    	for(int i=0; i<reqs.length; i++) {
    		for(int j=0; j<3; j++) {
    			input[i][j] = reqs[i][j];
    		}
    	}
    	
    	for(int i=1; i<k+1; i++) {
    		mentos[i] = 1;
    	}
    	minWaiting = Integer.MAX_VALUE - 1;
    	DFS(0, 1);
    	
  
        return minWaiting;
        
    }
    
    static void DFS(int depth, int start) {
    	
    	if(depth == N-K) {
    		
    		int time = checkTime();
    		
    		if(time < minWaiting) {
    			minWaiting = time;
    		}
    		
    		return;
    	}
    	
    	for(int i=start; i<K+1; i++) {
    		
    		mentos[i] += 1;
    		
    		DFS(depth+1, i+1);
    		
    		mentos[i] -= 1;
    	}
    	
    }
    
    static class Guest{
    	int start;
    	int cost;
    	int type;
    	
    	public Guest(int start, int cost, int type) {
    		this.start = start;
    		this.cost = cost;
    		this.type = type;
    	}
    }
    
    static Queue<Guest> container;
    static PriorityQueue<Integer>[] mento;
    
    static int checkTime() {
    	
    	int size = input.length;
    	
    	container = new LinkedList<>();
    	
    	for(int i=0; i<size; i++) {
    		
    		int start = input[i][0];
    		int cost = input[i][1];
    		int type = input[i][2];
    		
    		container.offer(new Guest(start, cost, type));
    	}
    	
    	int waiting = 0;
    	
    	mento = new PriorityQueue[K+1];
    	
    	for(int i=1; i<K+1; i++) {
    		mento[i] = new PriorityQueue<>();
    	}
    	
    	while(!container.isEmpty()) {
    			
    		Guest cur = container.poll();
    			
    		int start = cur.start;
    		int cost = cur.cost;
    		int type = cur.type;
    		
    		if(!mento[type].isEmpty() && mento[type].peek() <= start) {
    			mento[type].poll();
    		}
    			
    		if(mento[type].size() < mentos[type]) {
    				
    			mento[type].offer(start + cost);
    				
    		}else if(mento[type].size() == mentos[type]) {
    				
    			int time = mento[type].poll();
    			
    			waiting += time - start;
    			
    			mento[type].offer(time + cost);
    		}		

    	}
    	
    	return waiting;
    }

}
