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

        // 트리 초기화 
        for(int i = 0; i < length; i++)
            arr[i] = new ArrayList<>();
        
        for(int[] edge: edges){
            arr[edge[0]].add(edge[1]);
            arr[edge[1]].add(edge[0]);
        }

        // 루트 노드 방문 처리 
        if(info[0] == 1) return 0;
        for(int x: arr[0]) dq.add(x);
        visited[0] = true; 

        // 탐색 
        dfs(1, 0, info);
        return answer;
    }
    
    void dfs(int sheep, int wolf, int[] info){
        // 정답 갱신 
        if(sheep > answer) answer = sheep;

        for(int i = 0; i < dq.size(); i++){
            int x = dq.pollFirst();
            int s = info[x]^1; // 다음 노드가 양
            int w = info[x]; // 다음 노드가 늑대

            // 만약 x가 아직 방문 안 했고 방문하게 될 경우
            // 양과 늑대의 수가 조건에 부합한지 확인   
            if(!visited[x] && sheep + s > wolf + w){
                visited[x] = true;
                int count = 0; // 추가된 방문 가능한 노드 수 

                // 방문 가능한 노드 추가 
                for(int y: arr[x]){
                    if(!visited[y]){
                        dq.addLast(y);
                        count++; 
                    }
                }
                
                dfs(sheep + s, wolf + w, info);

                // 백트래킹 
                for(int j = 0; j < count; j++)
                    dq.removeLast();
                visited[x] = false;   
            }
            dq.addLast(x);
        }   
    }
}
