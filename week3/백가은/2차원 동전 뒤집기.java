import java.util.*;

// 백트래킹
class Solution {
    static int h, w, min;
    static int[][] map, target;
    static boolean[] visitedRow, visitedCol;
    
    public int solution(int[][] beginning, int[][] target) {
        min = Integer.MAX_VALUE;
        h = beginning.length;
        w = beginning[0].length;
        map = beginning;
        this.target = target;
        
        visitedRow = new boolean[h];
        visitedCol = new boolean[w];
        
        backtracking(new int[]{0, 0}, 0);
        
        if(min == Integer.MAX_VALUE) return -1;
        else return min;
    }
    
    static void backtracking(int[] loc, int cnt) {
        
        if(cnt >= min) return;                      // 가지치기
        if(loc[1] == w) {                           // 다음 행으로 가기
            backtracking(new int[]{loc[0] + 1, 0}, cnt);
        }
        if(loc[0] == h) {                           // 종료조건
            if(isValid()) min = cnt;
            return;
            
        }
        
        // 배열 순회하며 목표 상태와 다른 칸이 있으면 행 또는 열 뒤집기
        for(int i = loc[0]; i < h; i++) {
            for(int j = loc[1]; j < w; j++) {
                
                // 현재 칸의 상태가 목표 상태와 같은 경우 뒤집지 않고 다음 칸으로 넘어가기
                if(map[i][j] == target[i][j]) {
                    backtracking(new int[]{i, j + 1}, cnt);
                    
                // 현재 칸의 상태가 목표 상태와 다른 경우
                } else {
                    
                    // 현재 행을 뒤집은 적이 없는 경우 현재 행 뒤집기
                    if(!visitedRow[i]) {
                        visitedRow[i] = true;
                        
                        // 뒤집기 전 행 복사
                        int[] copyRow = Arrays.copyOf(map[i], w);
                        
                        // 뒤집기
                        change(0, i);
                        backtracking(new int[]{i, j + 1}, cnt + 1);

                        // 상태 돌려놓기
                        visitedRow[i] = false;
                        map[i] = copyRow;
                    } 

                    // 현재 열을 뒤집은 적이 없는 경우 현재 열 뒤집기
                    if(!visitedCol[j]) {
                        visitedCol[j] = true;
                        
                        // 뒤집기 전 열 복사
                        int[] copyCol = new int[h];
                        for(int k = 0; k < h; k++) {
                            copyCol[k] = map[k][j];
                        }
                        
                        // 현재 열 뒤집기
                        change(1, j);
                        backtracking(new int[]{i, j + 1}, cnt + 1);

                        // 상태 돌려놓기
                        visitedCol[j] = false;
                        for(int k = 0; k < h; k++) {
                            map[k][j] = copyCol[k];
                        }
                    }
                }
                
            }
        }
    }
    
    static void change(int dir, int n) {
        if(dir == 0) {
            for(int i = 0; i < w; i++) {
                map[n][i] = 1 - map[n][i];
            }
            
        } else {
            for(int i = 0; i < h; i++) {
                map[i][n] = 1 - map[i][n];
            }
        }
    }
    
    static boolean isValid(){
        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                if(map[i][j] != target[i][j]) return false;
            }
        }
        return true;
    }
    
}