import java.util.*;

class Solution {
    static int n, min;
    static int[][] map;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};
    
    public int solution(int[][] map) {
        this.map = map;
        n = map.length;
        min = Integer.MAX_VALUE;
        
        backtracking(new int[]{0, 0}, 0);
        return min;
    }
    
    static void backtracking(int[] loc, int cnt) {
        if(cnt >= min) return;
        if(loc[1] == n) backtracking(new int[]{loc[0] + 1, 0}, cnt);
        if(loc[0] == n) {
            if(isValid()) min = cnt;
            return;
        }
        
        for(int i = loc[0]; i < n; i++) {
            for(int j = loc[1]; j < n; j++) {
                
                if(map[i][j] == 0) backtracking(new int[]{i, j + 1}, cnt);
                
                int[][] copy = new int[n][n];
                for(int k = 0; k < n; k++) {
                    copy[k] = Arrays.copyOf(map[k], n);
                }
                
                map[i][j] = (map[i][j] + 1) % 4;
                for(int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    
                    if(!check(nx, ny)) continue;
                    map[nx][ny] = (map[nx][ny] + 1) % 4;
                }
                
                backtracking(new int[]{i, j + 1}, cnt + 1);
                
                for(int k = 0; k < n; k++) {
                    map[k] = copy[k];
                }
            }
        }
    }
    
    static boolean isValid(){
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(map[i][j] != 0) return false;
            }
        }
        
        return true;
    }
    
    static boolean check(int x, int y) {
        if(x < 0 || x >= n || y < 0 || y >= n) return false;
        return true;
    }
}