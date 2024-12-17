package Programmers;

import java.util.LinkedList;
import java.util.Queue;

public class 미로탈출명령어 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	// d l r u
	// 시간 초과
	// 그리디하게 접근해야할듯
	
	static int n,m, r, c, k;
	static String[] command = {"d", "l", "r", "u"};
	static int[] dr = {1, 0, 0, -1};
	static int[] dc = {0, -1, 1, 0};
	
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
    	
    	this.n= n;
    	this.m = m;
    	this.r = r;
    	this.c = c;
    	this.k = k;
    	
    	int distance = Math.abs(x - r) + Math.abs(y - c);
    	
    	if(distance > k || (k - distance) % 2 == 1) {
    		
    		return "impossible";
    	}
    	
        P result = BFS(x, y);
    	
        if(result == null) {
        	return "impossible";
        }else {
        	return result.answer.toString();
        }
    }
    
    static class P{
    	int x,y;
    	StringBuilder answer;
    	
    	public P(int x, int y, StringBuilder answer) {
    		this.x = x;
    		this.y = y;
    		this.answer = new StringBuilder(answer.toString());
    	}
    }
    
    static P BFS(int x, int y) {
    	
    	P start = new P(x, y, new StringBuilder());
    	
    	Queue<P> container = new LinkedList<>();
    	
    	container.offer(start);
    	
    	int depth = 0;
    	
    	while(!container.isEmpty()) {
    		
    		int size = container.size();
    		
    		for(int i=0; i<size; i++) {
    			
    			P cur = container.poll();
        		
        		int cx = cur.x;
        		int cy = cur.y;
        		
        		if(cx == r && cy == c && depth == k) {
        			return cur;
        		}
        		
        		StringBuilder answer = cur.answer;
        		
        		int distance = Math.abs(cx - r) + Math.abs(cy - c);
        		
        		if(distance > k - depth) {
        			continue;
        		}
        		
        		for(int d=0; d<4; d++) {
        			
        			int nx = cx + dr[d];
        			int ny = cy + dc[d];
        			
        			if(!check(nx, ny)) {
        				continue;
        			}
        			
        			answer.append(command[d]);
        			container.offer(new P(nx, ny, answer));
        			answer.deleteCharAt(answer.length() - 1);
        		}
        			
    		}
    		depth += 1;
    	}
    	
    	return null;
    }
   
    static boolean check(int x, int y) {
    	return x > 0 && y > 0 && x <= n && y <= m;
    }
}
