import java.util.*;

// 플로이드-워셜
class Solution {
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int INF = Integer.MAX_VALUE;
        int[][] cost = new int[n + 1][n + 1];
        for(int i = 1; i <= n; i++) {
            Arrays.fill(cost[i], INF);
            cost[i][i] = 0;
        }
        
        for(int i = 0; i < fares.length; i++) {
            int v1 = fares[i][0];
            int v2 = fares[i][1];
            int c = fares[i][2];
            cost[v1][v2] = c;
            cost[v2][v1] = c;
        }
        
        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    if(cost[i][k] == INF || cost[k][j] == INF) continue;
                    cost[i][j] = Math.min(cost[i][j], cost[i][k] + cost[k][j]);
                }
            }
        }
        
        int min = Integer.MAX_VALUE;
        for(int i = 1; i <= n; i++) {
            if(cost[s][i] == INF || cost[i][a] == INF || cost[i][b] == INF) continue;
            min = Math.min(min, cost[s][i] + cost[i][a] + cost[i][b]);
        }
        
        return min;
    }
}