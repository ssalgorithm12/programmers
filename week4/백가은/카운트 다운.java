import java.util.*;

// 그리디하게 가지치기?
class Solution {    
    static int target;
    static class Dart{
        int score, cnt, sum;
        Dart(int score, int cnt, int sum) {
            this.score = score;
            this.cnt = cnt;
            this.sum = sum;
        }
    }
    
    public int[] solution(int target) {
        this.target = target;
        return bfs();
    }
    
    static int[] bfs() {
        Queue<Dart> q = new LinkedList<>();
        q.offer(new Dart(0, 0, 0));
        
        boolean[][][] visited = new boolean[target + 1][target + 1][target * 2 + 1];
        visited[0][0][0] = true;
        
        int min = Integer.MAX_VALUE;
        int max = 0;
        
        while(!q.isEmpty()) {
            Dart cur = q.poll();
            
            if(cur.cnt > min) break;
            if(cur.score ==  target && cur.sum > max) max = cur.sum;
            if(cur.cnt == min) continue;
            
            int gap = target - cur.score;
            
            if(!(cur.cnt + 1 >= min && cur.score + 50 != target) && gap >= 50 && !visited[cur.score + 50][cur.cnt + 1][cur.sum + 1]) {
                visited[cur.score + 50][cur.cnt + 1][cur.sum + 1] = true;
                if(cur.score + 50 == target) min = cur.cnt + 1;
                q.offer(new Dart(cur.score + 50, cur.cnt + 1, cur.sum + 1));
            }
            
            for(int i = 1; i <= 3; i++) {
                int tmp = gap / i <= 20 ? (gap / i) * i : 20 * i;
                int nextSum = i == 1 ? cur.sum + 1 : cur.sum;
                if(!(cur.cnt + 1 >= min && cur.score + tmp != target) && !visited[cur.score + tmp][cur.cnt + 1][nextSum]) {
                    visited[cur.score + tmp][cur.cnt + 1][nextSum] = true;
                    if(cur.score + tmp == target) min = cur.cnt + 1;
                    q.offer(new Dart(cur.score + tmp, cur.cnt + 1, nextSum));
                }
            }
        }
        
        return new int[]{min, max};
    }
}