import java.util.*;

class Solution {
    int x_l = 0; // maps의 세로 길이 
    int y_l = 0; // maps의 가로 길이 
    int[] r = {1, -1, 0, 0};
    int[] c = {0, 0, 1, -1};
    
    public int solution(String[] maps) {
        x_l = maps.length;
        y_l = maps[0].length();
        char[][] map2 = new char[x_l][y_l]; // char형으로 변환한 maps
        int[] start = new int[2]; // 시작지점 
        
        // map 초기화 
        for(int i = 0; i < x_l; i++){
            map2[i] = maps[i].toCharArray();
            for(int j = 0; j < y_l; j++){
                if(map2[i][j] == 'S') 
                    start = new int[] {i, j};
            }
        }
        
        // 결과 탐색 및 출력  
        int[] road1 = bfs(start[0],start[1], map2, 'L');
        int[] road2 = bfs(road1[1], road1[2], map2, 'E');
        if(road1[0] != 0 && road2[0] != 0)
            return road1[0] + road2[0];
        else 
            return -1; 
    }
    
    int[] bfs(int x, int y, char[][] map, char dest){
        /*
            - 기능: 주어진 시작점과 도착점을 바탕으로 bfs를 실행하는 함수 
            - 입력 
                x, y: 시작점의 위치 
                map: bfs 탐색할 배열
                dest: 도착지점, 해당 문자를 가진 지점에 도달하면 bfs 종료 
            - 출력: int 배열, 순서대로 이동 거리, x좌표, y좌표 의미 
        */
        
        // 변수, 자료형 선언 
        int[] road = new int[] {0, x, y};
        int[][] dist = new int[x_l][y_l];
        Queue<int[]> q = new LinkedList<>();
        
        // 초기화 
        q.add(new int[] {x, y});
        for(int i = 0; i < x_l; i++)
            Arrays.fill(dist[i], 10000);
        dist[x][y] = 0;
        
        // bfs
        while(!q.isEmpty()&& road[0]==0){
            int[] now = q.poll();
            
            for(int i = 0; i < 4; i++){
                int xr = now[0] + r[i];
                int yc = now[1] + c[i];
                if(xr > -1 && xr < x_l && yc > -1 && yc < y_l){
                    if(map[xr][yc]!='X' && dist[xr][yc] > dist[now[0]][now[1]]+1){
                        dist[xr][yc] = dist[now[0]][now[1]]+1;
                        q.add(new int[] {xr, yc});
                        if(map[xr][yc] == dest)
                            road = new int[] {dist[xr][yc], xr, yc};
                    }
                }
            }
        }
        
        return road;
    }
}
