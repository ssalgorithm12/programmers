import java.util.*;

class Solution {
    static int[][] map;
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
            
        // 도형 테두리 유무 저장 배열
        // 테두리 모양이 'ㄷ'모양인 경우 제대로 따라가지 못하기때문에 2배로 늘려서 저장
        map= new int[101][101];
        for(int i=0; i<rectangle.length; i++){
            fill(rectangle[i][0] * 2, rectangle[i][1] * 2, rectangle[i][2] * 2, rectangle[i][3] * 2);
        }
        
        return bfs(characterX * 2, characterY * 2, itemX * 2, itemY * 2) / 2;
    }
    
    static void fill(int x1, int y1, int x2, int y2){
        for(int i = x1; i <= x2; i++){
            for(int j = y1; j <= y2; j++){
                if(map[i][j] == 2) continue;                    // 도형의 내부이므로 테두리가 될 수 없음
                if(i == x1 || i == x2 || j == y1 || j == y2) {  // 테두리 표시하기
                    map[i][j] = 1;                              
                } else map[i][j]=2;                             // 내부 채우기
                
                
            }
        }
    }
    
    static int bfs(int startX, int startY, int itemX, int itemY){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{startX, startY, 0});
        
        boolean[][] visited= new boolean[101][101];
        visited[startX][startY] = true;
        
        int[] dx= {0, -1, 0, 1};
        int[] dy= {-1, 0, 1, 0};
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            
            for(int i = 0; i < 4; i++){
                int nx= cur[0] + dx[i];
                int ny= cur[1] + dy[i];
                if(!check(nx, ny) || map[nx][ny] != 1 || visited[nx][ny]) continue;
                
                if(nx == itemX && ny == itemY) return cur[2] + 1;
                visited[nx][ny]= true;
                q.offer(new int[]{nx, ny, cur[2] + 1});
            }
        }
        return -1;
    }
    
    static boolean check(int x, int y){
        if(x < 0 || y < 0 || x > 100 || y > 100) return false;
        return true;
    }
}