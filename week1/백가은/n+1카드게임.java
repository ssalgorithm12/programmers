import java.util.*;

class Solution {
    
    public int solution(int coin, int[] cards) {
        int mid = cards.length / 2;
        int[] lower = new int[mid + 1];
        int[] upper = new int[mid + 1];
        Arrays.fill(lower, -1);
        Arrays.fill(upper, -1); 
                
        for(int i = 0; i < cards.length / 3; i++) {
            int now = cards[i];
            if(now <= mid) lower[now] = 0;
            else upper[now - mid] = 0;
        }
        
        int draw = cards.length / 3;
        int ans = 1;
        while(coin >= 0) {
            if(draw == cards.length) break;
            for(int i = 0; i < 2; i++) {
                int now = cards[draw++];
                if(now <= mid) lower[now] = 1;
                else upper[now - mid] = 1;
            }
            
            int min = Integer.MAX_VALUE;
            int idx = -1;
            for(int i = 1; i <= mid; i++) {
                if(lower[i] == -1 || upper[mid - i + 1] == -1) continue;
                int cost = lower[i] + upper[mid - i + 1];
                if(cost < min){
                    min = cost;
                    idx = i;
                }
            }
            if(min > coin) break;
            coin -= min;
            lower[idx] = -1;
            upper[mid - idx + 1] = -1;
            ans++;
        }
        return ans;
    }
}