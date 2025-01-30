import java.util.*;

class Solution {
    static int n;
    static long[] val;
    static List<Integer>[] adj;
    public long solution(int[] a, int[][] edges) {
        n = a.length;
        val = new long[n];
        
        long sum = 0;
        boolean flag = true;
        for(int i = 0; i < n; i++) {
            int tmp = a[i];
            
            val[i] = tmp;
            sum += tmp;
            if(tmp != 0 && flag) flag = false;
        }
        
        // 모든 가중치가 0인 경우, 불가능한 경우 처리
        if(sum != 0) return -1;
        if(flag) return 0;
        
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
        
        return bfs();
    }
    
    // 리프노드부터 시작해서 횟수 더하기
    static long bfs() {
        Queue<Integer> q = new LinkedList<>();
        // 리프노드를 큐에 추가하기
        for(int i = 0; i < n; i++) {
            if(adj[i].size() == 1) q.offer(i);
        }
        
        boolean[] visited = new boolean[n];
        long ans = 0;
        
        while(!q.isEmpty()) {
            int cur = q.poll();
            if(visited[cur]) continue;
            
            visited[cur] = true;
            ans += Math.abs(val[cur]);
            
            for(int a : adj[cur]) {
                if(visited[a]) continue;
                q.offer(a);
                val[a] += val[cur];
            }
        }
        return ans;
    }
}