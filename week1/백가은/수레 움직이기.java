import java.util.*;

class Solution {
    
    // 빨간 수레와 파란 수레를 각각 백트레킹하기 위해 변수 분리하여 전역으로 선언
    static int h, w, min, depthBlue;
    static int[][] map, move;
    static int[] locRed, locBlue;
    static boolean[][] visitedRed, visitedBlue;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};
    
    public int solution(int[][] maze) {
        h = maze.length;
        w = maze[0].length;
        map = maze;
        move = new int[h][w];
        min = Integer.MAX_VALUE;
        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                if(map[i][j] == 1) locRed = new int[]{i, j};
                if(map[i][j] == 2) locBlue = new int[]{i, j};
            }
        }
        visitedRed = new boolean[h][w];
        visitedRed[locRed[0]][locRed[1]] = true;
        moveRed(locRed, 0);                 // 빨간 수레 움직이기
        
        if(min == Integer.MAX_VALUE) return 0;
        return min;
    }
    
    // 빨간 수레가 갈 수 있는 경로 백트래킹
    // 빨간 수레가 몇 턴에 어디를 이동했는지 저장
    // 빨간 수레가 도착지에 도달하면 파란 수레 백트래킹 시작
    static void moveRed(int[] loc, int depth) {
        if(depth >= min) return;       // 가지치기
        if(map[loc[0]][loc[1]] == 3) {
            depthBlue = Integer.MAX_VALUE;
            visitedBlue = new boolean[h][w];
            visitedBlue[locBlue[0]][locBlue[1]] = true;
                        
            moveBlue(locBlue, 0);    // 파란 수레 백트래킹 시작
            
            // 파란 수레가 도착지에 도달할 수 있는 경우 경로 최소값 갱신
            if(depthBlue != 0) min = Math.min(min, Math.max(depth, depthBlue));
            return;
        }
        
        // 사방탐색하며 백트래킹
        for(int i = 0; i < 4; i++) {
            int nx = loc[0] + dx[i];
            int ny = loc[1] + dy[i];
            
            if(!check(nx, ny) || visitedRed[nx][ny] || map[nx][ny] == 5) continue;
            visitedRed[nx][ny] = true;
            move[nx][ny] = depth + 1;            // 빨간 수레가 몇 턴에 어디로 이동했는지 저장 
            moveRed(new int[]{nx, ny}, depth + 1);
            move[nx][ny] = 0;
            visitedRed[nx][ny] = false;
        }
    }
    
    // 빨간 수레의 이동 경로에 따른 파란 수레가 갈 수 있는 경우 백트래킹
    static void moveBlue(int[] loc, int depth) {
        if(depth >= depthBlue) return;
        if(map[loc[0]][loc[1]] == 4) {
            if(move[loc[0]][loc[1]] < depth) depthBlue = Math.min(depthBlue, depth);
            return;
        }
        
        for(int i = 0; i < 4; i++) {
            int nx = loc[0] + dx[i];
            int ny = loc[1] + dy[i];
            
            if(!check(nx, ny) || visitedBlue[nx][ny] || map[nx][ny] == 5) continue;
            int nextTurn = move[nx][ny];  // 파란 수레가 가려는 곳을 빨간 수레가 언제 갔는지 저장하는 변수
            // 동시에 같은 곳을 이동하는 경우, 빨간 수레가 먼저 도착해있는 곳인 경우 continue
            if(nextTurn == depth + 1 || (nextTurn < depth + 1 && map[nx][ny] == 3)) continue;
            // 서로 자리 바꾸는 경우 continue
            if(move[loc[0]][loc[1]] == depth + 1 && nextTurn == depth) continue;
            visitedBlue[nx][ny] = true;
            moveBlue(new int[]{nx, ny}, depth + 1);
            visitedBlue[nx][ny] = false;
        }
    }
    
    static boolean check(int x, int y) {
        if(x < 0 || x >= h || y < 0 || y >= w) return false;
        return true;
    }
}