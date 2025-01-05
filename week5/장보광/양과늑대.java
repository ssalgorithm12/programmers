import java.util.*;

class Solution {
    int answer = 0;
    Deque<Integer> dq = new LinkedList<>();
    List<Integer>[] arr;
    boolean[] visited;
    
    public int solution(int[] info, int[][] edges) {
        arr = new ArrayList[info.length];
        visited = new boolean[info.length];
        
        for(int i = 0; i < info.length; i++)
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

    //백 트랙킹, dfs  
    void dfs(int sheep, int wolf, int[] info){
        if(sheep > answer)
            answer = sheep;
        int num = dq.size();
        for(int i = 0; i < num; i++){
            int x = dq.pollFirst();
            if(!visited[x]){
                visited[x] = true;
                int count = 0;
                for(int y: arr[x]){
                    if(!visited[y]){
                        dq.addLast(y);
                        count++; 
                    }
                }
                int s = 0, w = 0;
                if(info[x] == 0) s = 1;
                else w = 1;
                if(sheep + s > wolf + w)
                    dfs(sheep + s, wolf + w, info);
                for(int j = 0; j < count; j++)
                    dq.removeLast();
                visited[x] = false;   
            }
            dq.addLast(x);
        }   
    }
}
