import java.util.*;

// 길과 가장 많이 연관된 등대 고르기
// 연관 수 최대값이 여러 개인 경우 백트래킹
// 등대 고른 후 해당 등대가 밝히는 길과 연관된 등대의 연관 수 줄이기
class Solution {
    static int n, min;
    static int[][] lighthouse;
    static List<Integer>[] adj;
    static boolean[] visitedLight, visitedRoad;
    
    public int solution(int n, int[][] lighthouse) {
        this.n = n;
        this.lighthouse = lighthouse;
        
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
                            
        
        visitedRoad = new boolean[n];
        visitedLight = new boolean[n + 1];
        min = Integer.MAX_VALUE;
        
        for(int i = 1; i <= n; i++) {
            if(adj[i].size() == max) {
                
                visitedLight[i] = true;
                for(int road : adj[i]) {
                    visitedRoad[road] = true;
                    int tmp = lighthouse[road][0] == i ? lighthouse[road][1] : lighthouse[road][0];
                    adj[tmp].remove((Integer)road);
                }
                
                backtracking(1, n - 1 - adj[i].size());
                
                visitedLight[i] = false;
                for(int road : adj[i]) {
                    visitedRoad[road] = false;
                    int tmp = lighthouse[road][0] == i ? lighthouse[road][1] : lighthouse[road][0];
                    adj[tmp].add(road);
                }
            }
        }
        
        return min;
    }
    
    // node : 새로 밝힐 수 있는 길이 가장 많은 등대 번호
    // cnt : 켜 두어야 하는 등대 개수
    // left : 밝혀지지 않은 길 개수
    static void backtracking(int cnt, int left){
        
        if(cnt >= min) return;
        if(left == 0) {
            min = cnt;
            return;
        }
        
        // 밝힐 수 있는 길 개수의 최대값
        int max = 0;
        int nodeLeft = 0;
        for(int i = 1; i <= n; i++) {
            if(!visitedLight[i]) {
                nodeLeft++;
                max = Math.max(max, adj[i].size());
            }
        }
        
        for(int i = 1; i <= n; i++) {
            // 최대값인거 선택해서 백트래킹 시작
            if(!visitedLight[i] && adj[i].size() == max) {
                // 최대값인 등대 중 방문 안 한 등대 방문처리
                visitedLight[i] = true;
                // 백트래킹하기 위해 리스트 복사
                List<Integer> copy = new ArrayList<>();
                // 새로 밝히는 길을 다른 등대의 길 리스트에서 제외
                for(int road : adj[i]) {
                    if(visitedRoad[road]) continue;
                    visitedRoad[road] = true;
                    copy.add(road);
                    int tmp = lighthouse[road][0] == i ? lighthouse[road][1] : lighthouse[road][0];
                    adj[tmp].remove((Integer)road);
                }


                backtracking(cnt + 1, left - adj[i].size());

                // 방문 처리 해제
                visitedLight[i] = false;
                // 연관 등대의 길 리스트에 다시 길 넣기
                for(int road : copy) {
                    visitedRoad[road] = false;
                    int tmp = lighthouse[road][0] == i ? lighthouse[road][1] : lighthouse[road][0];
                    adj[tmp].add(road);
                }
            }
        }
    }
}