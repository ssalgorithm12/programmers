import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

class Solution {
    
    // 노드
    class Node{
        int dist;
        ArrayList<Way> link;
        
        public Node(int dist){
            this.dist = dist;
            link = new ArrayList<>();
        }
    }
    
    // 간선
    class Way{
        int target;
        int weight;
        
        public Way(int target, int weight){
            this.target = target;
            this.weight = weight;
        }
    }
    
    static Node[] N;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        
        int size = fares.length;
        
        N = new Node[n + 1];
        
        // 각 노드의 거리 값 최대로
        for(int i=0; i<n+1; i++){
            N[i] = new Node(Integer.MAX_VALUE - 1);
        }
        
        // 각 노드에 간선 추가
        for(int i=0; i<size; i++){
            int x = fares[i][0];
            int y = fares[i][1];
            int w = fares[i][2];
            
            N[x].link.add(new Way(y, w));
            N[y].link.add(new Way(x, w));
        }
        
        Daijkstra(s, a, b, fares);
        
        int answer = 0;
        return answer;
    }
    
    public void Daijkstra(int s, int a, int b, int[][] fares){
        
        Queue<int[]> container = new LinkedList<>();
        
        container.offer(new int[]{s, s});
        
        while(!container.isEmpty()){
            
            int[] cur = container.poll();
            
            int A = cur[0];
            int B = cur[1];
            
            
        }
        
    }
    
}