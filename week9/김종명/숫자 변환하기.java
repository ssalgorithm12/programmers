import java.util.Queue;
import java.util.LinkedList;

class Solution {
    public int solution(int x, int y, int n) {
        // BFS로 모든 경우 탐색
        return BFS(x, y, n);
    }
    
    // BFS로 y부터 시작해서 x를 찾아내기
    // 가지치기 하기 더 쉬움
    public int BFS(int x, int y, int n){
        
        boolean[] visited = new boolean[y + 1];
        Queue<Integer> container = new LinkedList<>();

        container.offer(y);
        visited[y] = true;
        int count = 0;
        
        while(!container.isEmpty()){
            
            int size = container.size();
            
            for(int i=0; i<size; i++){
                
                int cur = container.poll();
                
                if(cur == x){
                    return count;
                }
                // x 보다 작아지면 가지치기
                if(cur - n >= x && !visited[cur - n]){
                    container.offer(cur - n);
                    visited[cur - n] = true;
                }
                // 2로 안나누어 떨어지면 가지치기
                if(cur % 2 == 0 && cur / 2 >= x && !visited[cur / 2]){
                    container.offer(cur / 2);
                    visited[cur / 2] = true;
                }
                // 3으로 안나누어 떨어지면 가지치기
                if(cur % 3 == 0 && cur / 3 >= x && !visited[cur / 3]){
                    container.offer(cur / 3);
                    visited[cur/ 3] = true;
                }
                
            }
            
            count += 1;
        }
        
        
        return -1;
    }
}