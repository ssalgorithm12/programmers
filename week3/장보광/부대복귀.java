import java.util.*;

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int count = sources.length;
        int[] answer = new int[count];
        int[] dist = new int[n+1];
        Arrays.fill(dist, -1);
        
        List<Integer>[] graph = new ArrayList[n+1];
        
        for(int i = 0; i <= n; i++)
            graph[i] = new ArrayList<>();
        
        for(int[] road: roads){
            graph[road[0]].add(road[1]);
            graph[road[1]].add(road[0]);
        }
        
        Queue<Integer> q = new LinkedList<>();
        q.add(destination);
        dist[destination] = 0;
        
        while(!q.isEmpty()){
            int now = q.poll();
            System.out.println(now);
            for(int x: graph[now]){
                if(dist[x]==-1 || (dist[x]!=-1 && dist[x] < dist[now] + 1)){
                    dist[x] = dist[now] + 1;
                    q.add(x);
                }
            }    
        }
        
        for(int i = 0; i < count; i++)
            answer[i] = dist[sources[i]];
        
        return answer;
    }
}
