import java.util.*;

class Solution {
    public int solution(int coin, int[] cards) {
        //  m = n/2
        int m = cards.length/2;
        // 현재 뽑은 카드의 수 
        int draw = cards.length/3;
        
        // n/2 이하인 수 
        int[] lower = new int[m+1];
        // n/2 초과인 수 
        int[] upper = new int[m+1];
        
        // 배열 초기화 
        Arrays.fill(lower, -1);
        Arrays.fill(upper, -1);
        
        // 결과 
        int answer = 1;
        
        // 초기 카드 할당;
        for(int i = 0; i < cards.length/3; i++){
            int now = cards[i];
            if(now > m) upper[now-m] = 0;
            else lower[now] = 0;
        }
        
        while(coin >= 0){
            // 먼저 카드 뽑기 
            for(int i = 0; i < 2; i++){
                if(draw < cards.length){
                    int now = cards[draw++];
                    if(now > m) upper[now-m] = 1;
                    else lower[now] = 1;    
                }
            }
            
           int min_cost = Integer.MAX_VALUE;
           int index = -1;
            
            for(int i = 1; i <= m; i++){
                // 뽑지 않은 카드일 경우 pass 
                if(lower[i]==-1 || upper[m-i+1]==-1) continue;
                
                int cost = lower[i] + upper[m-i+1];
                if(cost < min_cost) {
                    min_cost = cost;
                    index = i;
                }
            }
            
            if(min_cost > coin) break;
            coin -= min_cost;
            lower[index] = -1;
            upper[m-index+1] = -1;
            answer++;
            if(draw == cards.length) break;
        }
        
        return answer;
    }
}
