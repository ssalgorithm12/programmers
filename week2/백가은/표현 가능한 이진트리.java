import java.util.*;

// 트리에 이진수 할당한 후 부모가 더미노드인데 자식이 더미노드가 아닌 경우 판단
class Solution {
    static String binaryNum;// 이진수로 변환된 결과
    static int perfectLen, res;
    public int[] solution(long[] numbers) {
        int n = numbers.length;
        int[] answer = new int[n];
        for(int i = 0; i < n; i++) {
            long num = numbers[i];
            makeBinary(num);                  // 이진수로 변환
            makePerfectBinary();              // 포화이진트리 만들기 위해 이진수에 0 추가
            int root = (perfectLen + 1) / 2;  // 루트 노드의 인덱스
            int gap = root / 2;               // 자식과의 인덱스 차이
            res = 1;                          // 결과값
            checkTree(root, gap);             // 이분탐색으로 유효성검사
            answer[i] =  res;
        }
        
        return answer;
    }
    
    static void makeBinary(long num){
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
                
            } else res += "0";
            pow--;
        }
        binaryNum = res;
    }
    
    static void makePerfectBinary() {
        int len = binaryNum.length();
        perfectLen = calPerfectLen(len);
        int gap = perfectLen - len;
        String tmp = "";
        for(int j = 0; j < gap; j++) {
            tmp += "0";
        }
        binaryNum = tmp + binaryNum;
    }
    
    // 가장 가까운 포화 이진트리의 노드수 계산
    static int calPerfectLen(int len) {
        int h = (int) Math.ceil(Math.log(len + 1) / Math.log(2)); // 트리 높이 계산
        return (int) Math.pow(2, h) - 1;                          // 포화 이진 트리 노드 수
    }
    
    // 이분탐색으로 자신이 더미노드이면서 자식이 더미노드가 아닌 경우 res = 0으로 변경
    static void checkTree(int idx, int gap) {
        char cur = binaryNum.charAt(idx - 1);
        char left = binaryNum.charAt(idx - gap - 1);
        char right = binaryNum.charAt(idx + gap - 1); 
        if(cur == '0' && (left == '1' || right == '1')) {
            res = 0;
            return;
        }
        if(gap > 1) {
            checkTree(idx - gap, gap / 2);
            checkTree(idx + gap, gap / 2);
        }
    }
    
}