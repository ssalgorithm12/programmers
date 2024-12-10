import java.util.*;

class Solution {
    static int min, m, t;
    static int INF = Integer.MAX_VALUE;
    static int[][] waiting;
    static List<int[]>[] requests;
    
    public int solution(int k, int n, int[][] reqs) {
        m = n;                        // 멘토 수
        t = k;                          // 유형 수 
        requests = new List[t + 1];     // 유형에 따른 요청 리스트
        for(int i = 1; i <= t; i++) {
            requests[i] = new ArrayList<>();
        }
        
        // 상담 유형에 따라 요청 분류
        for(int i = 0; i < reqs.length; i++) {
            int start = reqs[i][0];
            int len = reqs[i][1];
            int type = reqs[i][2];
            requests[type].add(new int[]{start, len});
        }
        
        // 각 유형별로 멘토수(1 ~ n-2)에 따른 대기시간 구하기
        waiting = new int[t + 1][m - (t - 1) + 1];      // 유형별 멘토수에 따른 대기시간 저장 배열
        for(int i = 1; i <= t; i++) {
            for(int j = 1; j <= m - (t - 1); j++) {
                if(requests[i].size() <= j) continue;   // 멘토수가 요청 수보다 많을 때 continue;
                waiting[i][j] = simul(requests[i], j);  // 멘토수에 따른 대기시간을 배열에 저장
            }
        }
        
        // 각 상담유형의 멘토수별 대기시간 조합
        // 멘토수가 n명이면서 대기시간이 최소인 경우 찾기
        min = INF;
        comb(1, 0, 0);
        return min;
    }
    
    // 멘토수에 따른 상담 시뮬레이션
    static int simul(List<int[]> req, int n) {
        int sum = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i < n; i++) {
            pq.offer(req.get(i)[0] + req.get(i)[1]);
        }
        int idx = n;
        while(!pq.isEmpty()) {
            if(idx == req.size()) break;
            int cur = pq.poll();
            int nt;
            int[] counsel = req.get(idx);
            if(cur > counsel[0]) {
                sum += cur - counsel[0];
                nt = cur + counsel[1];
            } else nt = counsel[0] + counsel[1];
            
            pq.offer(nt);
            idx++;
        }

        return sum;      
    }
        

    // 각 유형별 멘토수에 따른 대기시간 조합하여
    // 멘토수가 n명이 될 때 대기시간 최소값 갱신
    static void comb(int depth, int sum, int cnt) {
        if(depth == t + 1) {
            if(cnt != m) return;
            min = Math.min(min, sum);
            return;
        }
        
        for(int i = 1; i <= m - (t - 1); i++) {
            if(i > m - cnt) continue;
            comb(depth + 1, sum + waiting[depth][i], cnt + i);
        }
    }
}