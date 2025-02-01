import java.util.*;

class Solution {
    static int n;
    static long[] val;
    static List<Integer>[] adj;
    static int[] parent;
    
    public long solution(int[] a, int[][] edges) {
        n = a.length;
        val = new long[n];
        parent = new int[n];
        
        long sum = 0;
        for(int i = 0; i < n; i++) {
            int tmp = a[i];
            val[i] = tmp;
            sum += tmp;
        }
        
        // 모든 가중치가 0인 경우, 불가능한 경우 처리
        if(sum != 0) return -1;
        
        adj = new List[n];
        for(int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        // 인접리스트 생성
        for(int i = 0; i < n - 1; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            
            adj[u].add(v);
            adj[v].add(u);
        }
        
        for(int i = 0; i < n; i++) {
            parent[i] = adj[i].size();
        }
        
        return bfs();
    }
    
    static long bfs() {
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < n; i++) {
            if(parent[i] == 1) q.offer(i);
        }
        
        boolean[] visited = new boolean[n];
        long ans = 0;
        
        while(!q.isEmpty()) {
            int cur = q.poll();
            if(visited[cur]) continue;
            
            visited[cur] = true;
            ans += Math.abs(val[cur]);
            parent[cur]--;
            
            
            for(int a : adj[cur]) {
                if(visited[a]) continue;
                val[a] += val[cur];
                if(--parent[a] == 1) q.offer(a);
            }
        }
        return ans;
    }
}