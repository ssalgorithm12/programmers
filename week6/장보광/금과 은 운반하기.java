// 이분 탐색을 써야 된다는 것만 참고 하였습니다. 
class Solution {
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long left = 0; // 최소 시간 
        long right = 1000000000000000L; // 최대 시간
                
        // 이분 탐색 
        while(left +1 < right){
            long mid = (left+right)/2;
            if(check(a, b, g, s, w, t, mid))
                right = mid;
            else
                left = mid;
        }
        return right;
    }
    
    // 적재 가능한지 판별하는 코드 
    boolean check(int a, int b, int[] g, int[] s, int[] w, int[] t, long now){
        long total = 0, total_s = 0, total_g = 0;
        
        // 합 계산 
        for(int i = 0; i < g.length; i++){
            long r = w[i]*(now/(2*t[i]));
            if(now%(2*t[i]) >= t[i]) r += w[i];
            total += Math.min(g[i]+s[i], r);
            total_g += Math.min(g[i], r);
            total_s += Math.min(s[i], r);
        }
        
        // 결과 판별 
        if(total >= a+b && total_g >= a && total_s >= b)
            return true;
        return false;                      
    }
}
