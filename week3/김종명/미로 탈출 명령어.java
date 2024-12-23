package programmers;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class 미로탈출명령어 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	// d l r u
	
	static int N, M, X, Y, R, C, K;
	
	static String answer;
	
	static class P implements Comparable<P>{
		int x;
		int y;
		int dist;
		String way;
		
		public P(int x, int y, int dist, String way) {
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.way = way;
		}
		
		@Override
		public int compareTo(P o) {
			
			return way.compareTo(o.way);
		}
		
	}
	
	public String solution(int n, int m, int x, int y, int r, int c, int k) {
		
		N = n;
		M = m;
		X = x;
		Y = y;
		R = r;
		C = c;
		K = k;
		
		int distance = Math.abs(X - R) + Math.abs(Y - C);
		
		if(distance > K) {
			
			return "impossible";
		}
		
		boolean isArrive = BFS();
		
		if(isArrive) {
			return answer;
		}else {
			return "impossible";
		}
		
	}
	
	
	static boolean BFS() {

		int[] dr = {1, 0, 0, -1};
		int[] dc = {0, -1, 1, 0};
		String[] command = {"d", "l", "r", "u"};
		
		PriorityQueue<P> container = new PriorityQueue<>();
		
		container.offer(new P(X, Y, 0, ""));
		
		boolean isArrive = false;
		
		while(!container.isEmpty()) {
			
			P cur = container.poll();
			
			int cx = cur.x;
			int cy = cur.y;
			int cd = cur.dist;
			String cw = cur.way;
			
			if(cd == K && cx == R && cy == C) {
				isArrive = true;
				answer = cw;
				break;
			}
			
			if(cd == K) {
				
				continue;
			}
			
			for(int d=0; d<4; d++) {
				
				int nx = cx + dr[d];
				int ny = cy + dc[d];
				int nd = cd + 1;
				String nw = cw.concat(command[d]);
				
				if(!check(nx, ny)) {
					continue;
				}
				
				container.offer(new P(nx, ny, nd, nw));
			}
			
		}
		
		return isArrive;
	}
	
	static boolean check(int x, int y) {
		return x > 0 && y > 0 && x <= N && y <= M;
	}
}
