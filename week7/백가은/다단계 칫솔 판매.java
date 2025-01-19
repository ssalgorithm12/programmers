import java.util.*;
/*
피드백
[보광]
- index 시작을 1로 두신 것 같은 데 이유가 있나요? 
- 시작이 1이라 중간 중간 -1 연산이 들어간 것 같네요.  
*/
// 배열에 부모 번호 저장 후 dfs로 이익금 나누기
class Solution {
    static Map<String, Integer> map = new HashMap<>();
    static int n;
    static int[] parent, earn;
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        n = enroll.length;
        for(int i = 1; i <= n; i++) {
            map.put(enroll[i - 1], i);
        }
        
        parent = new int[n + 1];
        for(int i = 0; i < referral.length; i++) {
            String p = referral[i];
            if(p.equals("-")) continue;
            parent[i + 1] = map.get(p);
        }
        
        earn = new int[n];
        for(int i = 0; i < seller.length; i++) {
            dfs(map.get(seller[i]), amount[i] * 100);
        }
        
        return earn;
    }
    
    static void dfs(int idx, int amount) {
        
        if(amount * 0.1 >= 1) {
            int tmp = (int)(amount * 0.1);
            if(idx >= 1) earn[idx - 1] += amount - tmp;
            dfs(parent[idx], tmp);
        } else {
            if(idx >= 1) earn[idx - 1] += amount;
        }
    }
    
    
}
