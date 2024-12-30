package programmers;

public class 고고학최고의발견 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	// static int[][] dr = {{1, 1, 1, 2}, {-1, 0, 0, 1}, {-2, -1, -1, -1}, {-1, 0, 1, 0}};
	// static int[][] dc = {{-1, 0, 1, 0}, {-1, -2, -1, -1}, {0, -1, 0, 1}, {1, 1, 1, 2}};
	static int minCount;
	
	public int solution(int[][] clockHands) {
		
		minCount = Integer.MAX_VALUE - 1;
		
		up_first(clockHands);
		
		left_first(clockHands);
		
		down_first(clockHands);
		
		right_first(clockHands);
		
        return minCount;
    }
	
	static void up_first(int[][] clockHands) {
		
		int size = clockHands.length;
		
		int[][] map = copy_map(clockHands);
		
		int count = 0;
		
		int[] dr = {1, 1, 1, 2};
		int[] dc = {-1, 0, 1, 0};
		
		for(int i=0; i<size; i++) {
			
			for(int j=0; j<size; j++) {
				
				if(map[i][j] > 0) {
					
					int weight = 4 - map[i][j];
					
					change_map(map, dr, dc, i, j, weight, size);
					
					count += weight;
				}
			}
		}
		
		if(count < minCount) {
			minCount = count;
		}
		
	}// up_first

	static void left_first(int[][] clockHands) {
		
		int size = clockHands.length;
		
		int[][] map = copy_map(clockHands);
		
		int count = 0;
		
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {1, 1, 1, 2};
		
		for(int i=0; i<size; i++) {
			
			for(int j=0; j<size; j++) {
				
				if(map[j][i] > 0) {
					
					int weight = 4 - map[j][i];
					
					change_map(map, dr, dc, j, i, weight, size);
					
					count += weight;
				}
			}
		}
		
		if(count < minCount) {
			minCount = count;
		}
		
	}// left_first
	
	static void down_first(int[][] clockHands) {
		
		int size = clockHands.length;
		
		int[][] map = copy_map(clockHands);
		
		int count = 0;
		
		int[] dr = {-2, -1, -1, -1};
		int[] dc = {0, -1, 0, 1};
		
		for(int i=size-1; i>=0; i--) {
			
			for(int j=0; j<size; j++) {
				
				if(map[i][j] > 0) {
					
					int weight = 4 - map[i][j];
					
					change_map(map, dr, dc, i, j, weight, size);
					
					count += weight;
				}
			}
		}
		
		if(count < minCount) {
			minCount = count;
		}
		
	}// down_first
	
	static void right_first(int[][] clockHands) {
		
		int size = clockHands.length;
		
		int[][] map = copy_map(clockHands);
		
		int count = 0;
		
		int[] dr = {-1, 0, 0, 1};
		int[] dc = {-1, -2, -1, -1};
		
		for(int i=size-1; i>=0; i--) {
			
			for(int j=0; j<size; j++) {
				
				if(map[j][i] > 0) {
					
					int weight = 4 - map[j][i];
					
					change_map(map, dr, dc, j, i, weight, size);
					
					count += weight;
				}
			}
		}
		
		if(count < minCount) {
			minCount = count;
		}
		
	}// right_first
	
	
	
	static void change_map(int[][] map, int[] dr, int[] dc, int i, int j, int weight, int size) {
		
		map[i][j] = (map[i][j] + weight) % 4;
		
		for(int d=0; d<4; d++) {
			
			if(!check(i + dr[d], j + dc[d], size)) {
				continue;
			}
			
			map[i + dr[d]][j + dc[d]] = (map[i + dr[d]][j + dc[d]] + weight) % 4;
		}
	}
	
	static int[][] copy_map(int[][] clockHands){
		
		int size = clockHands.length;
		
		int[][] map = new int[size][size];
		
		for(int i=0; i<size; i++) {
			map[i] = clockHands[i].clone();
		}
		
		return map;
	}// copy_map
	
	static boolean check(int x, int y, int size) {
		
		return x >= 0 && y >= 0 && x < size && y < size;
	}// check

}
