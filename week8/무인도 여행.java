import java.util.*;

// bfs -> 인접 영역의 합 구하기
// 합계 리스트에 담은 후 정렬하여 반환
class Solution {
    static int[][] map;
    static int h, w;
    static List<Integer> ansList = new ArrayList<>();
    
    public int[] solution(String[] maps) {
        h = maps.length;
        w = maps[0].length();
        map = new int[h][w];
        
        for(int i = 0; i < h; i++) {
            String s = maps[i];
            for(int j = 0; j < w; j++) {
                char c = s.charAt(j);
                if(c != 'X') map[i][j] = c - '0';
            }
        }
        
        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                if(map[i][j] > 0) bfs(i, j);
            }
        }
        
        int n = ansList.size();
        if(n == 0) return new int[]{-1};
        int[] answer = new int[n];
        for(int i = 0; i < n; i++) {
            answer[i] = ansList.get(i);
        }
        
        Arrays.sort(answer);
        return answer;
    }
    
    static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y, map[x][y]});
        
        int[] dx = {0, -1, 0, 1};
        int[] dy = {-1, 0, 1, 0};
        
        int sum = map[x][y];
        map[x][y] = 0;
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            
            for(int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                
                if(!check(nx, ny) || map[nx][ny] < 1) continue;
                q.offer(new int[]{nx, ny});
                sum += map[nx][ny];
                map[nx][ny] = 0;
            }
        }
        
        ansList.add(sum);
    }
    
    static boolean check(int x, int y) {
        if(x < 0 || x >= h || y < 0 || y >= w) return false;
        return true;
    }
}