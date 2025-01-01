package programmers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 코딩테스트공부 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	// [알고력, 코딩력, 증가하는 알고력, 증가하는 코딩력, 걸리는 시간]
	
	// 다익스트라
	
	// 정확성 50.0, 효율성 20.0
	
	static class P{
		int alp;
		int cop;
		
		P(int alp, int cop){
			this.alp = alp;
			this.cop = cop;
		}
	}
	
	static int min_time;
	static int max_alp;
	static int max_cop;
	static int[][] map;
	static P start;
	
    public int solution(int alp, int cop, int[][] problems) {
    	
    	start = new P(alp, cop);
    	
    	max_alp = -1;
    	max_cop = -1;
    	
    	for(int i=0; i<problems.length; i++) {
    		
    		if(problems[i][0] > max_alp) {
    			max_alp = problems[i][0];
    		}
    		
    		if(problems[i][1] > max_cop) {
    			max_cop = problems[i][1];
    		}
    	}
    	
    	map = new int[181][181];
    	
    	for(int i=0; i<181; i++) {
    		Arrays.fill(map[i], Integer.MAX_VALUE - 1);
    	}
    	
    	min_time = Integer.MAX_VALUE - 1;
    	
    	BFS(problems);
    	
      
        return min_time;
    }
    
    static void BFS(int[][] problems) {
    	
    	Queue<P> container = new LinkedList<>();
    	
    	container.offer(start);
    	
    	map[start.alp][start.cop] = 0;
    	
    	while(!container.isEmpty()) {
    		
    		P cur = container.poll();
    		
    		int ca = cur.alp;
    		int cc = cur.cop;
    		
    		if( ca >= max_alp && cc >= max_cop) {
    			
    			if(map[ca][cc] < min_time) {
    				
    				min_time = map[ca][cc];
    				
    			}
    			continue;
    		}
    		
    		if(ca + 1 < 181) {
    			
    			if(map[ca + 1][cc] > map[ca][cc] + 1) {
        			
    				map[ca + 1][cc] = map[ca][cc] + 1;
        			container.offer(new P(ca + 1, cc));
    			}
    		}
    		
    		if(cc + 1 < 181) {
    			
    			if(map[ca][cc + 1] > map[ca][cc] + 1) {
        			
    				map[ca][cc + 1] = map[ca][cc] + 1;
        			container.offer(new P(ca, cc + 1));
    			}
    		}
    		
    		
    		for(int i=0; i<problems.length; i++) {
    			
    			int pa = problems[i][0];
    			int pc = problems[i][1];
    			int paw = problems[i][2];
    			int pcw = problems[i][3];
    			int pt = problems[i][4];
    			
    			if(ca >= pa && cc >= pc) {
    				
    				if( ca + paw < 181 && cc + pcw < 181 ) {
    					if(map[ca + paw][cc + pcw] > map[ca][cc] + pt) {
        					map[ca + paw][cc + pcw] = map[ca][cc] + pt;
        					container.offer(new P(ca + paw, cc + pcw));
        				}
    				}
    			}
    		}
    		
    	}
    }
}
