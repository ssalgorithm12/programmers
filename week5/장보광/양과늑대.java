import java.util.*;

class Solution {
    int answer = 0;
    Deque<Integer> dq = new LinkedList<>(); // 이동 가능한 노드들 저장 
    List<Integer>[] arr; // 트리 
    boolean[] visited; // 방문 처리 
    
    public int solution(int[] info, int[][] edges) {
        arr = new ArrayList[info.length];
        visited = new boolean[info.length];
        
        // 트리 초기화 
        for(int i = 0; i < info.length; i++)
            arr[i] = new ArrayList<>();
        
        for(int[] edge: edges){
            arr[edge[0]].add(edge[1]);
            arr[edge[1]].add(edge[0]);
        }
        
        if(info[0] == 1) return 0; // 첫 노드가 1이면 바로 0 반환  
        for(int x: arr[0]) dq.add(x); // 루트 노드에서 이동 가능한 경로 추가
        visited[0] = true; // 루트 노드 방문 처리
        
        dfs(1, 0, info); // dfs 
        return answer;
    }
    
    void dfs(int sheep, int wolf, int[] info){
        if(sheep > answer) answer = sheep; // 최댓값 갱신 

        // dfs 
        for(int i = 0; i < dq.size(); i++){
            int x = dq.pollFirst();
            int s = info[x]^1; // 다음 노드가 양인 경우  
            int w = info[x]&1; // 다음 노드가 늑대인 경우 
            if(!visited[x] && sheep + s > wolf + w){
                visited[x] = true;
                int count = 0; // 다음 노드의 개수 
                for(int y: arr[x]){ // 다음 노드 제거 
                    if(!visited[y]){
                        dq.addLast(y);
                        count++; 
                    }
                }
                dfs(sheep + s, wolf + w, info); // dfs 
                for(int j = 0; j < count; j++) // 추가한 다음 노드 제거 
                    dq.removeLast();
                visited[x] = false;   
            }
            dq.addLast(x);
        }   
    }
}
