import java.util.*;

class Solution {
    int answer = 0;
    Deque<Integer> dq = new LinkedList<>();
    List<Integer>[] arr;
    boolean[] visited;
    
    public int solution(int[] info, int[][] edges) {
        int length = info.length;
        arr = new ArrayList[length];
        visited = new boolean[length];
        
        for(int i = 0; i < length; i++)
            arr[i] = new ArrayList<>();
        
        for(int[] edge: edges){
            arr[edge[0]].add(edge[1]);
            arr[edge[1]].add(edge[0]);
        }
        
        if(info[0] == 1) return 0;
        for(int x: arr[0]) dq.add(x);
        visited[0] = true; 
        
        dfs(1, 0, info);
        return answer;
    }
    
    void dfs(int sheep, int wolf, int[] info){
        if(sheep > answer)
            answer = sheep;

        for(int i = 0; i < dq.size(); i++){
            int x = dq.pollFirst();
            int s = info[x]^1; 
            int w = info[x];
            if(!visited[x] && sheep + s > wolf + w){
                visited[x] = true;
                int count = 0;
                for(int y: arr[x]){
                    if(!visited[y]){
                        dq.addLast(y);
                        count++; 
                    }
                }
                dfs(sheep + s, wolf + w, info);
                for(int j = 0; j < count; j++)
                    dq.removeLast();
                visited[x] = false;   
            }
            dq.addLast(x);
        }   
    }
}
