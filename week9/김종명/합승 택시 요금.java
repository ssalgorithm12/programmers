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
        
        return N[a].dist;
    }
    
    public void Daijkstra(int s, int a, int b, int[][] fares){
        
        Queue<int[]> container = new LinkedList<>();
        
        container.offer(new int[]{s, s});
        N[s].dist = 0;
        
        while(!container.isEmpty()){
            
            int[] cur = container.poll();
            
            int A = cur[0];
            int B = cur[1];
            
            for(int i=0; i<N[A].link.size(); i++){
                for(int j=0; j<N[B].link.size(); j++){
                 
                    int NA = N[A].link.get(i).target;
                    int NB = N[B].link.get(j).target;
                    
                    if(A == B && NA == NB){
                        // 현재 지점과 다음 지점이 같을 때
                        if(N[NA].dist > N[A].dist + N[A].link.get(i).weight){
                            N[NA].dist = N[A].dist + N[A].link.get(i).weight;
                            container.offer(new int[]{NA, NB});
                        }
                    }else{
                        // 현재 지점과 다음 지점이 다를 때
                        if(N[NA].dist > N[A].dist + N[A].link.get(i).weight + N[B].link.get(j).weight
                            && N[NB].dist > N[B].dist + N[A].link.get(i).weight + N[B].link.get(j).weight){
                            N[NA].dist = N[A].dist + N[A].link.get(i).weight + N[B].link.get(j).weight;
                            N[NB].dist = N[B].dist + N[A].link.get(i).weight + N[B].link.get(j).weight;
                            container.offer(new int[]{NA, NB});
                        }
                    }
                    
                }
            }
            
        }
        
    }
    
}











