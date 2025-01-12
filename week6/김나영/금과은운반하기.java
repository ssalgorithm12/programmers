class Solution {

    /*
     * <가은 피드백>
     * carry 메서드로 분리하니 가독성 좋은 것 같습니다
     */

    public boolean carry(long time, int a, int b, int[] g, int[] s,
                         int[] w, int[] t) {

        int n = g.length;
        long total = 0l;
        long total_a = 0l;
        long total_b = 0l;

        for (int i=0; i < n; i++) {
            // 해당 시간에 얼마나 옮길 수 있는지 cnt로 계산
            long cnt = time / (2l * t[i]);
            // 만약 편도 시간만큼 남는다면 cnt++
            if (time % (2l * t[i]) >= t[i]) cnt++;

            /*
            sum = 해당 구역에서 최대로 옮길 수 있는 광물의 양의 한계
            total = 해당 구역에서 해당 시간에 옮길 수 있는 광물의 최대 양
            total_a, total_b = 해당 구역에서 옮길 수 있는 금과 은의 최대 양
            */
            long sum = cnt * w[i];
            total += Math.min(sum, g[i]+s[i]);
            total_a += Math.min(sum, g[i]);
            total_b += Math.min(sum,s[i]);

            // 만약 모든 광물을 옮길 수 있다면 return true
            if (total >= a+b && total_a >= a && total_b >= b) return true;

        }
        return false;

    }

    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        // 가장 오래 걸리는 최악의 경우 계산
        long h = (long) Math.pow(10, 14) * 4;
        long l = 0;

        // h<=l일 때까지 계산
        while (h > l+1) {
            long mid = (h+l)/2;

            if (carry(mid, a, b, g, s, w, t)) h = mid;
            else l = mid;

        }

        return h;
    }
}
