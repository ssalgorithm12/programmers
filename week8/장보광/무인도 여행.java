import java.util.*;

class Solution {
    int[] r = {1, -1, 0, 0};
    int[] c = {0, 0, 1, -1};
    int x_l;
    int y_l; 
    int[][] map;
    boolean[][] visited; 
    
    public int[] solution(String[] maps) {
        List<Integer> arr = new ArrayList<>();
        x_l = maps.length;
        y_l = maps[0].length(); 
    
        map = new int[x_l][y_l];
        visited = new boolean[x_l][y_l];
        
        // int 배열로 변환 
        for(int i = 0; i < x_l; ++i){
            char[] temp = maps[i].toCharArray();
            for(int j = 0; j < y_l; ++j){
                if(temp[j] == 'X')
                    map[i][j] = -1;
                else 
                    map[i][j] = temp[j] - '0';
            }
        }
        
        // 배열의 모든 요소를 탐색 
        for(int i = 0; i < x_l; ++i){
            for(int j = 0; j < y_l; j++){
                if(map[i][j] > 0 && !visited[i][j]){
                    arr.add(bfs(i,j));
                }
            }
        }
        
        if(arr.size() < 1)
            return new int[] {-1}; 
        else{
            int[] answer = new int[arr.size()];
            for(int i = 0; i < arr.size(); i++)
                answer[i] = arr.get(i);
            Arrays.sort(answer);
            return answer;  
        }
    }
    
    int bfs(int x, int y){
        /*
            -기능: bfs 탐색 함수 
            - 입력 
                x: x 좌표 
                y: y 좌표 
            - 출력: int, 연결된 섬의 일수 합  
        */
        
        Queue<int[]> q = new LinkedList<>();
        visited[x][y] = true; 
        q.add(new int[] {x, y}); 
        int sum = map[x][y]; 
        while(!q.isEmpty()){
            int[] now = q.poll();
            for(int i = 0; i < 4; i++){
                int xr = now[0] + r[i];
                int yc = now[1] + c[i];
                if(check(xr, yc) && map[xr][yc] > 0 && !visited[xr][yc]){
                    visited[xr][yc] = true;
                    sum += map[xr][yc];
                    q.add(new int[] {xr, yc});
                }
            }
        }
        return sum; 
    }
    
    boolean check(int x, int y){
        /*
            - 기능: 입력된 x, y 값이 배열의 인덱스로 유효한지 확인
            - 입력
                x: x좌표 
                y: y좌표 
            - 출력: boolean, 가능, 불가능 여부 출력 
        */
        if(x > -1 && x < x_l && y > -1 && y < y_l)
            return true;
        else 
            return false; 
    }
}