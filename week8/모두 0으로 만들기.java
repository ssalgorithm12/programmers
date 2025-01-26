import java.util.*;

// 리프노드에서 출발하여 가운데로 향하며 숫자 몰아주기
class Solution {
    static int n, ans;
    static int[] val;
    static List<Integer>[] adj;
    public long solution(int[] a, int[][] edges) {
        val = a;
        n = a.length;
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
    
    // 리프에서 시작해서 동시에 한 칸 씩 움직일 방법????
    // 부모가 동일한 노드가 있을 수 있으므로
    // 중복 방문 가능해야 함
    // bfs는 적합하지 않음
}