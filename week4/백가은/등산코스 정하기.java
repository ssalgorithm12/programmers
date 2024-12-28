import java.util.*;

// BFS + 우선순위큐
class Solution {
    
    static int[] gates, visited;
    static List<Node>[] adj;
    static class Node{
        int to, time;
        Node(int to, int time) {
            this.to = to;
            this.time = time;
        }
    }
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        this.gates = gates;
        
        visited = new int[n + 1];                    // 지점 방문처리
        adj = new List[n + 1];                       // 인접 지점 저장 배열
        for(int i = 1; i <= n; i++) {   
            adj[i] = new ArrayList<>();
        }
        for(int i = 0; i < paths.length; i++) {     // 인접 지점 저장
            int a = paths[i][0];
            int b = paths[i][1];
            int t = paths[i][2];
            
            adj[a].add(new Node(b, t));
            adj[b].add(new Node(a, t));
        }
        
        for(int i = 0; i < summits.length; i++) {   // 봉우리 저장
            visited[summits[i]] = 2;
        }
        
        return bfs();
    }
    
    static int[] bfs() {
        int ansSummit = Integer.MAX_VALUE;          // 봉우리의 최소값
        int ansIntensity = Integer.MAX_VALUE;       // 시간 최대값
        // 시간 최대값 오름차순, 봉우리 최소값 오름차순으로 정렬
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] a1, int[] a2) {
                if(a1[1] != a2[1]) return Integer.compare(a1[1], a2[1]);
                return Integer.compare(a1[0], a2[0]);
            }
        });
        for(int i = 0; i < gates.length; i++) {
            pq.offer(new int[]{gates[i], 0});
        }
        
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            if(visited[cur[0]] == 1) continue;      // 방문한 지점은 continue
            if(visited[cur[0]] == 2) {              // 봉우리에 도착했다면 정답 갱신
                if(cur[1] <= ansIntensity) {
                    ansIntensity = cur[1];
                    ansSummit = Math.min(ansSummit, cur[0]);
                }
                continue;
            }
            visited[cur[0]] = 1;                    // 지점 방문처리
            
            for(Node n : adj[cur[0]]) {             // 인접 지점 중 방문 안 한 지점 pq에 넣기
                if(visited[n.to] == 1) continue;
                pq.offer(new int[]{n.to, Math.max(cur[1], n.time)});
            }
        }
        
        return new int[]{ansSummit, ansIntensity};
    }
}