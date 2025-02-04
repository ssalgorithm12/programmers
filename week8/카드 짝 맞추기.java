import java.util.*;

// ctrl 누를 때, 누르기 않을 때까지 완탐하면 시간초과....
class Solution {
    static int x, y, target, min;
    static int[][] map;
    static boolean[][][] visited = new boolean[4][4][9];
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};
    public int solution(int[][] board, int r, int c) {
        x = r;
        y = c;
        min = Integer.MAX_VALUE;
        
        map = board;
        target = 0;
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                if(map[i][j] != 0) target++;
            }
        }
        target /= 2;
        
        backTracking(r, c, 0, 0, 0);
        return min;
    }
    
    // 뒤집기 여부, 네 방향 중 어디로 갈지에 따른 백트래킹
    // 파라미터 : (현재 위치의 x좌표, 현재 위치의 y좌표, 현재 뒤집힌 카드에 적힌 숫자, 조작 횟수, 제거된 카드 쌍)
    static void backTracking(int x, int y, int cur, int cnt, int num) {
        
        if(cnt >= min) return;
        int card = map[x][y];
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(!check(nx, ny)) continue;
            
            while(map[nx][ny] == 0) {
                if(!check(nx + dx[i], ny + dy[i])) break;
                nx += dx[i];
                ny += dy[i];
            }
            // 뒤집기
            if(card != 0) {
                
                if(card == cur && num + 1 == target) {
                    min = cnt + 1;
                    return;
                }
                
                map[x][y] = 0;
                if(cur == 0 && !visited[nx][ny][num]) {
                    visited[nx][ny][num] = true;
                
                    backTracking(nx, ny, card, cnt + 2, num);

                    visited[nx][ny][num] = false;
                    
                } else if(card == cur && !visited[nx][ny][num + 1]) {
                    visited[nx][ny][num + 1] = true;
                
                    backTracking(nx, ny, 0, cnt + 2, num + 1);

                    visited[nx][ny][num + 1] = false;
                }
                map[x][y] = card;
            }
            
            if(card != 0 && card == cur) continue;
            // 뒤집지 않고 넘어가기
            if(!visited[nx][ny][num]) {
                visited[nx][ny][num] = true;
                backTracking(nx, ny, cur, cnt + 1, num);
                visited[nx][ny][num] = false;
            }
            
        }
    }
    
    static boolean check(int x, int y) {
        if(x < 0 || x >= 4 || y < 0 || y >= 4) return false;
        return true;
    }
}