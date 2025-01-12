import java.util.*;

// dfs로 보드와 테이블의 빈칸 조각들을 리스트에 담아서
// 테이블에 있는게 보드에 있으면 map에서 하나씩 빼기
// 조각을 방향의 조합으로 만들기(예: 121)
// 상:0, 우:1, 하:2, 좌:3
// 회전이 가능하므로 회전 버전으로 총 네 개를 만들어서 넣어야 하는데
// 해당하는 조각을 사용했을 때 나머지 버전도 삭제해야하는데
// 구현을 못 하겠음

/*
 * [종명]
 * - 해당 블럭에 대해서 정규화로 저장해서 푸는 방법이 있던데 그 방식을 사용하면 해결할 수 있을듯
 * - 회전 버전을 모두 저장할 필요는 없을듯
 * - 사방 탐색처럼 하나씩 체크하는 용도로만 사용하면 될듯 
 *  
 */

class Solution {
    static List<String> tmpList;
    static string res;
    static boolean[][] visited;
    static int[][] map;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};
    
    public int solution(int[][] game_board, int[][] table) {
        int h = game_board.length;
        int w = game_board[0].length;
        int total = h * w;
        
        map = game_board;
        visited = new int[h][w];
        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                if(map[i][j] == 1 || visited[i][j]) continue;
                visisted[i][j] = true;
                bfs(i, j);
            }
        }
        Map<String, Integer> board = new HashMap<>();
        for(String shape : tmpList) {
            map.put(shape, map.getOrDefault(shape, 0) + 1);
        }
        
        map = table;
        visited = new int[h][w];
        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                if(map[i][j] == 1 || visited[i][j]) continue;
                visisted[i][j] = true;
                bfs(i, j);
            }
        }
        for(String shape : tmpList) {
            if(map.get(shape) == null) continue;
            map.put(shape, map.get(shape) - 1);
            total -= shape.length();
        }
        
        return total;
    }
    
    static void bfs(int x, int y){
        Queue<>
        
        for(int = 0; i < 4; i++) {
            
        }
    }
}