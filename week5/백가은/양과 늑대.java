import java.util.*;

class Solution {
    
    static int max;
    static Node[] tree;
    static boolean[] visitedByNode;
    static boolean[][][] visitedByCnt;
    static class Node{
        int n, parent, left, right;
        Node(int n){
            this.n = n;
        }
    }
    
    public int solution(int[] info, int[][] edges) {
        
        int len = info.length;                          // 노드 개수
        tree = new Node[len];                           // 트리 배열
        
        for(int i = 0; i < len; i++) {                  // 각 노드의 값(양/늑대) 저장
            tree[i] = new Node(info[i]);
        }
        
        tree[0].parent = -1;                            // 루트 노드의 부모값 초기화
        for(int i = 0; i < len - 1; i++) {              // 이진트리 만들기
            int p = edges[i][0];
            int c = edges[i][1];
            
            tree[c].parent = p;                         // 부모 노드 번호 저장
            if(tree[p].left == 0) tree[p].left = c;     // 자식 노드 번호 저장
            else tree[p].right = c;
        }
        
        visitedByNode = new boolean[len];                       // 노드 방문처리 배열(개수 갱신 여부 위해)
        visitedByCnt = new boolean[len][len + 1][len + 1];      // 양/늑대 개수에 따른 방문처리 배열(중복 탐색 방지 위해)

        visitedByNode[0] = true;                                // 루트노드(0번) 방문처리
        visitedByCnt[0][1][0] = true;
        backtracking(0, 1, 0);                                  // 백트래킹

        return max;
    }
    
    // 자식, 부모 방문하며 완탐
    static void backtracking(int node, int sheep, int wolf) {
                
        if(wolf >= sheep) return;               // 늑대에게 잡아먹히면 return
        max = Math.max(max, sheep);             // 양 최대값 갱신

        int[] childs = new int[2];
        childs[0] = tree[node].left;            // 왼쪽 자식 노드
        childs[1] = tree[node].right;           // 오른쪽 자식 노드

        for(int i = 0; i < 2; i++) {

            if(childs[i] == 0) continue;        // 자식 없으면 continue
            int c = childs[i];                  // 자식 노드의 번호
            int val = tree[c].n;                // 자식 노드의 값

            int ns = sheep;                     // 노드 방문 후 양/늑대 개수수
            int nw = wolf;
            boolean have = visitedByNode[c];    // 이미 방문한 적 있는지 여부
            if(!have) {                         // 이미 방문한 적 있다면 값 갱신하지 않음
                visitedByNode[c] = true;
                if(val == 0) ns  += 1;
                else nw += 1;
            }
            
            if(!visitedByCnt[c][ns][nw]) {
                visitedByCnt[c][ns][nw] = true;         // 방문처리

                backtracking(c, ns, nw);                // DFS 호출

                visitedByCnt[c][ns][nw] = false;        // 방문 해제
                if(!have) visitedByNode[c] = false;
            }
        }
        
        // 부모
        if(tree[node].parent != -1) {                        // 부모 노드가 있는 경우
            int p = tree[node].parent;                       // 부모 노드의 번호
            
            if(!visitedByCnt[p][sheep][wolf]) {
                visitedByCnt[p][sheep][wolf] = true;         // 방문처리

                backtracking(p, sheep, wolf);                // DFS 호출

                visitedByCnt[p][sheep][wolf] = false;        // 방문 해제
            } 
        }
        
    }
}

// 장보광: 중복되는 코드가 많아서 함수로 따로 빼거나 줄일 방법을 생각해보는 게 좋을 듯
// -> 자식 값을 배열에 넣어 반복문으로 중복 제거
