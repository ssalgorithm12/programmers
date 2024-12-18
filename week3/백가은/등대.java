import java.util.*;

class Solution {
    static int cnt, min, n;
    static int[][] lighthouse;
    static List<Integer>[] adj;    
    static class Light{
        int num, left, use;
        public Light(int num, int left, int use) {
            this.num = num;
            this.left = left;
            this.use = use;
        }
    }
    
    public int solution(int n, int[][] lighthouse) {
        this.n = n;
        this.lighthouse = lighthouse;
        adj = new List[n + 1];  // 각 등대가 새로 밝힐 수 있는 길 목록
        for(int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        int max = 0;
        for(int i = 0; i < n - 1; i++) {
            int a = lighthouse[i][0];
            int b = lighthouse[i][1];
            
            adj[a].add(i);
            adj[b].add(i);
        }
        
        // 그리디하게 새로 밝힐 수 있는 길 개수 많은 것부터 사용하면
        // 최소를 보장할 수 없음
        // 완탐으로 조합하기엔 너무 많음
        
    }
}