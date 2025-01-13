import java.util.*;

// 이분탐색으로 방의 수 결정
// 우선순위 큐로 방 구현
class Solution {
    static int n;
    static int[][] time;
    
    public int solution(String[][] book_time) {
        n = book_time.length;
        time = new int[n][2];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < 2; j++) {
                time[i][j] = parsing(book_time[i][j].split(":"));
            }
        }
        
        // 시작시간 기준 오름차순 정렬
        Arrays.sort(time, new Comparator<>(){
            @Override
            public int compare(int[] a1, int[] a2) {
                return Integer.compare(a1[0], a2[0]);
            }
        });
        
        // 이분탐색
        int left = 1;
        int right = n;
        int mid = 0;
        int min = n + 1;
        while(left <= right) {
            mid = (left + right) / 2;
            if(bfs(mid)) {
                min = Math.min(min, mid);
                right = mid - 1;
            } else left = mid + 1;
        }
        
        return min;
    }
    
    // 분 단위로 변환
    static int parsing(String[] time) {
        return Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
    }
    
    // 우선순위큐에 방 개수만큼 예약 넣은 후
    // 종료 시간이 가장 이른 방부터 다음 손님 받기
    // 다음 손님 받을 수 없으면 false 반환
    static boolean bfs(int cnt) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<>(){
            @Override
            public int compare(int[] a1, int[] a2) {
                return Integer.compare(a1[1], a2[1]);
            }
        });
        for(int i = 0; i < cnt; i++) {
            pq.offer(new int[]{time[i][0], time[i][1]});
        }
        
        int idx = cnt - 1;
        while(!pq.isEmpty() && ++idx < n) {
            int[] cur = pq.poll();
            if(cur[1] + 10 > time[idx][0]) return false;
            pq.offer(new int[]{time[idx][0], time[idx][1]});
        }
        
        return true;
    }
}