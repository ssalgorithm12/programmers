import java.util.*;

// 길과 가장 많이 연관된 등대만 고르기
// 등대 고른 후 해당 등대가 밝히는 길과 연관된 등대의 연관 수 줄이기
// 백트래킹 실패
class Solution {
    
    static int min = Integer.MAX_VALUE;
    static List<Integer>[] adj;
    static boolean[] visited;
    
    public int solution(int n, int[][] lighthouse) {
                
        adj = new List[n + 1];  // 각 등대가 속한 도로의 인덱스 저장 배열
        for(int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        int max = 0;
        for(int i = 0; i < n - 1; i++) {
            int a = lighthouse[i][0];
            int b = lighthouse[i][1];
            
            adj[a].add(i);
            adj[b].add(i);
            
            max = Math.max(max, Math.max(adj[a].size(), adj[b].size()));
        }
                            
        
        visited = new boolean[n];
        for(int i = 1; i <= n; i++) {
            if(adj[i].size() == max) {
                visited[i] = true;
                backtracking(i, 1, n - 1);
                visited[i] = false;
            }
        }
        
        return min;
    }
    
    // node : 새로 밝힐 수 있는 길이 가장 많은 등대 번호
    // cnt : 켜 두어야 하는 등대 개수
    // left : 밝혀지지 않은 길 개수
    static void backtracking(int node, int cnt, int left){
        
        if(cnt > min) return;
        if(left == 0) {
            min = cnt;
            return;
        }
        
        // 밝힐 수 있는 길 개수의 최대값
        int max = 0;
        for(int i = 1; i <= n; i++) {
            if(adj[i] != null && adj[i].size() > max) {
                max = adj[i].size();
            }
        }
        
        for(int i = 1; i <= n; i++) {
            
            // 최대값인거 선택해서 백트래킹 시작
            if(adj[i].size() == max) {
                
                // 백트래킹하기 위해 리스트 복사
                List<Integer> copy = new ArrayList<>();

                // 새로 밝히는 길을 다른 등대의 길 리스트에서 제외
                for(int road : adj[node]) {
                    tmp.add(road);
                    
                    if(visited[road]) continue;
                    visited[road] = true;
                    
                    int tmp = lighthouse[road][0] == node ? lighthouse[road][1] : lighthouse[road][0];
                    adj[tmp].remove((Integer)road);
                }

                adj[node] = null;

                visited[node] = true;
                backtracking(node, cnt + 1, left - adj[node].size());
                visited[node] = false;
                
                for(int road : copy) {
                    adj[node].add(road);
                    
                }
            }
        }
        
        
        
    }
}