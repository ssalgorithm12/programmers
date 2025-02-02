package programmers;

import java.util.LinkedList;
import java.util.Queue;

public class 카드짝맞추기 {

	public static void main(String[] args) {

	}

	// BFS로 카드들을 찾아가는 과정을
	// DFS를 사용해서 최단 거리를 찾아가는 방향으로 고민중
	
	static int size = 4;
	static int cnt;
	static int min_dist;
	static int[] p;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int[][] map = new int[size][size];
	
	static class P{
		int r, c;
		
		public P(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public int solution(int[][] board, int r, int c) {
		
		cnt = 0;
		
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				map[i][j] = board[i][j];
				
				if(map[i][j] > 0) {
					cnt += 1;
				}
			}
		}
		
		cnt /= 2;
		
		min_dist = Integer.MAX_VALUE - 1;
		
		p = new int[cnt];
		
		DFS(0, 1, r, c);
		
		return min_dist;
    }
	
	static void DFS(int depth, int start, int r, int c) {
		
		if(depth == cnt) {
			
			int y = r;
			int x = c;
			int dist = 0;
			
			for(int i=0; i<cnt; i++) {
				
				int[] ans = findPair(p[i], y, x);
				dist += ans[2];
				
				ans = findPair(p[i], ans[0], ans[1]);
				dist += ans[2];
				y = ans[0];
				x = ans[1];
			}
			
			if(dist < min_dist) {
				min_dist = dist;
			}
			
			return;
		}
		
		for(int i=start; i<=cnt; i++) {
			
			p[depth] = i;
			DFS(depth + 1, i+1, r, c);
		}
	}
	
	// 해당 카드의 짝을 찾거나 다음 카드를 찾아주는 BFS 함수
	static int[] findPair(int target, int r, int c) {
		
		Queue<P> container = new LinkedList<>();
		boolean[][] visited = new boolean[size][size];
		
		container.offer(new P(r, c));
		visited[r][c] = true;
		
		// 짝을 찾기까지 최소 거리
		int dist = 0;
		
		while(!container.isEmpty()) {
			
			int turn = container.size();
			
			for(int i=0; i<turn; i++) {
				
				P cur = container.poll();
				
				int cr = cur.r;
				int cc = cur.c;
				
				if(map[cr][cc] == target) {
					// 도착하면 해당 카드들 0으로 변경
					map[r][c] = 0;
					map[cr][cc] = 0;
						
					return new int[] {cr, cc, dist};
				}
				
				for(int d=0; d<4; d++) {
					
					int nr = cr + dr[d];
					int nc = cc + dc[d];
					
					if(!check(nr, nc)) {
						continue;
					}
					
					if(visited[nr][nc]) {
						continue;
					}
					
					container.offer(new P(nr, nc));
					visited[nr][nc] = true;
				}
				
			}// turn
			
			dist += 1;
		}
		
		return new int[] {-1, -1, -1};
	}
	
	// map 범위 벗어나는지 check하는 함수
	static boolean check(int r, int c) {
		return r >= 0 && c >= 0 && r < size && c < size;
	}
}
