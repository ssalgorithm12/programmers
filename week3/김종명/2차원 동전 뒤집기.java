package programmers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class 동전뒤집기 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int answer = solution(new int[][] {{0, 0, 0, 0, 0}, {0,1,1,1 ,1}, {0,1,1,1 ,1} ,{0,1,1,1 ,1}, {0,1,1,1 ,1}}, 
				new int[][] {{0, 0, 0, 0, 0}, {0,1,1,1 ,1}, {0,1,1,1 ,1} ,{0,1,1,1 ,1}, {0,1,1,1 ,1}});
	
		System.out.println(answer);
	}
	
	
	
	static class P{
		
		int[][] map;
		int count;
		ArrayList<String> visited;
		
		public P(int[][] map, int count, ArrayList<String> visited) {
			
			this.map = map;
			this.count = count;
			this.visited = visited;
		}
		
	}
	
    static public int solution(int[][] beginning, int[][] target) {
         	
    	int answer = BFS(beginning, target);
    	
        return answer;
    }
    
    static int BFS(int[][] beginning, int[][] target) {
    	
    	Queue<P> container = new LinkedList<>();
    	
    	P start = new P(beginning, 0, new ArrayList<String>());
    	
    	container.offer(start);
    	
    	while(!container.isEmpty()) {
    		
    		P cur = container.poll();
    		
    		int[][] map = cur.map;
    		int count = cur.count;
    		ArrayList<String> visited = cur.visited;
    		
    		if(check(map, target)) {
    			return count;
    		}
    		
    		for(int r=0; r<map.length; r++) {
    			
    			changeRow(map, r);
    			
    			String v = convertMap(map);
    			
    			if(visited.contains(v)) {
    				continue;
    			}
    			
    			ArrayList<String> nv = new ArrayList<>();
    			
    			for(int i=0; i<visited.size(); i++) {
    				
    				nv.add(visited.get(i));
    			}
    			
    			nv.add(v);
    			
    			container.offer(new P(map, count+1, nv));
    			
    			changeRow(map, r);
    			
    		}
    		
    		for(int c=0; c<map.length; c++) {
    			
    			changeCol(map, c);
    			
    			String v = convertMap(map);
    			
    			if(visited.contains(v)) {
    				continue;
    			}
    			
    			ArrayList<String> nv = new ArrayList<>();
    			
    			for(int i=0; i<visited.size(); i++) {
    				
    				nv.add(visited.get(i));
    			}
    			
    			nv.add(v);
    			
    			container.offer(new P(map, count+1, nv));
    			
    			changeCol(map, c);
    			
    		}
    	}
    	
    	return -1;
    	
    }
    
    
    // 세로
    static void changeRow(int[][] map, int row) {
    	
    	int cSize = map[0].length;
    	
    	for(int i=0; i<cSize; i++) {
    		
    		map[row][i] = (map[row][i] + 1) % 2;
    		
    	}
    	
    }
    
    // 가로
    static void changeCol(int[][] map, int col) {
    	
    	int rSize = map[0].length;
    	
    	for(int i=0; i<rSize; i++) {
    		
    		map[i][col] = (map[i][col] + 1) % 2;
    		
    	}
    	
    }
    
    // String으로 변환
    static String convertMap(int[][] map) {
    	
    	String result = "";
    	
    	int rSize = map.length;
    	int cSize = map[0].length;
    	
    	for(int i=0; i<rSize; i++) {
    		for(int j=0; j<cSize; j++) {
    		
    			result += Integer.toString(map[i][j]);
    			
    		}
    	}
    	
    	return result;
    	
    }
    
    // 똑같은지 체크
    static boolean check(int[][] map, int[][] target) {
    	
    	int rSize = map.length;
    	int cSize = map[0].length;
    	
    	for(int i=0; i<rSize; i++) {
    		for(int j=0; j<cSize; j++) {
    		
    			if(map[i][j] != target[i][j]) {
    				return false;
    			}
    			
    		}
    	}
    	
    	return true;
    }

}
