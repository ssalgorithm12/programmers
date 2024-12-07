package programmers;

import java.util.Arrays;

public class 수레움직이기 {

	// 33.7 / 100
	
	static P red;
	static P blue;
	static boolean[][] redV;
	static boolean[][] blueV;
	static int[][] map;
	static int minCount;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) {
		
		System.out.println(solution(new int[][] {{1, 0, 2}, {0, 0, 0}, {5, 0, 5}, {4, 0, 3}}));
		
	}
	
	static class P{
		int y;
		int x;
		
		public P(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	// 0 빈칸 1 빨강 시작 2 파랑 시작 3 빨강 도착 4 파랑 도착 5 벽
	
	public static int solution(int[][] maze) {
		
		int ylength = maze.length;
		int xlength = maze[0].length;
		
		map = new int[ylength][xlength];
		
		
		for(int i=0; i<ylength; i++) {
			for(int j=0; j<xlength; j++) {
				
				if(maze[i][j] == 1) {
					
					red = new P(i, j);
					
				}else if(maze[i][j] == 2) {
					
					blue = new P(i, j);
					
				}
				
				map[i][j] = maze[i][j];
			}
		}
		
		minCount = Integer.MAX_VALUE - 1;
		
		redV = new boolean[ylength][xlength];
		blueV = new boolean[ylength][xlength];
		
		redV[red.y][red.x] = true;
		blueV[blue.y][blue.x] = true;
		redDFS(red, blue, maze, 0);
		
		redV = new boolean[ylength][xlength];
		blueV = new boolean[ylength][xlength];
		
		for(int i=0; i<ylength; i++) {
			for(int j=0; j<xlength; j++) {
				
				map[i][j] = maze[i][j];
				
			}
		}
		
		redV[red.y][red.x] = true;
		blueV[blue.y][blue.x] = true;
		blueDFS(red, blue, maze, 0);
		
		if(minCount == Integer.MAX_VALUE - 1) {
			minCount = 0;
		}
		
		return minCount;
	}
	
	static void redDFS(P red, P blue, int[][] maze, int cnt) {
		
		if(maze[red.y][red.x] == 3 && maze[blue.y][blue.x] == 4) {
			
			if( cnt < minCount ) {
				minCount = cnt;
			}
			
			return;
		}
		
		for(int d=0; d<4; d++) {
			
			P nr = new P(red.y + dr[d], red.x + dc[d]);
			
			if(!check(nr.y, nr.x)) {
				continue;
			}
			
			if(map[nr.y][nr.x] == 2 || map[nr.y][nr.x] == 5) {
				continue;
			}
			
			if(redV[nr.y][nr.x]) {
				continue;
			}
			
			map[red.y][red.x] = 0;
			map[nr.y][nr.x] = 1;
			
			for(int dd=0; dd<4; dd++) {
				
				P nb = new P(blue.y + dr[dd], blue.x + dc[dd]);
				
				if(!check(nb.y, nb.x)) {
					continue;
				}
				
				if(map[nb.y][nb.x] == 1 || map[nb.y][nb.x] == 5) {
					continue;
				}
				
				if(blueV[nb.y][nb.x]) {
					continue;
				}
				
				redV[nr.y][nr.x] = true;
				blueV[nb.y][nb.x] = true;
				

				
				map[blue.y][blue.x] = 0;
				map[nb.y][nb.x] = 2;
				
				redDFS(nr, nb, maze, cnt + 1);
				
				map[nb.y][nb.x] = 0;
				map[blue.y][blue.x] = 2;

				blueV[nb.y][nb.x] = false;
				redV[nr.y][nr.x] = false;
			}	
			
			map[nr.y][nr.x] = 0;
			map[red.y][red.x] = 1;
			
		}
	}// red first DFS
	
	static void blueDFS(P red, P blue, int[][] maze, int cnt) {
		
		if(maze[red.y][red.x] == 3 && maze[blue.y][blue.x] == 4) {
			
			if( cnt < minCount ) {
				
				minCount = cnt;
			}
			
			return;
		}
		
		for(int d=0; d<4; d++) {
			
			P nb = new P(blue.y + dr[d], blue.x + dc[d]);
			
			if(!check(nb.y, nb.x)) {
				continue;
			}
			
			if(map[nb.y][nb.x] == 1 || map[nb.y][nb.x] == 5) {
				continue;
			}
			
			if(blueV[nb.y][nb.x]) {
				continue;
			}
			
			map[blue.y][blue.x] = 0;
			map[nb.y][nb.x] = 2;
			
			for(int dd=0; dd<4; dd++) {
				
				P nr = new P(red.y + dr[dd], red.x + dc[dd]);
				
				if(!check(nr.y, nr.x)) {
					continue;
				}
				
				if(map[nr.y][nr.x] == 2 || map[nr.y][nr.x] == 5) {
					continue;
				}
				
				if(redV[nr.y][nr.x]) {
					continue;
				}
				
				redV[nr.y][nr.x] = true;
				blueV[nb.y][nb.x] = true;
				
				map[red.y][red.x] = 0;
				map[nr.y][nr.x] = 1;
				
				blueDFS(nr, nb, maze, cnt + 1);
				
				map[nr.y][nb.x] = 0;
				map[red.y][red.x] = 1;

				blueV[nb.y][nb.x] = false;
				redV[nr.y][nr.x] = false;
			}	
			
			map[nb.y][nb.x] = 0;
			map[blue.y][blue.x] = 2;
			
		}
	}// blue first DFS
	
	static boolean check(int y, int x) {
		return y >= 0 && x >= 0 && y < map.length && x < map[0].length;
	}
	
}
