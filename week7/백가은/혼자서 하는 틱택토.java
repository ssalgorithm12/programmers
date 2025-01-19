import java.util.*;
/*
피드백
[보광]
- 이 게임이 끝났을 때 체크해야 되는 조건은 4개 입니다. 
- 1. 둘 다 이겼는지 여부  
- 2. O가 이겼을 때 O, X의 개수 
- 3. X가 이겼을 때 O, X의 개수 
- 4. X와 O의 개수 총합 비교
- 이거 보고 어느 조건을 빼먹었는지 확인해보세요. 
*/
//왜 틀렸는지 찾아주세요..
class Solution {
    
    static int h, w;
    static int[][] map;
    static int[] dx = {0, 1, 1};
    static int[] dy = {1, 0, 1};
    static boolean[][] visited;
    static boolean res;
    
    public int solution(String[] board) {
        
        h = board.length;
        w = board[0].length();
        map = new int[h][w];
        
        int cntF = 0;
        int cntS = 0;
        
        for(int i = 0; i < h; i++) {
            String s = board[i];
            for(int j = 0; j < w; j++) {
                char c = s.charAt(j);
                
                if(c == 'O') {
                    cntF++;
                    map[i][j] = 1;
                }
                else if(c == 'X') {
                    cntS++;
                    map[i][j] = 2;
                }
            }
        }
        
        // 잘못 표시한 경우
        if(cntS < cntF - 1 || cntS > cntF) return 0;
        // 게임이 종료되었음에도 계속 진행한 경우
        // if(isValid(1) && isValid(2)) return 0;
        if(isValid(1) && (cntS != cntF - 1)) return 0;
        if(isValid(2) && (cntF != cntS)) return 0;
        return 1;
    }
    
    static boolean isValid(int target) {
        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                
                if(map[i][j] == target) {
                    visited = new boolean[h][w];
                    
                    for(int k = 0; k < 3; k++) {
                        res = false;
                        visited[i][j] = true;
                        possible(i, j, target, 1, k);
                        if(res) return true;
                    }
                }
            }
        }
        return false;
    }
    
    static void possible(int x, int y, int target, int cnt, int dir) {
        if(res) return;
        if(cnt == 3) {
            res = true;
            return;
        }
        
        int nx = x + dx[dir];
        int ny = y + dy[dir];

        if(check(nx, ny) && map[nx][ny] == target && !visited[nx][ny]) {
            visited[nx][ny] = true;
            possible(nx, ny, target, cnt + 1, dir);
        }
    }
    
    static boolean check(int x, int y) {
        if(x < 0 || x >= h || y < 0 || y >= w) return false;
        return true;
    }
}
