import java.util.*;
class Solution {
    static int n, m, endX, endY, dist;
    static String answer;
    static Queue<P> pq = new PriorityQueue<>(new Comparator<P>(){
        @Override
        public int compare(P o1, P o2){
            return o1.command.compareTo(o2.command);
        }
    });
    static class P{
        int x, y, d;
        String command;
        P(int x, int y, int d, String command){
            this.x = x;
            this.y = y;
            this.d = d;
            this.command = command;
        }
    }
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        this.n = n;
        this.m = m;
        this.endX = r;
        this.endY = c;
        dist = k;

        pq.offer(new P(x, y, 0, ""));   // 시작점 우선순위큐에 추가
        answer = "impossible";                    // 초기화
        bfs();                                    // 가능한 경우 answer 갱신
        return answer;
    }
    
    static void bfs() {
        int[] dx = new int[]{1, 0, 0, -1};
        int[] dy = new int[]{0, -1, 1, 0};
        String[] dir = new String[]{"d", "l", "r", "u"};
        
        while(!pq.isEmpty()){
            P cur = pq.poll();
            
            for(int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                
                if(!check(nx, ny)) continue;
                // 가능한 경우 경로 반환환
                if(cur.d + 1 == dist) {
                    if(nx == endX && ny == endY) {
                        answer = cur.command + dir[i];
                        return;
                    }
                    continue;
                }

                // 맨해튼 거리로 가도 불가능 한 경우 가지치기
                // 가능한 경우 다른 방향향 탐색 하지 않고 break
                if(Math.abs(endX - nx) + Math.abs(endY - ny) <= dist - (cur.d + 1)) {
                    pq.offer(new P(nx, ny, cur.d + 1, cur.command + dir[i]));
                    break;
                }
            }
        }
    }
    
    static boolean check(int x, int y) {
        if(x < 1 || x > n || y < 1 || y > m) return false;
        return true;
    }
}