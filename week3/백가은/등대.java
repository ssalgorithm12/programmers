import java.util.*;

// 길과 가장 많이 연관된 등대만 고르기
// 등대 고른 후 해당 등대가 밝히는 길과 연관된 등대의 연관 수 줄이기
class Solution {
    public int solution(int n, int[][] lighthouse) {
                
        List<Integer>[] adj = new List[n + 1];  // 각 등대가 속한 도로의 인덱스 저장 배열
        for(int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < n - 1; i++) {
            int a = lighthouse[i][0];
            int b = lighthouse[i][1];
            
            adj[a].add(i);
            adj[b].add(i);
        }
        
        int cnt = 0;
        int left = n - 1;
        int node = -1;
        boolean[] visited = new boolean[n];
        while(left > 0) {
            
            // max인 등대가 여러개인 경우 백트래킹 등 완탐해야 함
            int max = 0;
            for(int i = 1; i <= n; i++) {
                if(adj[i] != null && adj[i].size() > max) {
                    max = adj[i].size();
                    node = i;
                }
            }
            cnt++;
            left -= adj[node].size();
            
            for(int road : adj[node]) {
                if(visited[road]) continue;
                visited[road] = true;
                int tmp = lighthouse[road][0] == node ? lighthouse[road][1] : lighthouse[road][0];
                adj[tmp].remove((Integer)road);
            }
            adj[node] = null;
        }
        
        return cnt;
    }
}