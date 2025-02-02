import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Arrays;

class Solution {
    
    static class P{
        int y;
        int x;
        
        public P(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
    
    static int[][] map;
    static ArrayList<Integer> container;
    
    public int[] solution(String[] maps) {
        
        int row = maps.length;
        int col = maps[0].length();
        
        map = new int[row][col];
        
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                
                // X 값은 0 으로 저장
                if(maps[i].charAt(j) == 'X'){
                    map[i][j] = 0;
                    continue;
                }
                
                map[i][j] = maps[i].charAt(j) - '0';
            }
        }
        
        container = new ArrayList<>();
        
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                
                if(map[i][j] == 0){
                    continue;
                }
                
                BFS(i, j);
            }
        }
        
        // 무인도 섬이 없으면 -1 반환
        if(container.size() == 0){
            return new int[]{-1};
        }
        
        int[] answer = new int[container.size()];
        
        for(int i=0; i<answer.length; i++) {
        	answer[i] = container.get(i);
        }
        
        Arrays.sort(answer);
        
        return answer;
    }

    // BFS를 사용해서 무인도 섬의 머물 수 있는 기간 찾기
    static void BFS(int y, int x){
        
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};
        
        Queue<P> way = new LinkedList<>();
        
        int count = 0;
        
        way.offer(new P(y, x));
    
        count += map[y][x];
        map[y][x] = 0;
    
        while(!way.isEmpty()){
        
            P cur = way.poll();
            
            int cy = cur.y;
            int cx = cur.x;
            
            for(int d=0; d<4; d++){
                
                int ny = cy + dr[d];
                int nx = cx + dc[d];
                
                if(!check(ny, nx)){
                    continue;
                }
                
                if(map[ny][nx] == 0){
                    continue;
                }
                
                way.offer(new P(ny, nx));
                
                count += map[ny][nx];
                map[ny][nx] = 0;
            }
        }
        
        container.add(count);
    }
    
    static boolean check(int y, int x){
        int row = map.length;
        int col = map[0].length;
        return y >= 0 && x >= 0 && y < row && x < col;
    }

    
}