package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// 통과 (170.46ms, 184MB)

// BFS를 돌면서 모든 최단 거리를 기록

public class 부대복귀 {
	
	// 경로를 저장할 class
	static class P{
		
		ArrayList<Integer> connect;
		
		public P() {
			connect = new ArrayList<>();
		}
		
	}
	
	static P[] road;
	static int finish;
	static int[] distance;

    static public int[] solution(int n, int[][] roads, int[] sources, int destination) {
    	
    	road = new P[n+1];
    	finish = destination;
    	
    	for(int i=0; i<n+1; i++) {
    		
    		road[i] = new P();
    	}
    	
    	for(int i=0; i<roads.length; i++) {
    		
    		int a = roads[i][0];
    		int b = roads[i][1];
    		
    		road[a].connect.add(b);
    		road[b].connect.add(a);
    	}
    	
    	int[] answer = new int[sources.length];
    	
    
    		
    	distance = new int[n+1];
    		
    	// 모든 거리를 최대로 저장
    	Arrays.fill(distance, Integer.MAX_VALUE - 1);
    		
    	BFS();
    	
    	// 답 도출
    	for(int i=0; i<sources.length; i++) {
    		
    		answer[i] = distance[sources[i]];
    		
    		if(answer[i] == Integer.MAX_VALUE - 1) {
    			answer[i] = -1;
    		}
    	}
    	
        return answer;
    }
    
    static void BFS() {
    	 	
    	Queue<Integer> container = new LinkedList<>();
    	
    	distance[finish] = 0;
    	
    	container.offer(finish);
    	
    	while(!container.isEmpty()) {
    		
    		int cur = container.poll();
    		int cDistance = distance[cur];
    		
    		for(int i=0; i<road[cur].connect.size(); i++) {
    			
    			int next = road[cur].connect.get(i);
    			
    			// 거리가 이미 최소값이면 continue
    			if(distance[next] <= cDistance + 1) {
    				continue;
    			}
    			
    			// 아니라면 거리를 최소값으로 갱신
    			distance[next] = cDistance + 1;
    			
    			container.offer(next);
    		}
    	}
    		
    }
}
