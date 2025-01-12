package programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 정규화 함수 인터넷 참고

// DFS 백트래킹 돌기전 블럭 비교 함수까지 제작 완료

// DFS 어떻게 돌릴지 아직 고민중..

public class 퍼즐조각채우기 {
	
	public static void main(String[] args) {
		
		
	}
	
	ArrayList<List<int[]>> empty;
	ArrayList<List<int[]>> block;
	static int[][] board;
	static int[][] blocks;
	static boolean[][] visited;

	public int solution(int[][] game_board, int[][] table) {
		
		int size = game_board.length;
		
		empty = new ArrayList<>();
		block = new ArrayList<>();
		
		board = new int[size][size];
		blocks = new int[size][size];
		visited = new boolean[size][size];
		
		for(int i=0; i<size; i++) {
			
			board[i] = game_board[i].clone();
			blocks[i] = table[i].clone();
		}
		
		for(int y=0; y<size; y++) {
			for(int x=0; x<size; x++) {
				
				if(visited[y][x]) {
					continue;
				}
				
				if(board[y][x] == 1) {

					visited[y][x] = true;
					
				}else {
					
					List<int[]> space = addSpace(y, x, 0, board);
					
					empty.add(normalize(space));
				}
			}
		}
		
		visited = new boolean[size][size];
		
		for(int y=0; y<size; y++) {
			for(int x=0; x<size; x++) {
				
				if(visited[y][x]) {
					continue;
				}
				
				if(blocks[y][x] == 0) {

					visited[y][x] = true;
					
				}else {
					
					List<int[]> space = addSpace(y, x, 1, blocks);
					
					block.add(normalize(space));
				}
			}
		}
		
		
        int answer = -1;
        return answer;
    }
	
	// 블럭이 맞는지 확인하는 함수
	static boolean isMatch(List<int[]> empty,List<int[]> block) {
		
		if(empty.size() != block.size()) {
			return false;
		}
		
		
		Collections.sort(empty, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);
		
		
		for(int d=0; d<4; d++) {
			
			List<int[]> rotated = new ArrayList<>();
			
			int minX = Integer.MAX_VALUE;
			int minY = Integer.MAX_VALUE;
			
			for(int[] b : block) {
				
				int y = b[1];
				int x = -b[0];
				
				rotated.add(new int[] {y, x});
				
				if(x < minX ) {
					minX = x;
				}
				
				if(y < minY ) {
					minY = y;
				}
			}
			
			for(int[] r : rotated) {
				r[0] -= minY;
				r[1] -= minX;
			}
			
			Collections.sort(rotated, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);
			
			boolean isValid = true;
			
			for(int i=0; i<empty.size(); i++) {
				
				int[] e = empty.get(i);
				int[] r = rotated.get(i);
				
				if(e[0] != r[0] || e[1] != r[1]) {
					isValid = false;
				}
				
			}
			
			if(isValid) {
				return true;
			}
			
		}
		
		return false;
		
	}
	
	// 공간을 찾아주는 BFS 함수
	static List<int[]> addSpace(int y, int x, int type, int[][] board) {
		
		List<int[]> answer = new ArrayList<>();
		
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};
		
		Queue<int[]> container = new LinkedList<>();
		
		int[] start = {y, x};
		
		container.offer(start);
		visited[y][x] = true;
		
		while(!container.isEmpty()) {
			
			int[] cur = container.poll();
			
			int cy = cur[0];
			int cx = cur[1];
			
			for(int d=0; d<4; d++) {
				
				int ny = cy + dr[d];
				int nx = cx + dc[d];
				
				if(!check(ny, nx)) {
					continue;
				}
				
				if(visited[ny][nx]) {
					continue;
				}
				
				if(board[ny][nx] != type) {
					continue;
				}
				
				container.offer(new int[] {ny, nx});
				visited[ny][nx] = true;
				answer.add(new int[] {ny, nx});
			}
		}
		
		return answer;
	}
	
	// 정규화 함수
	static List<int[]> normalize(List<int[]> data){
		
		if(data == null || data.isEmpty()) return data;
		
		int minX = Integer.MAX_VALUE;
		int minY = Integer.MAX_VALUE;
		
		for(int[] d: data) {
			minY = Math.min(minY, d[0]);
			minX = Math.min(minX, d[1]);
		}
		
		for(int[] d: data) {
			d[0] -= minY;
			d[1] -= minX;
		}
		
		return data;
	}
	
	// 배열 범위 벗어나는지 체크
	static boolean check(int y, int x) {
		
		return y >= 0 && x >= 0 && y < board.length & x < board.length;
	}

}
