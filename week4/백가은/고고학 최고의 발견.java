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
        
        backtracking(0, 0, 0);
        return min;
    }
    
    static void backtracking(int x, int y, int cnt) {
        
        if(cnt >= min) return;                      // 가지치기
        
        if(y == n) backtracking(x + 1, 0, cnt);     // 다음 행으로 넘어가기
        else if(x == n) {                           // 종료 조건
            if(isValid()) min = cnt;                // 모두 12시가 된 경우 최소값 갱신
            return;
        }
        
        else if(map[x][y] == 0) backtracking(x, y + 1, cnt);    // 이미 12시인 경우 넘어가기
        else {                                                  // 12시가 아닌 경우 회전
            
            int[][] copy = new int[n][n];                       // 원래 상태 복사
            for(int i = x - 1; i <= x + 1; i++) {
                for(int j = y - 1; j <= y + 1; j++) {
                    if(!check(i, j)) continue;
                    copy[i][j] = map[i][j];
                }
            }
            
            // 회전 횟수
            for(int i = 1; i <= 3; i++) {
                
                map[x][y] = (map[x][y] + i) % 4;
                
                for(int j = 0; j < 4; j++) {    
                    int nx = x + dx[j];
                    int ny = y + dy[j];
                    
                    if(!check(nx, ny)) continue;
                    map[nx][ny] = (map[nx][ny] + i) % 4;
                }
                
                backtracking(x, y + 1, cnt + i);
                
                for(int k = x - 1; k <= x + 1; k++) {           // 상태 복구
                    for(int l = y - 1; l <= y + 1; l++) {
                        if(!check(k, l)) continue;
                        map[k][l] = copy[k][l];
                    }
                }
                
                if(map[x][y] == 0) break;
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