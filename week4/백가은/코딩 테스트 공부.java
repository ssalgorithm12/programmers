import java.util.*;

// BFS + 우선순위큐
class Solution {
    
    static int n;
    static int[] target;
    static int[][] problems;
    
    public int solution(int alp, int cop, int[][] problems) {
        
        this.problems = problems;
        n = problems.length;
        
        // 각 능력의 최대값 갱신
        int maxA = 0;
        int maxC = 0;
        for(int i = 0; i < n; i++) {
            int a = problems[i][0];
            int c = problems[i][1];
            
            maxA = Math.max(maxA, a);
            maxC = Math.max(maxC, c);
        }
        target = new int[]{maxA, maxC};
        
        return bfs(alp, cop);
    }
    
    static int bfs(int alp, int cop) {
        // 시간 오름차순으로 우선순위큐 정렬
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] a1, int[] a2) {
                return Integer.compare(a1[2], a2[2]);
            }
        });
        pq.offer(new int[]{alp, cop, 0});
        // 알고력 + 코딩력 방문 체크 set
        Set<String> visited = new HashSet<>();
        
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            
            // 두 능력 모두 달성했으면 현재 시간 반환
            if(cur[0] >= target[0] && cur[1] >= target[1]) return cur[2];
            // 방문체크
            boolean res = visited.add(cur[0] + " " + cur[1]);
            if(!res) continue;
            
            // 문제 중 풀 수 있는 문제 있으면 풀기
            for(int i = 0; i < n; i++) {
                if(problems[i][0] <= cur[0] && problems[i][1] <= cur[1]) {
                    pq.offer(new int[]{cur[0] + problems[i][2], cur[1] + problems[i][3], cur[2] + problems[i][4]});
                }
            }
            
            // 공부하기
            if(cur[0] < target[0]) pq.offer(new int[]{cur[0] + 1, cur[1], cur[2] + 1});
            if(cur[1] < target[1]) pq.offer(new int[]{cur[0], cur[1] + 1, cur[2] + 1});
        }
        
        return -1;
    }
}