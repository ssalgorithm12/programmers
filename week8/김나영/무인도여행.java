import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0 ,1};
    static int n, m;
    static char [][] map;
    static List<Integer> list;
    public int[] solution(String[] maps) throws IOException {
        map = new char[maps.length][];
        list = new ArrayList<Integer>();

        for (int i = 0; i < maps.length; i++) {
            map[i] = maps[i].toCharArray();
        }

        n = map.length;
        m = map[0].length;

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (map[r][c] != 'X') {
                    list.add(bfs(r, c));
                }
            }
        }

        int[] answer;
        if (list.size() == 0) {
            answer = new int[] {-1};
        } else {
            answer = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                answer[i] = list.get(i);
            }
        }

        Arrays.sort(answer);
        return answer;
    }

    public int bfs(int r, int c) {
        Queue<int[]> q = new LinkedList<int[]>();
        int cnt = 0;
        cnt += map[r][c] - '0';
        map[r][c] = 'X';
        System.out.println(cnt);

        q.offer(new int[] {r, c});

        while(!q.isEmpty()) {
            int[] p = q.poll();
            for (int i = 0; i < 4; i++) {
                int nr = p[0] + dr[i];
                int nc = p[1] + dc[i];

                if (check(nr, nc) || map[nr][nc]=='X') continue;

                cnt += map[nr][nc] - '0';
                map[nr][nc] = 'X';
                q.offer(new int[] {nr, nc});
            }
        }
        return cnt;
    }

    public boolean check(int r, int c) {
        return r < 0 || r >= n || c < 0 || c >= m;
    }
}