package Programmers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class 상담원인원 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		solution(2, 3, new int[][]{
		    {0, 100, 1},
		    {10, 50, 1},
		    {20, 30, 1},
		    {30, 10, 2},
		    {40, 10, 2},
		    {50, 10, 2},
		    {60, 10, 1}
		    });
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
    	
    	System.out.println(minWaiting);
        return minWaiting;
        
    }
    
    static void DFS(int depth, int start) {
    	
    	if(depth == N-K) {
    		
    		int time = checkTime();
    		
    		if(time < minWaiting) {
    			
    			minWaiting = time;
    		}
    		
    		System.out.println(Arrays.toString(mentos));
    		
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
    		
    		if(mento[type].isEmpty()) {
    			// 멘토가 남아있다면 추가
    			mento[type].offer(start + cost);
    			
    		}else {
    			
    			while(!mento[type].isEmpty()  && mento[type].peek() <= start) {
    				// 이미 시간 지난 멘토들 빼주기
    				mento[type].poll();
    			}
    			
    			if(mento[type].size() >= mentos[type]) {
    				// 이미 멘토가 꽉 차 있다면
    				int endTime = mento[type].poll();
    				
    				waiting += endTime - start;
    				
    				mento[type].offer(endTime + cost);
    			}else {
    				// 멘토가 자리가 비어있다면
    				mento[type].offer(start + cost);
    			}
    			
    		}

    	}
    	
    	return waiting;
    }

}
