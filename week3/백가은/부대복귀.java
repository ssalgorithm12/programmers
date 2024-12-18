import java.util.*;

// 도착지에서 bfs 시작하여 각 지점까지의 거리 저장
class Solution {
    static int n;
    static int[] dist;
    static List<Integer>[] adj;
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {   
        this.n = n;
        adj = new List[n + 1];
        for(int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < roads.length; i++) {
            int a = roads[i][0];
            int b = roads[i][1];
            adj[a].add(b);
            adj[b].add(a);
        }
        
        dist = new int[n + 1];
        Arrays.fill(dist, -1);
        dist[destination] = 0;
        bfs(destination);
        
        int len = sources.length;
        int[] answer = new int[len];
        
        for(int i = 0; i < len; i++) {
            int loc = sources[i];
            answer[i] = dist[loc];
        }
        
        return answer;
    }
    
    static void bfs(int start) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{start, 0});
        boolean[] visited = new boolean[n + 1];
        visited[start] = true;
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            
            for(int a : adj[cur[0]]) {
                if(visited[a]) continue;                
                visited[a] = true;
                dist[a] = cur[1] + 1;           // 각 지점까지의 거리 저장
                q.offer(new int[]{a, cur[1] + 1});
            }
        }
    }
}