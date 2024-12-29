import java.util.*;

class Solution {
    public int[] solution(int e, int[] starts) {
        
        int len = starts.length;
        
        int[] res = new int[e + 1];
        for(int i = 1; i <= e; i++) {
            for(int j = 1; j <= e; j++) {
                if(i * j <= e) res[i * j]++;
                else break;
            }
        }
        
        int lower = Integer.MAX_VALUE;
        for(int i = 0; i < len; i++) {
            if(starts[i] < lower) lower = starts[i];
        }
        
        int[] frq = new int[e + 1];
        int max = -1;
        int idx = -1;
        for(int i = e; i >= lower; i--) {
            if(res[i] >= max) {
                idx = i;
                max = res[i];
            }
            frq[i] = idx;
        }
        
        int[] ans = new int[len];
        for(int i = 0; i < len; i++) {
            ans[i] = frq[starts[i]];
        }
        
        return ans;
    }
}