public class Solution {
    
    /*
        금을 우선으로 운반한 경우 금과 은의 양 -> Gmax, Smin
        은을 우선으로 운반한 경우 금과 은의 양 -> Gmin, Smax
        a <= Gmax
        b <= Smax
        a + b <= Gmax + Smin = Gmin + Smin
        만족한다면 주어진 시간 T 안에 a만큼의 금과 b만큼의 은을 운반할 수 있다는 것이다.
    */

    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {

        int n = g.length;
        
        long lower = 0;
        long upper = (long) (10e9 * 2 * 10e5 * 2);
        long mid = 0;
        
        long min = upper;
        
        while (lower <= upper) {
            mid = (lower + upper) / 2;
            
            int gold = 0;                               // 제한 시간(mid) 내에 옮길 수 있는 금의 양
            int silver = 0;                             // 제한 시간(mid) 내에 옮길 수 있는 은의 양
            int sum = 0;                                // 제한 시간(mid) 내에 옮길 수 있는 금과 은의 양의 합

            for(int i = 0; i < n; i++) {                
                int nowGold = g[i];                                 // 현재 도시의 금 보유량
                int nowSilver = s[i];                               // 현재 도시의 은 보유량
                int nowWeight = w[i];                               // 현재 도시의 최대 운반 가능 무게
                long nowTime = t[i];                                // 현재 도시의 운반 소요 시간
                
                long moveCount = mid / (nowTime * 2);               // 왕복 가능 최대 횟수
                if (mid % (nowTime * 2) >= t[i]) moveCount++;       // 편도로 이동(마지막엔 돌아올 필요 없으므로)이 가능한 경우

                // 운반 가능한 양을 합계에 더하기
                gold += Math.min(nowGold, moveCount * nowWeight);               // 금을 우선적으로 운반했을 경우 최대량
                silver += Math.min(nowSilver, moveCount * nowWeight);           // 은을 우선적으로 운반했을 경우 최대량
                sum += Math.min(nowGold + nowSilver, moveCount * nowWeight);    // 금 + 은 운반량 최대량
            }

            if (a <= gold && b <= silver && a + b <= sum) {
                upper = mid - 1;
                min = Math.min(min, mid);
                
            } else lower = mid + 1;

            
        }

        return min;
    }
}