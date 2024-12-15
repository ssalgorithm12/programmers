import java.util.*;

class Solution {
    static Node root;
    static int res, idx;
    static String binaryNum;
    static class Node{
        int val;
        Node left, right;
        Node(){}
        Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    
    public int[] solution(long[] numbers) {
        int n = numbers.length;
        int[] answer = new int[n];
        for(int i = 0; i < n; i++) {
            long num = numbers[i];
            binaryNum = makeBinary(num);  // 이진수 변환
        
            // 이진수 앞에 0을 넣어서 포화 이진트리로 만들기
            int len = binaryNum.length();
            int gap = calPerfectLen(len) - len;
            String tmp = "";
            for(int j = 0; j < gap; j++) {
                tmp += "0";
            }
            binaryNum = tmp + binaryNum;
            
            root = new Node();
            makeTree(root, gap + len);
            idx = 0;
            fillTree(root);
            res = 1;
            checkTree(root);
            answer[i] = res;
        }
        
        return answer;
    }
    
    // 이진수로 변환
    static String makeBinary(long num){
        String res = "";
        int pow = 0;
        while(true) {
            if(Math.pow(2, pow + 1) > num) break;
            pow++;
        }
        while(pow >= 0) {
            if(num - Math.pow(2, pow) >= 0) {
                num -= Math.pow(2, pow);
                res += "1";
            }
            else res += "0";
            pow--;
        }
        return res;
    }
    
    // 가장 가까운 포화 이진트리의 노드수 계산
    static int calPerfectLen(int len) {
        int h = (int) Math.ceil(Math.log(len + 1) / Math.log(2)); // 트리 높이 계산
        return (int) Math.pow(2, h) - 1;                          // 포화 이진 트리 노드 수
    }
    
    // 포화 이진트리 노드수만큼 이진트리의 틀 만들기
    static void makeTree(Node root, int len) {
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        int cnt = 1;
        while(true){
            Node cur = q.poll();
            cur.left = new Node();
            cur.right = new Node();
            cnt += 2;
            if(cnt == len) return;
            q.offer(cur.left);
            q.offer(cur.right);
        }
    }
    
    // 전위순회하며 트리에 수 채우기
    static void fillTree(Node cur) {
        if(cur == null) return;
        fillTree(cur.left);
        cur.val = binaryNum.charAt(idx++) - '0';
        fillTree(cur.right);
    }
    
    // 재귀호출로 자식 탐색
    static void checkTree(Node cur) {
        
        // 자식 없는 경우 넘어가기
        if(cur.left != null){
            // 부모가 더미노드인데 자식은 더미노드가 아닌 경우 res = 0, 탐색 종료
            if(cur.val == 0 && (cur.left.val == 1 || cur.right.val == 1)) {
                res = 0;
                return;
            }
            checkTree(cur.left);
            checkTree(cur.right);
        }
    }
}